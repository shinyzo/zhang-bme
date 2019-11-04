<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title>BME - 登录中心</title>
    <link rel="stylesheet" th:href="@{/css/layui/css/layui.css}">
    <link rel="stylesheet" th:href="@{/css/bme/login.css}">
</head>
<body class="layui-layout-login">
<div class="login-bg">
    <div class="cover"></div>
</div>
<form class="layui-form" th:action="@{/loginauth}" method="post">
    <div class="container">
        <button class="close" title="关闭">X</button>
        <div class="layui-form-mid layui-word-aux">
           <div> BME - 登录中心 </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-inline">
                <input type="text" name="title" placeholder="请输入验证码" autocomplete="off" class="layui-input verity">
            </div>
            <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->

        </div>
        <div class="layui-form-item">
                <label class="layui-form-label">记住密码</label>
                <div class="layui-input-block">
                  <input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">
                </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="loginSubmit">登陆</button>
            </div>
        </div>
        <a href="" class="font-set">忘记密码?</a>  <a href="" class="font-set">立即注册</a>
    </div>
</form>
<script type="text/javascript" th:src="@{/css/layui/layui.js}"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer;

        //监听提交
        form.on('submit(loginSubmit)', function(data){
            console.log(data.field);
            $.ajax({
                url:'/bme/loginauth',
                async: false,
                type:'POST',
                dataType: 'json',
                data:data.field,
                success: function(data){
                    console.log(data);
                    if(data.code=="0000"){
                        layer.msg(data.msg);
                        location.href= "/bme/main/index";
                    }else{
                        layer.alert(data.msg);
                    }
                }
            })
            return false;
        });

        //表单初始赋值
        /* form.val('example', {
         "username": "贤心" // "name": "value"
         ,"password": "123456"
         ,"interest": 1
         ,"like[write]": true //复选框选中状态
         ,"close": true //开关状态
         ,"sex": "女"
         ,"desc": "我爱 layui"
         })*/


    });
</script>
</body>
</html>