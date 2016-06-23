<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${rc.contextPath}/index"><b>天猫博客</b></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${rc.contextPath}/index">主页</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">天猫搜索 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="http://www.tnmao.com">天猫磁力搜索</a></li>
                        <li><a href="http://www.tnmao.com/pan/index">天猫网盘搜索</a></li>
                        <li><a href="http://www.tnmao.com/guge/index">天猫谷歌搜索</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if Session.loginUser??>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <img style="width: 30px" src="${loginUser.img}" alt="${loginUser.nickName}">
                            <span>${loginUser.nickName}</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-left">
                            <li><a href="${rc.contextPath}/qq/logout">安全退出</a></li>
                        </ul>
                    </li>
                <#else >
                    <li>
                        <a href="${rc.contextPath}/qq/login" target="_self">
                            <img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png" alt="QQ登录" border="0">
                        </a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>