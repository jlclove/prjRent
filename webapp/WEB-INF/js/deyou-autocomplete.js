(function($, window, document, undefined) {
	$.fn.autoPrompt = function(options){
		var elements = this,
			$content = $('<ul class="prompt-content"></ul>');
		var cache	= {};
		var settings = {
			key: 'key',
			id: 'id',
			text: 'text',
			showDefault: true,
			title: '\u70ed\u95e8\u641c\u7d22',
			count: 'sellCount',
			interval: 100
		};

		$.extend(settings, options);

		function render(){
			elements.addClass('search-input');
			elements.after($content);

            elements.on('input', function(){
                if(this.timeout){
                    clearTimeout(this.timeout);
                }
                this.timeout = setTimeout(function(){
                    fetchData();
                    $content.data('index', -1);
                }, settings.interval);
            });
            elements.on('propertychange', function(){
                if(this.timeout){
                    clearTimeout(this.timeout);
                }
                this.timeout = setTimeout(function(){
                    fetchData();
                    $content.data('index', -1);
                }, settings.interval);
            });
			
			elements.on('keydown', function(e){
				var keyCode = e.keyCode;
				if(keyCode === 38){
					e.preventDefault();
					$content.trigger('prev');
				}else if(keyCode === 40){
					e.preventDefault();
					$content.trigger('next');
				}else if(keyCode === 13){
//					e.preventDefault();
//					elements.trigger('selected');
				}
			});

			elements.on('selected', function(e){
				settings.selected.call(this, e);
			});

			elements.on('focus', function(){
				if(this.hideFn){
					clearTimeout(this.hideFn);
				}
				fetchData();
			});
			elements.on('blur', function(){
				if(this.hideFn){
					clearTimeout(this.hideFn);
				}
				this.hideFn = setTimeout(function(){
					$content.hide();
				}, 200);
			});
			elements.on('dbclick', function(e){
				e.preventDefault();
			});
			$content.on('click', 'li[data-value]', function(){
				elements.val($(this).attr('data-value'));
				elements.trigger('selected');
				$content.hide();
			});
			$content.on('prev', function(){
				$content.find('li[data-value]').removeClass('active');
				if($content.data('index') > 0){
					$content.data('index', $content.data('index') - 1);
				}else{
					$content.data('index', $content.find('li[data-value]').length - 1);
				}
				var $active = $content.find('li[data-value]:eq(' + $content.data('index') + ')').addClass('active');
				elements.val($active.find('.text').text());
			});
			$content.on('next', function(){
				$content.find('li').removeClass('active');
				if($content.data('index') < $content.find('li[data-value]').length - 1){
					$content.data('index', $content.data('index') + 1);
				}else{
					$content.data('index', 0);
				}
				var $active = $content.find('li[data-value]:eq(' + $content.data('index') + ')').addClass('active');
				elements.val($active.find('.text').text());
			});
		}

		function fetchData(){
			var key = $.trim(elements.val());
			if(!cache['$' + key]){
				if(!settings.url){
					throw new Error('missing request url');
				}
				$.get(settings.url + '?' +settings.key + '=' +encodeURIComponent( key), function(response){
					if(response.status === 'ok'){
						reRenderContent(response[settings.data]);
						cache['$' + key] = response[settings.data];
					}else{
						$content.hide();
						cache['$' + key] = [];
					}
				});
			}else{
				reRenderContent(cache['$' + key]);
			}
		}

		function reRenderContent(data){
			var key = $.trim(elements.val());
			if(data.length > 0){
				$content.show();
			}else{
				$content.hide();
			}
			$content.empty();
			$.each(data, function(i, o){
				var $tmp;
				if(key !== ''){
					$tmp = $('<li id=' + o[settings.id] + ' data-value=' + o[settings.text] + '><span class="text">' + o[settings.text].replace(key, '<strong>' + key + '</strong>') + '</span></li>');
					$content.append($tmp);
				}else{
					$tmp = $('<li id=' + o[settings.id] + ' data-value=' + o[settings.text] + '><span class="text">' + o[settings.text] + '</span></li>');
					$content.append($tmp);
				}
				if(o[settings.count]){
					$tmp.append('<span class="count">\u7ea6' + o[settings.count] + '\u5957</span>');
				}
			});
			if(key === '' && settings.showDefault){
				$content.prepend('<li style="color:#f60">' + settings.title + '</li>');
			}
		}
		render();
	};

})(jQuery, window, document);