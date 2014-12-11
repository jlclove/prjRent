// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
//	$(".shareDiv").hide();
//	// 发送给好友
//	WeixinJSBridge.on('menu:share:appmessage', function(argv) {
//		shareFriend();
//	});
//	
//	// 分享到朋友圈
//	WeixinJSBridge.on('menu:share:timeline', function(argv) {
//		shareTimeline();
//	});
//	
//	// 分享到微博
//	WeixinJSBridge.on('menu:share:weibo', function(argv) {
//			shareWeibo();
//		});
	}, false);

Galleria.loadTheme('/static/vendor/galleria/src/themes/classic/galleria.classic.js');

if(isMobileAgent()){
	$('#photo-slide').append('<a href="http://api.map.baidu.com/staticimage?width=' +$(window).width()+ '&height=200&markers=' + latitude + ',' + longitude + '&zoom=17&markerStyles=m,,0xff0000"><img src="http://api.map.baidu.com/staticimage?width=' +$(window).width()+ '&height=200&markers=' + latitude + ',' + longitude + '&zoom=17&markerStyles=m,,0xff0000"></a>');
}

var config = {
	debug: false,
	thumbCrop: true,
	imageCrop: true,
	transition: 'slide',
	initialTransition: 'fade',
	preload: 0,
	idleMode: 'hover',
	carouselSteps: 1,
	lightbox: false
};
Galleria.run('#photo-slide', config);
Galleria.on('image', function(e) {
	$('#mytab li').removeClass('active');
	var index = this.getIndex(),
		length = this.getDataLength();
	if(index === length - 1){
		$('#mytab li:nth(1)').addClass('active');
	}else{
		$('#mytab li:nth(0)').addClass('active');
	}
	if(e.imageTarget && e.imageTarget.contentWindow && e.imageTarget.contentWindow.initMap){
		e.imageTarget.contentWindow.initMap();
	}
	Galleria.configure({preload: 2});
});

Galleria.ready(function(){
	var API = this;
	if(isMobileAgent()){
	}else{
		this.push({iframe: '/static/template/map.html',thumb:'/static/images/map-icon.jpg'});
	}
	API._carousel.next.on('click', function(){
		if(API.getIndex() < API.getDataLength() -1){

			API.next();
		}
	});
	API._carousel.prev.on('click', function(){
		if(API.getIndex() > 0){
			API.prev();
		}
	});
	$('#mytab').on('click', 'a[data-index]', function(evt){
		$(evt.delegateTarget).find('li').removeClass('active');
		$(evt.currentTarget).parent().addClass('active');
		var index = parseInt($(evt.currentTarget).attr('data-index'), 10);
		if(index === API.getIndex()){
			return;
		}
		if(index >= 0){
			API.show(index);
		}
	});
	
	$('#login').on('click',function(e){
		var pass = $('#inputPassword').val();
		if(pass==''){
			alert('请输入密码');
		}else{
			window.location.href=$(this).attr('tar')+'/'+pass;
		}
		
	});
});


//分享
function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
      //"appid":window.shareData.appid,
      "img_url":window.shareData.imgUrl,
      "img_width":"640",
      "img_height":"640",
      "link":window.location.href,
      "desc":'描述',
      "title":'标题',
      }, function(res) {
      _report('send_msg', res.err_msg);
      })
}
function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
      "img_url":window.shareData.imgUrl,
      "img_width":"640",
      "img_height":"640",
      "link":window.location.href,
      "desc": '描述',
      "title": '标题',
      }, function(res) {
      _report('timeline', res.err_msg);
      });
}
function shareWeibo() {
    WeixinJSBridge.invoke('shareWeibo',{
      "content":window.shareData.wContent,
      "url":window.shareData.wLink,
      }, function(res) {
      _report('weibo', res.err_msg);
      });
}

function isMobileAgent(){
    var sUserAgent=navigator.userAgent.toLowerCase();
    var bIsIpad=sUserAgent.match(/ipad/i)=="ipad";
    var bIsIphoneOs=sUserAgent.match(/iphone os/i)=="iphone os";
    var bIsMidp=sUserAgent.match(/midp/i)=="midp";
    var bIsUc7=sUserAgent.match(/rv:1.2.3.4/i)=="rv:1.2.3.4";
    var bIsUc=sUserAgent.match(/ucweb/i)=="ucweb";
    var bIsAndroid=sUserAgent.match(/android/i)=="android";
    var bIsCE=sUserAgent.match(/windows ce/i)=="windows ce";
    var bIsWM=sUserAgent.match(/windows mobile/i)=="windows mobile";
    if(bIsIpad||bIsIphoneOs||bIsAndroid||bIsMidp||bIsUc7||bIsUc||bIsCE||bIsWM)
    {
        return true;
    }else{
    	return false;
    }
}