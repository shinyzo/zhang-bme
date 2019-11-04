<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>BME - 登录中心</title>
    <link rel="stylesheet" th:href="@{/layui/css/layui.css}">
</head>
<body class="layui-layout-body">

<form class="layui-form" >
    <div class="layui-form-item">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-inline">
            <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密 码:</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">(密码不能少于6位)</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit  lay-filter="loginSubmit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script th:src="@{/layui/layui.js}"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form
            , $   = layui.jquery;
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

    });
</script>
</body>
</html>