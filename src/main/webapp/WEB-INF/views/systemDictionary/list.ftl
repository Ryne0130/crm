<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#include "../common/header.ftl"/>
    <script src="/js/plugins/jqueryForm/jquery.form.js"></script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#-- 往数据模型中填充一个属性 -->
            <#assign menu="systemDictionary"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">字典目录管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/systemDictionary/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a role="button" class="btn btn-success inputBtn">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>字典标题</th>
                    <th>字典编号</th>
                    <th>字典介绍</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.list as entity>
                    <tr>
                       <td>${entity_index + 1}</td>
                       <td>${entity.title}</td>
                       <td>${entity.sn}</td>
                       <td>${entity.intro}</td>
                       <td>
                           <#--当前按钮中绑定自定义属性,内容是当前数据的JSON字符串-->
                           <a class="btn btn-info btn-xs inputBtn" role="button" data-json='${entity.json}'>
                               <span class="glyphicon glyphicon-pencil"></span> 编辑
                           </a>
                           <a data-url="/systemDictionary/delete.do?id=${entity.id}" class="btn btn-danger btn-xs deleteBtn">
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

<!-- 模态框必须作为body的子标签 -->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/systemDictionary/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">字典标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title"
                                   value="${(entity.title)!}" placeholder="请输入字典标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">字典编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sn"
                                   value="${(entity.sn)!}" placeholder="请输入字典编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">字典介绍：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="intro"
                                   value="${(entity.intro)!}" placeholder="请输入字典介绍">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <span class="glyphicon glyphicon-off"></span> 关闭
                </button>
                <button type="button" class="btn btn-primary submitBtn">
                    <span class="glyphicon glyphicon-saved"></span> 保存
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        //找到新增和编辑按钮绑定点击事件
        $(".inputBtn").click(function () {
            //1:清空模态框中的数据
            $("#editForm input").val("");
            //2:获取当前按钮中data-json的值
            var json = $(this).data("json");
            if (json) { //只有编辑按钮才有JSON对象,才需要做回显
               $("#editForm input[name='id']").val(json.id);
               $("#editForm input[name='title']").val(json.title);
               $("#editForm input[name='sn']").val(json.sn);
               $("#editForm input[name='intro']").val(json.intro);
            }
            //3:显示模态框
            $("#inputModal").modal("show");
        });

        //把当前表单提交当时改为ajax提交
        $("#editForm").ajaxForm(function (data) {
            reload();
        });
        
        //给表单提交按钮绑定点击事件
        $(".submitBtn").click(function () {
            $("#editForm").submit();
        });
        
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