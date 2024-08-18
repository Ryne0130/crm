<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#-- 往数据模型中填充一个属性 -->
            <#assign menu="role"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">角色管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/role/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a href="/role/input.do" class="btn btn-success inputBtn">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>角色名称</th>
                    <th>角色编号</th>
                    <th>操作</th>
                </tr>
                <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                       <td>${entity_index + 1}</td>
                       <td>${entity.name}</td>
                       <td>${entity.sn}</td>
                       <td>
                           <#--当前按钮中绑定自定义属性,内容是当前数据的JSON字符串-->
                           <a class="btn btn-info btn-xs" href="/role/input.do?id=${entity.id}">
                               <span class="glyphicon glyphicon-pencil"></span> 编辑
                           </a>
                           <a data-url="/role/delete.do?id=${entity.id}" class="btn btn-danger btn-xs deleteBtn">
                               <span class="glyphicon glyphicon-trash"></span> 删除
                           </a>
                       </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
<script>
    $(function () {
        //找到删除按钮,绑定点击事件
        $(".deleteBtn").click(function () {
            //获取当前对象的data-url的值
            var url = $(this).data("url");
            //弹出对话框,确实是否要删除
            $.messager.confirm("温馨提示", "亲,真的要删除吗", function () {
                //发送ajax请求删除数据
                $.get(url,function (data) {
                    reload();
                });
            });
        });
    });
</script>
</body>
</html>