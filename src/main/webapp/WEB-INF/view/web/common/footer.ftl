<footer class="footer navbar-inverse">
    <div class="container">
        <div class="navbar-collapse collapse navbar-text navbar-right">
            <p class="text-right">天猫的个人博客 | Copyright©2016 | 蜀ICP备16007831号</p>
        </div>
    </div>
</footer>
<script type="text/javascript">
    //设置底部置底
    var footerInit = function() {
        var windowH = $(window).height();
        var headerH = $("body>.navbar").height();
        var footerH = $("footer").height();
        var $mainBody = $("body>.container");
        $mainBody.css({
            "min-height": (windowH - headerH - footerH -22) + "px"
        });
    }
    footerInit();
    window.onresize = function () {
        footerInit();
    };
</script>