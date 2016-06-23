<table class="table table-hover">
    <tbody>
    <tr>
        <th>id</th>
        <th>author</th>
        <th>createTime</th>
        <th>readCount</th>
        <th>title</th>
        <th>blogType</th>
        <th>content</th>
        <th>type</th>
        <th>keyWords</th>
        <th>操作</th>
    </tr>
    <#list pager.dataList as blog>
    <tr>
        <td style="vertical-align:middle;">
            <#if blog.id??>
                <div>${blog.id}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.author??>
                <div>${blog.author}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.createTime??>
                <div>${blog.createTime}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.readCount??>
                <div>${blog.readCount}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.title??>
                <div>${blog.title}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.blogType??>
                <div>${blog.blogType}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.content??>
                <div style="width:200px;height:20px;overflow: hidden">${blog.content}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.blogClassName??>
                <div>${blog.blogClassName}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <#if blog.keyWords??>
                <div>${blog.keyWords}</div>
            </#if>
        </td>
        <td style="vertical-align:middle;">
            <button class="btn btn-primary" style="width: 100%">查看/修改</button>
            <button class="btn btn-danger" style="width: 100%;margin-top: 5px" onclick="deleteBlog(<#if blog.id??>${blog.id}</#if>)">删除</button>
        </td>
    </tr>
    </#list>
    </tbody>
</table>
<#include "common/pager.ftl">