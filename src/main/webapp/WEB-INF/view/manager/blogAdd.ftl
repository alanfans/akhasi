<!-- 内容开始 -->
<section class="content">
    <div class="box box-default">
        <div class="box-body" style="display: block;">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">标题</label>
                        <input id="title" type="text" class="form-control" placeholder="title...">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">关键字(多个用逗号隔开)</label>
                        <input id="keyWords" type="text" class="form-control"  placeholder="keyWords...">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>博客类型</label>
                        <select id="blogType" class="form-control select2 select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                            <option value="原创" selected="selected">原创</option>
                            <option value="转载">转载</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>博客分类</label>
                        <select id="blogClassId" class="form-control select2 select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                            <#if blogClass??>
                                <#list blogClass as bs>
                                    <option value="${bs.id}">${bs.className}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="col-md-12">
                    <label>博客摘要</label>
                    <textarea id="abstracts" class="form-control" rows="3" placeholder="abstracts..."></textarea>
                </div>
                <div id="blog-contetn" class="col-md-12">
                    <label>博客正文</label>
                    <script id="editor" style="width: 100%;height: 300px"></script>
                    <button class="btn btn-primary" style="margin-top: 5px" onclick="publish()">发表博文</button>
                </div>
            </div>
        <div>
</section>
<#include "common/editorCommon.ftl">
<script type="text/javascript">
//    UE.getEditor('editor').destroy();
    var ue = UE.getEditor('editor');
//    UE.getEditor('editor').destroy();
</script>