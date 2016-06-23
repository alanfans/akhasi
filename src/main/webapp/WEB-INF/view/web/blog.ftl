<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>${blog.title}-小罗的个人博客主页</title>
    <meta name="keywords" content="${blog.title},小罗的个人博客主页" />
    <meta name="description" content="${blog.title}-小罗的个人博客主页" />
<#include "../common/commonCss.ftl">
</head>
<body>
    <#include "common/header.ftl">
    <div class="container">
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="${rc.contextPath}/index">主页</a></li>
                <li><a href="${rc.contextPath}/blog/blogClass/${blog.blogClassId}/1">${blog.blogClassName}</a></li>
                <li class="active">${blog.title}</li>
            </ol>
            <div class="col-md-8">
                <!-- 博客 -->
                <div class="panel panel-primary">
                    <div class="panel-body" style="text-align: center">
                        <h1>
                           ${blog.title}
                        </h1>
                        <div style="padding: 5px">
                            <span >作者：<a class="author" href="#">${blog.author}</a></span>
                            <span>${blog.createTime}</span>
                        </div>
                        <div style="padding: 5px">
                            <label class="label label-success">${blog.blogType}</label>
                            <label class="label label-warning">${blog.blogClassName}</label>
                            <#if blog.keyWords??>
                                <#list blog.keyWordsArray as kw>
                                    <label class="label label-info">${kw}</label>
                                </#list>
                            </#if>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="featured-media">
                            <#if blog.titleImg??>
                                <a href="">
                                    <img class="img-responsive center-block" src="${blog.titleImg}" alt="${blog.title}">
                                </a>
                            </#if>
                        </div>
                        <div class="post-content">
                            <p>${blog.content}</p>
                        </div>
                    </div>
                </div>
                <!-- 回复 -->
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <h4><b>共有<span style="color: brown">${pager.totalCount}</span>个评论</b></h4>
                        <hr>
                        <!-- 评论列表  -->
                        <#list pager.dataList as reply>
                            <div class="panel panel-default">
                                <div class="panel-heading reply-info">
                                    <span><a class="author" href="#">${reply.owner.nickName}</a></span>
                                    <span class="author reply-link">
                                        <a
                                            <#if Session["loginUser"]??>
                                                    onclick="reply(${blog.id},${reply.owner.id},${reply.id},'${reply.owner.nickName}')"
                                            <#else >
                                                    onclick="login()"
                                            </#if>
                                                class="btn btn-link" href="javascript:void(0);">回复(${reply.childs?size})</a>
                                    </span>
                                    <span>${reply.createTime}</span>
                                </div>
                                <div class="panel-body">
                                    <div>${reply.content}</div>
                                    <#if (reply.childs?size>0)>
                                        <b>--- 共有 ${reply.childs?size} 条评论 ---</b>
                                        <#list reply.childs as rp>
                                            <div style="margin-left: 10px;margin-top: 5px">
                                                <div>
                                                    <div style="display:inline;">
                                                        <a class="author" href="#">${rp.owner.nickName}</a>
                                                        <span>回复</span>
                                                        <a class="author" href="#">${rp.toUser.nickName}</a>
                                                        <span>:</span>
                                                    </div>
                                                    <div style="display:inline;">
                                                        ${rp.content}
                                                    </div>
                                                </div>
                                            </div>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                        <!-- 评论列表分页  -->
                        <#assign path=rc.contextPath+"/blog/get/"+blog.id>
                        <#include "common/pager.ftl">
                        <form id="reply-from">
                            <textarea name="content" style="width:100%;height:150px;"></textarea>
                            <input type="hidden" value="${blog.id}" name="blogId" />
                            <button type="button"
                                    <#if Session["loginUser"]??>
                                        onclick="formReply()"
                                    <#else >
                                        onclick="login()"
                                    </#if>
                                    class="btn btn-primary" style="margin-top: 5px">发表评论</button>
                        </form>
                    </div>
                </div>
            </div>
            <#include "common/rightNav.ftl">
        </div>
    </div>
    <div class="reply-wrap" ><div class="reply"></div></div>
    <div class="login-wrap" >
        <div class="login" style="width: 280px;height: 90px;position:absolute;left:60%;top:55%;">
            <a href='${rc.contextPath}/qq/login' style="margin: auto">
                <img src='http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_5.png'/>
            </a>
        </div>
    </div>
    <#include "../common/commonJs.ftl">
    <#include "common/footer.ftl">
</body>
</html>