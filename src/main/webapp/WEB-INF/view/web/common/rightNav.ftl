<div class="col-md-4">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <b>站内检索</b>
        </div>
        <div class="panel-body">
            <form action="${rc.contextPath}/blog/search">
                <div class="input-group">
                    <input name="keyWords" type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        </button>
                    </span>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <b>博客分类</b>
        </div>
        <div class="panel-body">
        <#if commonBlogClass??>
            <#list commonBlogClass as cbs>
                <a href="${rc.contextPath}/blog/blogClass/${cbs.id}/1" class="btn btn-link">
                ${cbs.className}
                </a>
            </#list>
        </#if>
        </div>
    </div>
</div>