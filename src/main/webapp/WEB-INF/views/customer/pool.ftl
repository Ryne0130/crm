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
            <#assign menu="customerPool"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">客户池</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/customerPool/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字:</label>
                    <input type="text" class="form-control" name="keyword"
                           value="${(qo.keyword)!}" placeholder="请输入姓名/电话">
                </div>
                <button id="btn_query" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>QQ</th>
                    <th>职业</th>
                    <th>来源</th>
                    <th>营销人员</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.age}</td>
                        <td>${entity.genderName}</td>
                        <td>${entity.tel}</td>
                        <td>${entity.qq}</td>
                        <td>${entity.job.title}</td>
                        <td>${entity.source.title}</td>
                        <td>${entity.seller.name}</td>
                        <td>
                            <#--<a class="btn btn-info btn-xs inputBtn" role="button" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a class="btn btn-success btn-xs statusBtn" role="button" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-plane"></span> 修改状态
                            </a>-->
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

});
</script>
</body>
</html>