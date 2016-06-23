window.onload = function(){
    var leftBarc = $("#left-bar").children();
    leftBarc.each(function(index,element){
        $(element).click(function(){
            leftBarc.each(function(index,ele){
                $(ele).removeAttr("class");
            });
            $(element).attr("class","active");
        });
    })
};
function load(url){
    var $cw = $("#content-div");
    $cw.empty();
    $cw.load(url);
}

function deleteBlog(blogId){
    var data = {};
    data["blogId"] = blogId;
    $.ajax({
        url: contextPath+'/manager/blog/delete',
        dataType: 'json',
        data: data,
        type:"POST",
        success: function(){
            alert("删除成功");
            window.location.reload();
        },
        error:function(){
            alert("删除失败");
        }
    });
}

//获得编辑器内容
function getContent() {
    return UE.getEditor('editor').getContent();
}
function publish(){
    var data = {};
    data['title'] = $("#title").val();
    data['blogType'] = $("#blogType").val();
    data['blogClassId'] = $("#blogClassId").val();
    data['abstracts'] = $("#abstracts").val();
    data['content'] = getContent();
    data['keyWords'] = $("#keyWords").val();
        //alert(JSON.stringify(data));
    //alert(contextPath+'/manager/blog/add');
    $.ajax({
        url: contextPath+'/manager/blog/add',
        dataType: 'json',
        data: data,
        type:"POST",
        success: function(){
            alert("发表成功");
            window.location.reload();
        },
        error:function(){
            alert("发表失败");
        }
    });
}