<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>${blogClass.className} -天猫的个人博客主页</title>
    <meta name="keywords" content="${blogClass.className},天猫的个人博客主页" />
    <meta name="description" content="${blogClass.className}" />
<#include "../common/commonCss.ftl">
</head>
<body>
<#include "common/header.ftl">
<div class="container">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="${rc.contextPath}/index">主页</a></li>
            <#if blogClass??>
                <li class="active">${blogClass.className}</li>
            </#if>
        </ol>
        <div class="col-md-8">
            <#include "common/comBlogList.ftl">
            <#assign path=rc.contextPath+"/blog/blogClass/${blogClass.id}">
            <#include "common/pager.ftl">
        </div>
    <#include "common/rightNav.ftl">
    </div>
</div>
<#include "../common/commonJs.ftl">
<#include "common/footer.ftl">
</body>
</html>