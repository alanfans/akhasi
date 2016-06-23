<#list pager.dataList as blog>
<div class="panel panel-primary">
    <div class="panel-body" style="text-align: center">
        <h1>
            <a href="${rc.contextPath}/blog/get/${blog.id}">${blog.title}</a>
        </h1>
        <div style="padding: 5px">
            <span >作者：<a href="#">${blog.author}</a></span>
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
        <div class="post-content">
            <div>
            ${blog.contentDesc}
            </div>
        </div>
        <div>
            <a href="${rc.contextPath}/blog/get/${blog.id}" class="btn btn-primary">阅读全文</a>
        </div>
    </div>
</div>
</#list>