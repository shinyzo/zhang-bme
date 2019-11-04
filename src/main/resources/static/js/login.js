if(window.top!==window.self){window.top.location=window.location};
layui.use(['element','layer','form'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    // 刷新验证码
    $(document).on('click', '.captcha-img', function () {
        var src = this.src.split("?")[0];
        this.src = src + "?" + Math.random();
    });

    $(document).on('click', '.ajax-login', function (e) {
        e.preventDefault();
        var form = $(this).parents("form");
        var url = form.attr("action");
        var serializeArray = form.serializeArray();
        $.post(url, serializeArray, function (result) {
            console.log(result)
            if(result.code != 0000){
                $('.captcha-img').click();
                layer.msg(result.msg);
                return false;
            }
            window.location.href= "./main/index";
        });
    });

});