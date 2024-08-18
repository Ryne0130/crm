<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
<script src="/js/plugins/jqueryForm/jquery.form.js"></script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#-- 往数据模型中填充一个属性 -->
            <#assign menu="employee"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">员工管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字:</label>
                    <input type="text" class="form-control" name="keyword"
                           value="${(qo.keyword)!}" placeholder="请输入姓名/邮箱">
                </div>
                <div class="form-group">
                    <label for="dept">部门:</label>
                    <select class="form-control" id="dept" name="deptId">
                        <option value="-1">全部</option>
                        <#list depts as d>
                            <option value="${d.id}">${d.name}</option>
                        </#list>
                    </select>
                    <script>
                        $("#dept").val(${qo.deptId});
                    </script>
                </div>
                <button id="btn_query" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
                <@shiro.hasPermission name="employee:input">
                    <a href="/employee/input.do" class="btn btn-success">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </@shiro.hasPermission>
                <a role="button" class="btn btn-danger batchDelete">
                    <span class="glyphicon glyphicon-trash"></span> 批量删除
                </a>
                <a role="button" class="btn btn-warning exportExcel">
                    <span class="glyphicon glyphicon-export"></span> 导出
                </a>
                <a role="button" class="btn btn-warning importExcel">
                    <span class="glyphicon glyphicon-import"></span> 导入
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th><input type="checkbox" id="all"></th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>email</th>
                    <th>年龄</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.list as entity>
                    <tr>
                        <td><input type="checkbox" class="ck" data-eid="${entity.id}"></td>
                        <td>${entity_index + 1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.email}</td>
                        <td>${entity.age}</td>
                        <td>${entity.dept.name}</td>
                        <td>
                            <@shiro.hasPermission name="employee:input">
                                <a class="btn btn-info btn-xs" href="/employee/input.do?id=${entity.id}">
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="employee:delete">
                                <a data-url="/employee/delete.do?id=${entity.id}" class="btn btn-danger btn-xs deleteBtn">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </@shiro.hasPermission>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>

<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">导入员工</h4>
            </div>
            <div class="modal-body">
                <#--用到文件上传,设置表单二进制编码-->
                <form class="form-horizontal" action="/employee/imporExcel.do"
                      method="post" id="editForm" enctype="multipart/form-data">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">上传文件：</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" name="xls"
                                accept="application/vnd.ms-excel">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">参考模板：</label>
                        <div class="col-sm-6">
                            <a href="/template/employee.xls" class="btn btn-success btn-block">
                                <span class="glyphicon glyphicon-download"></span> 下载模板
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <span class="glyphicon glyphicon-off"></span> 关闭
                </button>
                <button type="button" class="btn btn-primary importBtn">
                    <span class="glyphicon glyphicon-saved"></span> 保存
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    //禁用jQuery在数组中自动加[]的功能
    $.ajaxSettings.traditional = true;

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

        //全选和全不选
        $("#all").click(function () {
            $(".ck").prop("checked", this.checked);
        });

        //自动操作#all
        $(".ck").click(function () {
            if ($(".ck:checked").size() == $(".ck").size()) {
                $("#all").prop("checked", true);
            } else {
                $("#all").prop("checked", false);
            }
        });

        //批量删除按钮绑定点击事件
        $(".batchDelete").click(function () {
            //1:当一个都没有勾选的时,给予提示
            if (!$(".ck:checked").size()) {
                $.messager.alert("温馨提示", "至少要选中1个");
                return;
            }
            //2:已经勾选了,给予确认操作的提示
            $.messager.confirm("温馨提示", "确定要批量删除吗", function () {
                //3:拿到需要删除的id
                var ids = $.map($(".ck:checked"), function (ele) {
                    return $(ele).data("eid");
                });
                //4:发送ajax请求
                $.post("/employee/batchDelete.do", {ids: ids}, function (data) {
                    reload();
                }, "json");
            });
        });

        //导出Excel
        $(".exportExcel").click(function() {
            //地址栏跳转到导出资源的界面
            location.href = "/employee/exportExcel.do";
        });

        //导入Excel
        $(".importExcel").click(function() {
            //弹出模态框
            $("#importModal").modal("show");
        });

        //上传员工确定按绑定点击事件
        $(".importBtn").click(function() {
            $("#editForm").submit();
        });
    });
</script>
</body>
</html>