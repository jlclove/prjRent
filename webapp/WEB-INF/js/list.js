require([ 'jquery', 'util', 'spin', 'loading', 'text!/static/template/list.html', 'text!/static/template/list_large.html',
		'text!/static/template/list_empty.html', 'localization', 'bootstrap', 'lazyload',
		'nouislider', 'control', 'autocomplete', 'tmpl', 'cookie' ], function($, util, Spinner, Loading,
		listTemplate, largeListTemplate, listEmptyTemplate, Localization) {
	$(function() {
		var config = {
			connect : true,
			step : 10
		},
		category = {
			r : room ? 'r' + room : '',
			d : district ? district : ''
		}, priceFormat = {
			decimals : 0,
			to : function(value) {
				if (value >= maxPrice || value <= minPrice) {
					return Localization.UNIT_UNLIMITED;
				}
				return value + Localization.UNIT_TEN_THOUSAND;
			}
		}, areaFormat = {
			decimals : 0,
			to : function(value) {
				if (value >= maxArea || value <= minArea) {
					return Localization.UNIT_UNLIMITED;
				}
				return value + Localization.UNIT_SQUARE_METERS;
			}
		};

		function linkFactory(target, format) {
			return $.Link({
				target : $(target),
				format : format
			});
		}

        var obj = decodeURIComponent(location.search).substr(1).split('&');
        for ( var i in obj) {
            var temp = obj[i].split('=');
            if (temp[1] && temp[1]) {
                if(temp[0] == 'priceSort'){
                    $(window).data('sortObj', {'priceSort':temp[1]});
                }else if(temp[0] == 'acreageSort'){
                    $(window).data('sortObj', {'acreageSort':temp[1]});
                }
            }
        }

		function getUrl(param) {
			var d = category.d ? '/' + category.d : '';
			var r = category['r'] ? '/' + category['r']: '';

            var obj = decodeURIComponent(location.search).substr(1).split('&'), map = {};
            for ( var i in obj) {
                var temp = obj[i].split('=');
                if (temp[1] && temp[1]) {
                    map[temp[0]] = temp[1];
                }
            }
            $.extend(map, param);
			var search = util.parseParam(map);
			if (search) {
				search = '?' + search;
			}
			return {
				param : map,
				url : '/ershoufang' + d + r + search,
				ajaxUrl : '/json' + d + r + search
			};
		}

		function fetchData(title, fromUI) {
            var area = $('#area-slider').val(), price = $('#price-slider').val();
			var urlObj = getUrl($.extend({
                'acreage_from' : area[0] <= minArea ? fromUI ? null : undefined : parseInt(area[0], 10),
                'acreage_to' : area[1] >= maxArea ? fromUI ? null : undefined : parseInt(area[1], 10),
                'price_from' : price[0] <= minPrice ? fromUI ? null : undefined : parseInt(price[0], 10),
                'price_to' : price[1] >= maxPrice ? fromUI ? null : undefined : parseInt(price[1], 10),
                'priceSort' : fromUI ? null : undefined,
                'acreageSort' : fromUI ? null : undefined
            }, $(window).data('sortObj')));
			pageNo = 1;
            if(history.pushState){
                history.pushState($.extend(urlObj.param, category), title,
                    urlObj.url);
            }

			$.get(urlObj.ajaxUrl, function(response) {
				$('#inner-list').empty().scrollTop(0);
				$('#saleCount').text(response.count);
				if (response.count > 0) {
					totleCount = response.count;
					currentCount = response.data.length;
					var listStyle = $.cookie('listStyle');
					$.template('template', listStyle == 'large' ? largeListTemplate : listTemplate);
				} else {
					$.template('template', listEmptyTemplate);
				}
                response.showHeader = pageNo > 1 ? false : true;
                var domEl = $.tmpl('template', $.extend(response,{getYear: formatYear}, {
                    'priceSort' : null,
                    'acreageSort' : null
                }, urlObj.param));
                domEl.appendTo($('#inner-list'));
				lazyInit($('img.lazy'));
                $('img.load').show();
                if(isVisible()){
                    nextPage();
                }
			});
		}
        function formatYear(timestamp){
            return (new Date(timestamp)).getFullYear();
        }
		function lazyInit(target) {
			if(target.lazyload){
				target.lazyload({
					container : $('body'),
					effect : 'fadeIn',
					placeholder : ''
				});
			}else{
				setTimeout(function(){
					lazyInit(target);
				}, 500);
			}
		}

        var nextPageTrigger = null;
		function nextPage(){
            if(nextPageTrigger){
                clearTimeout(nextPageTrigger);
            }
            nextPageTrigger = setTimeout(function(){
                if(currentCount >= totleCount){
                    $('img.load').hide();
                    return;
                }
                var urlObj = getUrl({
                    pageNo : ++pageNo
                });
                $.get(urlObj.ajaxUrl, function(response) {
                    var listStyle = $.cookie('listStyle');
                    $.template('template', listStyle == 'large' ? largeListTemplate : listTemplate);
                    response.showHeader = pageNo > 1 ? false : true;
                    currentCount += response.data.length;
                    var tmpl = $.tmpl('template', $.extend(response,{getYear: formatYear}));
                    tmpl.appendTo($('#house-list'));
                    lazyInit(tmpl.find('img.lazy'));
                });
            }, 300);
		}

        lazyInit($('img.lazy'));
		$('.dropdown-toggle').dropdown();
		$('#menu').jqMenu({
			target : '#region-text',
			select : function(event) {
				event.preventDefault();
				category.d = $(event.target).attr('value');
				fetchData('d', true);
			}
		});
		$('#price-slider').noUiSlider($.extend({
			start : [ price_from, price_to ],
			range : {
				'min' : minPrice,
				'max' : maxPrice
			},
			serialization : {
				lower : [ linkFactory('#min-price', priceFormat),$.Link({
					target : $('#showLabel'),
					method : function(value){
						$(this).text( value );
						var top = $('#price-slider .noUi-connect').offset().top;
						var left = $('#price-slider .noUi-connect').offset().left;
			            $('#showLabel').css({'top': top - 60, 'left': left - $('#showLabel').outerWidth() / 2});
					},
					format: priceFormat
				}) ],
				upper : [ linkFactory('#max-price', priceFormat),$.Link({
					target : $('#showLabel'),
					method : function(value){
						$(this).text( value );
						var top = $('#price-slider .noUi-background').offset().top;
						var left = $('#price-slider .noUi-background').offset().left;
			            $('#showLabel').css({'top': top - 60, 'left': left - $('#showLabel').outerWidth() / 2});
					},
					format: priceFormat
				}) ]
			}
		}, config)).on({
			slide: function(){
				$('#showLabel').fadeIn('fast');
			},
			change: function(){
				$('#showLabel').fadeOut('fast');
			}
		});
		
		$('#area-slider').noUiSlider($.extend({
			start : [ acreage_from, acreage_to ],
			range : {
				'min' : minArea,
				'max' : maxArea
			},
			serialization : {
				lower : [ linkFactory('#min-area', areaFormat),$.Link({
					target : $('#showLabel'),
					method : function(value){
						$(this).text( value );
						var top = $('#area-slider .noUi-connect').offset().top;
						var left = $('#area-slider .noUi-connect').offset().left;
			            $('#showLabel').css({'top': top - 60, 'left': left - $('#showLabel').outerWidth() / 2});
					},
					format: areaFormat
				}) ],
				upper : [ linkFactory('#max-area', areaFormat),$.Link({
					target : $('#showLabel'),
					method : function(value){
						$(this).text( value );
						var top = $('#area-slider .noUi-background').offset().top;
						var left = $('#area-slider .noUi-background').offset().left;
			            $('#showLabel').css({'top': top - 60, 'left': left - $('#showLabel').outerWidth() / 2});
					},
					format: areaFormat
				}) ]
			}
		}, config)).on({
			slide: function(){
				$('#showLabel').fadeIn('fast');
			},
			change: function(){
				$('#showLabel').fadeOut('fast');
			}
		});
		$('#price-slider').change(function(event, data) {
			fetchData('price', true);
		});
		$('#area-slider').change(function(event, data) {
			fetchData( 'acreage', true);
		});

		var timeout = null, threshold = 450;
        $('.js_fangx').click(function(evt) {
			if (timeout) {
				clearTimeout(timeout);
			}
			timeout = setTimeout(function() {
				$('.js_fangx').removeClass('active');
				var r;
				if ((r = $(evt.target).addClass('active').val()) > 0) {
					category.r = 'r' + r;
				} else {
					category.r = null;
				}
				fetchData('r', true);
			}, 100);
		});
		$(window).on('scroll', function(){
			if(isVisible()){
				nextPage();
			}
		});

        function isVisible(){
            return $(window).height() >= $(document).height() - $(document).scrollTop() - threshold;
        }
        /* 切换列表样式 */
        $(document).on('click', '.js_list_large', function(){
            if($.cookie('listStyle') == 'large'){

            }else {
                $.cookie('listStyle', 'large', { expires: 7, path: '/' });
                fetchData('', true);
            }
        });
        $(document).on('click', '.js_list', function(){
            if($.cookie('listStyle') == 'normal'){

            }else {
                $.cookie('listStyle', 'normal', { expires: 7, path: '/' });
                fetchData('', true);
            }
        });
        $(document).on('click', '.js_sort_acreage', function(){
            var sort = $(window).data('sortObj');
            if( sort && sort['acreageSort'] == 'desc'){
                $(window).data('sortObj', {acreageSort : 'asc'});
            }else{
                $(window).data('sortObj', {acreageSort : 'desc'});
            }
            fetchData('', true);
        });
        $(document).on('click', '.js_sort_price', function(){
            var sort = $(window).data('sortObj');
            if( sort && sort['priceSort'] == 'asc'){
                $(window).data('sortObj', {priceSort : 'desc'});
            }else{
                $(window).data('sortObj', {priceSort : 'asc'});
            }
            fetchData('', true);
        });
        $(document).on('click', '.js_sort_default', function(){
            $(window).data('sortObj', null);
            fetchData('', true);
        });

		$('#wd').autoPrompt({
			key: 'key',
			url: '/json/autocomplete',
			id: 'id',
			data: 'propertyList',
			text: 'propertyName',
			selected: function(){
				$('#auto').val(true);
				$('#form').submit();
			}
		});
	});
});