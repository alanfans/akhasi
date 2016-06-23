<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>天猫的个人博客主页</title>
    <meta name="keywords" content="天猫的个人博客主页" />
    <meta name="description" content="天猫的个人博客主页" />
    <#include "../common/commonCss.ftl">
</head>
<body>
    <#include "common/header.ftl">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <#include "common/comBlogList.ftl">
                <#assign path=rc.contextPath+"/index">
                <#include "common/pager.ftl">
            </div>
            <#include "common/rightNav.ftl">
        </div>
    </div>
    <#include "../common/commonJs.ftl">
    <#include "common/footer.ftl">
</body>
</html>