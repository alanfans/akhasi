function closeReply(){
    $(".reply-wrap").css("display","none");
}
function closeLogin(){
    $(".login-wrap").css("display","none");
}
function reply(blogId,toUserId,rootId,userName){
    $(".reply").empty();
    var replyForm =
        "<label>你正在回复:<b style='color: orange'>"+userName+"</b></label>"
        +"<form>"
        +"<textarea id='content' name='content' style='width: 100%;height: 200px'></textarea>"
        +"<input id='blogId' type='hidden' value='"+blogId+"' name='blogId' />"
        +"<input id='toUserId' type='hidden' value='"+toUserId+"' name='toUserId' />"
        +"<input id='rootId' type='hidden' value='"+rootId+"' name='rootId' />"
        +"<button type='button' onclick='subReply()' class='btn btn-info' style='margin-top: 5px'>发表评论</button>"
        +"<button type='button' onclick='closeReply()' class='btn btn-info' style='margin-top: 5px;margin-left: 5px'>取消</button>"
        +"</form>";
    $(".reply-wrap").css("display","block");
    $(".reply").append(replyForm)
}

function subReply(){
    var data = {};
    data["blogId"] = $("#blogId").val();
    data["toUserId"] = $("#toUserId").val();
    data["rootId"] = $("#rootId").val();
    data["content"] = $("#content").val();
    $.ajax({
        url: contextPath+'/blog/publishReply',
        dataType: 'json',
        data: data,
        type:"POST",
        success: function(){
            $(".reply-wrap").css("display","none");
            window.location.reload();
        }
    });
}

function formReply(){
    var data = {};
    data["blogId"] = $("#reply-from  [name='blogId']").val();
    data["content"] = $("#reply-from  [name='content']").val();
    $.ajax({
        url: contextPath+'/blog/publishReply',
        dataType: 'json',
        data: data,
        type:"POST",
        success: function(){
            $(".reply-wrap").css("display","none");
            window.location.reload();
        }
    });
}

function login(){
    $(".login-wrap").css("display","block");

}

function toLogin() {
    //以下为按钮点击事件的逻辑。注意这里要重新打开窗口
    //否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口
    var A=window.open("oauth/index.php","TencentLogin", "width=450,height=320,menubar=0,scrollbars=1,resizable=1,status=1,titlebar=0,toolbar=0,location=1");
}
