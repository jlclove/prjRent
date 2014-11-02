(function($){
	$.fn.jqMenu = function(options) {
		var data = options ? options.data || [] : [],
			target = options.target,
			self = $(this);

		if(data.length > 0){
			render(data);
		}

		function render(d){
			var _html = function(d, c){
				c = c ? c + "-" : "";
				var template = "<ul class=" + c + "jqmenu>";
				$.each(d, function(i, e){
					template += "<li class=" + c + "jqmenu-item>";
					template += "<a>" + e.text + "</a>";
					if(e.children){
						template += _html(e.children, "sub");
					}
					template += "</li>";
				});
				template += "</ul>";
				return template;
			};
			var dom = $(_html(d));
			self.append(dom);
		}

		self.find('.jqmenu').on('mouseenter', '.jqmenu-item > a', function(event){
			var target = $(event.target).siblings('ul.sub-jqmenu:hidden');
			if(target.length > 0){
				$('.jqmenu ul.sub-jqmenu').fadeOut(200);
				target.fadeIn(200);
			}
		});

		self.find('.sub-jqmenu').on('mouseleave', function(event){
			$('.jqmenu ul.sub-jqmenu').fadeOut(); 
		});

		self.on('click', 'a', function(event){
			if(target){
				$(target).text($(event.target).text());
			}
			if($.isFunction(options.select)){
				options.select.call($(event.target), event);
			}
		});
	}; 
})(jQuery);