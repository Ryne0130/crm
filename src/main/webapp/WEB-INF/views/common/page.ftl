<div style="text-align: center;">
    <nav>
        <ul id="page" class="pagination"></ul>
    </nav>
</div>
<script type="text/javascript">
    $(function(){
        $("#page").twbsPagination({
            //这里填充数据使用的是PageInfo
            startPage: ${pageInfo.pageNum} || 1,
            totalPages: ${pageInfo.pages} || 1,
            visiblePages: 5,
            first:"首页",
            prev:"上页",
            next:"下页",
            last:"尾页",
            onPageClick:function(event, page){
                //讲当前页设置page值
                $("#currentPage").val(page);
                //表单提交
                $("#searchForm").submit();
            }
        });
    });
</script>