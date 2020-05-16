<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="./css/reg.css">

</head>
<body>
    <div class="reg">
        <div class="header">
            <h1>
                <a href="./login">登录</a> <a href="./register">注册</a>
            </h1>
        </div>
        <form action="" method="post" id="userForm">
            <table>
                <tr>
                    <td class="td1">用户名</td>
                    <td><input type="text" class="input1" name="username" id="username" placeholder="请输入用户名"></td>
                </tr>
                <tr>
                    <td class="td1">密码</td>
                    <td><input type="password" class="input1" name="password" id="password" placeholder="请输入密码"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="btn-red">
                            <input type="button" value="注册" id="reg-btn" onclick="doRegister()">
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="layer/layer.js"></script>

    <script>
        //1.登录单击事件
        function doRegister(){
            //账号和密码的非空校验(使用jQuery做的id选择器)
            var username = $("#username").val();
            var password = $("#password").val();
            var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;

            //表单元素的value取值不会为null，当为空时是空字符串
            if(username == "" || password == ""){

                /*为了使弹出框的样式保持一致，不受浏览器的影响，可以使用组件layer
                * layer.msg(arg1,arg2,arg3)
                * arg1：提示信息
                * arg2: time为弹出框持续的时间，单位为ms
                *       icon为弹出框呈现的样式
                *       shift为弹出框出现的样式
                * arg3: 回调方法
                * 具体细节可以参考layer文档
                * */
                layer.msg("用户注册账号或密码为空，请输入", {time:2000, icon:5, shift:6}, function(){});
                return;
            }

            //验证密码是否符合要求
            if(!pwdReg.test(password)){
                layer.msg("密码必须由数字和字母组成，且长度在8-16位之间，请重新输入", {time:2000, icon:5, shift:6}, function(){});
                $("#password").reset();
                return;
            }

            //提交表单
            var loadingIndex=null;
            $.ajax({
                type:"POST",
                url:"doAjaxRegister",
                data:{"username":username,"password":password},
                beforeSend:function(){
                    loadingIndex = layer.msg('注册中', {icon: 16});
                },
                success:function(result){
                    layer.close(loadingIndex);
                    if(result.success){
                        layer.msg("注册成功!", {time:2000, icon:2, shift:6}, function(){});
                        //后台验证用户注册成功之后，跳转到login.jsp页面
                        window.location.href = "login";
                    }else{
                        layer.msg("该用户名已经被注册，请重新填写！", {time:2000, icon:2, shift:6}, function(){});
                        //清除已经填写的用户信息
                        $("#userForm")[0].reset();
                    }
                }
            });
        }

    </script>

</body>
</html>