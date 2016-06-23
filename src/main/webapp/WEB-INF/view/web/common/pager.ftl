<nav>
    <#assign cp=pager.currentPage>
    <#assign tp=pager.totalPage>
    <#assign start=1>
    <#assign end=cp+5>
    <#assign num=cp-5>
    <#if (num>0)>
        <#assign start=start+num>
    </#if>
    <#if (end>tp)>
        <#assign end=tp>
    </#if>
    <ul class="pagination">
        <#if pager.currentPage!=1>
            <li><a href="${path}/1">首页</a></li>
            <li>
                <a href="${path}/${pager.currentPage-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        <#else >
            <li class="disabled"><span aria-hidden="true">首页</span></li>
            <li class="disabled">
                <span aria-hidden="true">&laquo;</span>
            </li>
        </#if>
        <#list start..cp as i>
            <#if i = pager.currentPage>
                <li class="active">
                    <span>${i}<span class="sr-only">(current)</span></span>
                </li>
            <#else>
                <li><a href="${path}/${i}">${i}</a></li>
            </#if>
        </#list>
        <#if (cp+1<=tp)>
            <#list cp+1..end as i>
                <li><a  href="${path}/${i}">${i}</a></li>
            </#list>
        </#if>
        <#if (pager.currentPage<pager.totalPage)>
            <li>
                <a href="${path}/${pager.currentPage+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a href="${path}/${tp}">尾页</a></li>
        <#else >
            <li class="disabled">
                <span aria-hidden="true">&raquo;</span>
            </li>
            <li class="disabled"><span aria-hidden="true">尾页</span></li>
        </#if>
    </ul>
</nav>