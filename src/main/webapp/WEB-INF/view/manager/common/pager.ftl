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
            <li><a onclick="load('${rc.contextPath}/manager/blog/list/1')" href="#">首页</a></li>
            <li>
                <a onclick="load('${rc.contextPath}/manager/blog/list/${pager.currentPage-1}')" href="#" aria-label="Previous">
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
                <li><a onclick="load('${rc.contextPath}/manager/blog/list/${i}')" href="#">${i}</a></li>
            </#if>
        </#list>
        <#if (cp+1<=tp)>
            <#list cp+1..end as i>
                <li><a onclick="load('${rc.contextPath}/manager/blog/list/${i}')"  href="#">${i}</a></li>
            </#list>
        </#if>
        <#if (pager.currentPage<pager.totalPage)>
            <li>
                <a onclick="load('${rc.contextPath}/manager/blog/list/${pager.currentPage+1}')" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a onclick="load('${rc.contextPath}/manager/blog/list/${tp}')" href="#">尾页</a></li>
        <#else >
            <li class="disabled">
                <span aria-hidden="true">&raquo;</span>
            </li>
            <li class="disabled"><span aria-hidden="true">尾页</span></li>
        </#if>
    </ul>
</nav>
