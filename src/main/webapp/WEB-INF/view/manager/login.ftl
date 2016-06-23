<html>
<head>
    <meta charset="UTF-8">
    <title>天猫的个人博客主页</title>
    <meta name="keywords" content="天猫的个人博客主页" />
    <meta name="description" content="天猫的个人博客主页" />
<#include "../common/commonCss.ftl">
</head>
<body style="background: url('${rc.contextPath}/resource/img/BingWallpaper-2016-05-27.jpg'); background-position-y: 50%; background-repeat:no-repeat;"  >
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="login-wrap" style="display: block">
                <div class="login" style="background:rgba(0,0,0,.5)">
                    <div style="text-align: center">
                        <h1 style="color:white">login</h1>
                    </div>
                    <form action="${rc.contextPath}/manager/login" method="post">
                        <div class="form-group" style="color:white">
                            <label for="exampleInputEmail1">用户名</label>
                            <input name="userName" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group" style="color:white">
                            <label for="exampleInputPassword1">密码</label>
                            <input name="passwd" type="password" class="form-control" placeholder="Password">
                        </div>
                        <div style="text-align: center">
                            <button style="width: 100%" class="btn btn-primary" type="submit">登录</button>
                        </div>
                        <div style="text-align: center">
                            <button style="width: 100%;margin-top: 10px" class="btn btn-danger" type="reset">重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../common/commonJs.ftl">
</body>
</html>