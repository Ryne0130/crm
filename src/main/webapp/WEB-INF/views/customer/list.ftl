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
            <#assign menu="customer"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">客户管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/customer/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字:</label>
                    <input type="text" class="form-control" name="keyword"
                           value="${(qo.keyword)!}" placeholder="请输入姓名/电话">
                </div>
                <div class="form-group">
                    <label for="dept">状态:</label>
                    <select class="form-control" name="status">
                        <option value="-1">全部</option>
                        <option value="0">潜在客户</option>
                        <option value="2">开发失败</option>
                        <option value="3">流失</option>
                    </select>
                    <script>
                        $("#searchForm select[name='status']").val(${qo.status});
                    </script>
                </div>
                <button id="btn_query" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
                <a role="button" class="btn btn-success inputBtn">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
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
                    <th>状态</th>
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
                        <td>${entity.statusName}</td>
                        <td>
                            <a class="btn btn-info btn-xs inputBtn" role="button" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a class="btn btn-success btn-xs statusBtn" role="button" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-plane"></span> 修改状态
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>

<#-- 编辑客户模态框 -->
<div class="modal fade" id="customerModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customer/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id"/>
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">姓名：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="name" placeholder="请输入客户姓名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">年龄：</label>
                        <div class="col-lg-6">
                            <input type="number" class="form-control" name="age" placeholder="请输入客户年龄"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">性别：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="gender">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">电话：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="tel" placeholder="请输入客户电话"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">QQ：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control"name="qq" placeholder="请输入客户QQ"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">职业：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="job.id">
                                <#list jobs as j>
                                    <option value="${j.id}">${j.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">来源：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="source.id">
                                <#list sources as s>
                                    <option value="${s.id}">${s.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary customerSubmitBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<#-- 修改客户状态 -->
<div class="modal fade" id="statusModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改客户状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customer/updateStatus.do" method="post" id="statusForm">
                    <input type="hidden" name="id" />
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">客户姓名：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="name" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">客户状态：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="status">
                                <option value="0">潜在客户</option>
                                <option value="1">客户池</option>
                                <option value="2">开发失败</option>
                                <option value="3">流失</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary statusSubmitBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<script>
$(function () {
    //找到添加和编辑按钮绑定点击事件
    $(".inputBtn").click(function () {
        //1:清除模态框中的数据
        $("#editForm input").val("");
        //2:获取当前对象中绑定的JSON
        var json = $(this).data("json");
        if (json) {
            //数据回显
            $("#editForm input[name='id']").val(json.id);
            $("#editForm input[name='name']").val(json.name);
            $("#editForm input[name='age']").val(json.age);
            $("#editForm input[name='tel']").val(json.tel);
            $("#editForm input[name='qq']").val(json.qq);
            $("#editForm select[name='gender']").val(json.gender);
            $("#editForm select[name='job.id']").val(json.jobId);
            $("#editForm select[name='source.id']").val(json.sourceId);
        }
        //3:显示模态框
        $("#customerModal").modal("show");
    });

    $(".statusBtn").click(function () {
        var json = $(this).data("json");
        $("#statusForm input[name='id']").val(json.id);
        $("#statusForm input[name='name']").val(json.name);
        $("#statusModal").modal("show");
    });

    //设置表单的提交事件
    $(".customerSubmitBtn").click(function () {
        $("#editForm").ajaxSubmit(function (data) {
            reload();
        });
    });
    $(".statusSubmitBtn").click(function () {
        $("#statusForm").ajaxSubmit(function (data) {
            reload();
        });
    });
});
</script>
</body>
</html>