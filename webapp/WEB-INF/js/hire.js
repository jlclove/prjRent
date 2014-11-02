require(['jquery'], function(){
    $(function(){
        $('.js_phone').on('keydown', function(e){
            if(e.keyCode == 13){
                send($(this).val(), $(this).attr('position'));
            }
        });

        $('.js_btn').on('click', function(){
            var input = $(this).parent().siblings();
            send(input.val(), input.attr('position'));
        });

        function send(phoneNum, position){
            if(this.timeout){
                clearTimeout(this.timeout);
                this.timeout = null;
            }
            this.timeout = setTimeout(function(){
                var phoneReg = /^1[3|4|5|8][0-9]\d{8}$/;
                if(!phoneReg.test(phoneNum)){
                    alert('不是有效的手机号码');
                    return;
                }
                $.post('/hire/send', {mobilePhone: phoneNum, position: position},function(response){
                    if(response.status == 'ok'){
                        alert('您的手机号码信息已提交，招聘人员会在两天内与您联系');
                    }else if(response.status == 'fail'){
                        alert(response.message);
                    }
                });
            }, 100);
        }
    });
});