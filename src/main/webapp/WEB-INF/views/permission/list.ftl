<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="permission"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">权限管理</h1>
                </div>
            </div>
            <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a role="button" class="btn btn-success reload">
                    <span class="glyphicon glyphicon-refresh"></span> 重新加载权限
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>权限名称</th>
                    <th>权限表达式</th>
                </tr>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.expression}</td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
<script>
    $(function() {
        $(".reload").click(function() {
            $.messager.confirm("温馨提示", "加载权限需要大量时间,确定要加载吗", function() {
                $.get("/permission/reload.do", function(data) {
                    reload();
                }, "json");
            });
        });
    });
</script>
</body>
</html>