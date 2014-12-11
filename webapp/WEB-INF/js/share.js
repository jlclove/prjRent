require([ 'jquery', 'jqueryui' ], function($) {
	$(function() {
		var sorting = false;
//		$.get('/getPictureList/' + propertyId, function(pictureList){
//			var picture, remark, path;
//			for(var i = 0; i < pictureList.length; i++){
//				picture = pictureList[i];
//				remark = picture.remark ? picture.remark : '';
//				path = picture.path;
//				if(!pictureListNow[path]){
//					var dom = '<div class="col-md-3">' +
//						'<a href="#" class="thumbnail">' +
//							'<img src="/static/imgs/' + path + '" realsrc="'+path+'">' +
//							'<div class="caption" data-remark="' + remark + '">' + remark + '</div>' +
//							'<span class="glyphicon glyphicon-ok"></span>'+
//						'</a>' +
//					'</div>';
//					$('#sortable').append($(dom));
//				}
//			}
//		});
		$("#sortable").sortable({
			revert : 300,
			sort: function(){
				sorting = true;
			}
		});
		$('#sortable').on('click', 'a', function(e){
			e.preventDefault();
		});
		$('#sortable').on('mousedown', 'a', function(e){
			sorting = false;
		});
		$('#sortable').on('mouseup', 'a', function(e){
			if(!sorting){
				$(this).toggleClass('active');
				countPhoto();
			}
		});
		$('#gene').on('click', function(e){
			e.preventDefault();
			if(checkForm()){
				var selectedPic = $('#sortable a.active');
				var formData = {},i = 0;
				$.each(selectedPic, function(i, o){
					var img = $(o).find('img');
					var allImg = $("#sortable a");
					formData['housePictureList[' + i + '].pictureUrl'] = img.attr('realsrc');
					formData['housePictureList[' + i + '].remark'] = img.attr('data-remark');
					formData['housePictureList[' + i + '].sequence'] = allImg.index(o);
					i++;
				});
				$.extend(formData, {
					'id': houseId,
					'userName': $('#name').val(),
					'mobilePhone': $('#phone').val(),
					'price': $('#price').val(),
					'title':$('#title').val(),
					'attachName':$('[name=attachName]').val()
				});
				console.log(formData);
				$.ajax({
					type: 'post',
					url: '/share/submit'+($('[name=password]').val()==''?'':'/'+$('[name=password]').val()),
					data: formData,
					success: function(response){
						$("#resultDiv").fadeIn(500);
						window.setTimeout(function(){
								$("#resultDiv").fadeOut(1000);
								window.location.href = "/" + response.messageId;
								},2000);
					}
				});
			}
		});
		function checkForm(){
			var errorMsg = [];
			var rules = {
				empty: {
					pattern: /^.+$/,
					msg: '必填'
				},
				number: {
					pattern: /^\d+$/,
					msg: '必须是数字'
				},
				decimal: {
					pattern: /^\d+(\.\d+)?$/,
					msg: '必须是数字'
				},
				selected: {
					pattern: function(ele){
						return ele.find('a.active').length > 0;
					},
					msg: '至少选择一张图片',
					pos: 'before'
				}
			};
			checkField($('#title'),[rules.empty]);
			checkField($('#price'),[rules.empty, rules.decimal]);
			checkField($('#name'),[rules.empty]);
			checkField($('#phone'),[rules.empty, rules.number]);
			checkField($('#sortable'),[rules.selected]);
			function checkField(ele, rules){
				for(var i = 0; i < rules.length; i++){
					if($.isFunction(rules[i].pattern)){
						if(rules[i].pattern(ele)){
							hideError(ele);
						}else{
							showError(ele,rules[i].msg, rules[i].pos);
							errorMsg.push(ele);
							break;
						}
						continue;
					}
					if(rules[i].pattern.test(ele.val())){
						hideError(ele);
					}else{
						showError(ele,rules[i].msg);
						errorMsg.push(ele);
						break;
					}
				}
				if(errorMsg.length > 0){
					errorMsg[0].focus();
				}
			}
			function showError(ele, msg, pos){
				if(ele.parent().find('.error ').length == 0){
					if(pos == 'before'){
						ele.parent().prepend($('<span class="error" style="color:red">' + msg + '</span>'));
					}else{
						ele.parent().append($('<span class="error mg_l20" style="color:red">' + msg + '</span>'));
					}
				}
			}
			function hideError(ele){
				ele.parent().find('.error ').remove();
			}
			return errorMsg.length == 0;
		}
		function countPhoto(){
			$('#photoCount').text($('#sortable a.active').length);
		}
	});
});

function addPic(fileName){
	var html = '<div class="col-md-3">\
					<a href="#" class="thumbnail active"> <img\
					src="/static/imgs/'+fileName+'"\
					realsrc="'+fileName+'">\
					<div class="caption" data-remark=""></div>\
					<span class="glyphicon glyphicon-ok"></span>\
				</a>\
			</div>';
	$('#sortable').append(html);
	$('#photoCount').html(parseInt($('#photoCount').html())+1);
}

function addRar(fileName){
	$('[name=attachName]').val(fileName);
}