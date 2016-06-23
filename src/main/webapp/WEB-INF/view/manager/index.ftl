<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>天猫的个人博客主页</title>
    <meta name="keywords" content="天猫的个人博客主页" />
    <meta name="description" content="天猫的个人博客主页" />
    <#include "../common/commonCss.ftl">
    <link href="${rc.contextPath}/resource/css/dashboard.css" rel="stylesheet">
</head>
<body>
<!-- 头 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <!-- 边侧导航栏 -->
        <div class="col-sm-3 col-md-2 sidebar">
            <label>博客管理</label>
            <ul id="left-bar" class="nav nav-sidebar">
                <li class="active">
                    <a href="#" onclick="load('${rc.contextPath}/manager/blog/list/1')">博客列表</a>
                </li>
                <li><a href="#" onclick="load('${rc.contextPath}/manager/blog/add')">发表博文</a></li>
                <li><a href="#">Analytics</a></li>
                <li><a href="#">Export</a></li>
            </ul>
        </div>
        <!-- 正文 -->
        <div id="content-div" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <#include "blogList.ftl">
        </div>
    </div>
</div>
</body>
<#include "../common/commonJs.ftl">
<script src="${rc.contextPath}/resource/js/managerJs.js" type="text/javascript"></script>
</html>