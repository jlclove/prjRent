require(['bootstrap'], function(){
    $(function(){
        $('[data-spy="scroll"]').scrollspy();
        $(window).on('scroll', function(){
            if(this.timeout){
                clearTimeout(this.timeout);
                this.timeout = null;
            }
            this.timeout = setTimeout(function(){
                var top = $(document).scrollTop() - $('.content').offset().top,
                    topValue = $('#main').outerHeight() + $('.dooio-header').outerHeight() -$('#contact').outerHeight();
                if(top > 0){
                    if($(document).scrollTop() >= topValue){
                        $('#category').css({
                            'position': 'absolute',
                            'top': topValue + 'px'
                        });
                    }else{
                        $('#category').css({
                            'position': 'fixed',
                            'top': '0'
                        });
                    }
                }else{
                    $('#category').css({
                        'position': 'static'
                    });
                }
            }, 10);
        });
        $('#btn-showmore').click(function(){
            $(this).parent().hide();
        });
    });
});