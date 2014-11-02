require(['bootstrap', 'autocomplete'], function(){
    $(function(){
        $('#form').on('submit', function(){
            var wd = $('#wd');
            if($.trim(wd.val()) === ''){
                wd.val('').focus();
                window.location = "/ershoufang";
                return false;
            }
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

        $('.btn-service').click(function(){
            $('.btn-service').removeClass('active');
            $(this).addClass('active');

            var shreshold = 550;
            if($('body').scrollTop() < shreshold)
            $('body').animate({scrollTop:shreshold},'slow');
        });
        $('[href=#buy]').click(function(){
            $('#sell').fadeOut().removeClass('active').addClass('right');
            $('#buy').fadeIn().addClass('active').removeClass('left');;
        });
        $('[href=#sell]').click(function(){
            $('#sell').fadeIn().addClass('active').removeClass('right');
            $('#buy').fadeOut().removeClass('active').addClass('left');;
        });

        polyvObject.thisMovie = function (movieName) {
        	if (navigator.appName.indexOf("Microsoft") != -1) {
				var reObj=window[movieName];
				try {
					if(reObj.length>0){
						return reObj[0];
					} else {
						return reObj;
					}
				} catch(e){
					console.log(e);
				}
				return document[movieName];
			} else {
				return document[movieName];
			}
		};
        
        polyvObject.swf.prototype.stop = function(){
        	var object = document.getElementById(this.container).childNodes[0];
        	try {
        		polyvObject.thisMovie(object.id).j2s_pauseVideo();
			} catch(e) {
				console.log(e);
			}
        };
        polyvObject.swf.prototype.play = function() {
			var object = document.getElementById(this.container).childNodes[0];
			try {
				polyvObject.thisMovie(object.id).j2s_resumeVideo();
			} catch(e) {
				console.log(e);
			}
		};
        $('#myModal').on('hide.bs.modal', function (e) {
            if(polyv_player && polyvObject && polyv_player.stop){
            	polyv_player.stop();
            }
        });
        $('#myModal').on('shown.bs.modal', function (e) {
            if(polyv_player && polyvObject && polyv_player.play){
            	polyv_player.play();
            }
        });
    });
});