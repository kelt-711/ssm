<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.test.utils.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="./css/login.css">

</head>

<%
    //完成用户名的回显
    String username="";
    //获得从客户端携带过来的所有Cookie
    Cookie[] cookies=request.getCookies();
    //从Cookie的数组中查找指定名称的Cookie
    Cookie cookie = CookieUtils.findCookie(cookies, "username");
    if(cookie!=null)
    {
        username=cookie.getValue();
    }
%>


<body>
    <div class="login">
        <div class="header">
            <h1>
                <a href="./login">登录</a> <a href="./register">注册</a>
            </h1>
        </div>

        <form action="" method="post">
            <table>
                <tr>
                    <td class="td1">用户名</td>
                    <td><input type="text" class="input1" id="username" name="username" value="<%= username %>" placeholder="请输入用户名"></td>
                </tr>
                <tr>
                    <td class="td1">密码</td>
                    <td><input type="password" class="input1" id="password" name="password" placeholder="请输入密码"></td>
                </tr>
                <tr>
                    <td class="td1" colspan="2">
                        <input type="checkbox" name="rememberUsername" value="true" checked="checked"> 记住用户名</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="btn-red">
                            <input type="button" value="登录" id="login-btn" onclick="doLogin()">
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
        function doLogin(){
            //账号和密码的非空校验(使用jQuery做的id选择器)
            var username = $("#username").val();
            var password = $("#password").val();

            //表单元素的value取值不会为null，当为空时是空字符串
            if(username == "" || password==""){
                //alert("用户登录账号或密码为空，请输入");
                /*为了使弹出框的样式保持一致，不受浏览器的影响，可以使用组件layer
                * layer.msg(arg1,arg2,arg3)
                * arg1：提示信息
                * arg2: time为弹出框持续的时间，单位为ms
                *       icon为弹出框呈现的样式
                *       shift为弹出框出现的样式
                * arg3: 回调方法
                * 具体细节可以参考layer文档
                * */
                layer.msg("用户登录账号或密码为空，请输入", {time:2000, icon:5, shift:6}, function(){});
                return;
            }

            var rememberUsername = document.getElementsByName("rememberUsername");
            var flag = rememberUsername[0].checked;

            //提交表单
            var loadingIndex=null;
            $.ajax({
                type:"POST",
                url:"doAjaxLogin",
                data:{"username":username,"password":password,"flag":flag},
                beforeSend:function(){
                    loadingIndex = layer.msg('登录中', {icon: 16});
                },
                success:function(result){
                    layer.close(loadingIndex);
                    if(result.success){
                        //后台验证用户的信息成功之后，跳转到index.jsp页面
                        window.location.href = "index";
                    }else{
                        layer.msg("用户的登录账号或密码不正确，请重新输入！", {time:2000, icon:2, shift:6}, function(){});
                    }
                }
            });

        }

    </script>

</body>
</html>

