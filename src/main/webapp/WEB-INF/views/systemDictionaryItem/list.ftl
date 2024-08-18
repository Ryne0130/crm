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
            <#assign menu="systemDictionaryItem"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">字典明细管理</h1>
                </div>
            </div>

            <#--提交数据的表单-->
            <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <#--隐藏parentId,并且做回显-->
                <input type="hidden" name="parentId" value="${qo.parentId}">
            </form>

            <#--右边显示界面分为2份-->
            <div class="row">
                <#--显示所有的字典目录-->
                <div class="col-sm-4">
                    <ul id="menu" class="list-group">
                        <li class="list-group-item">
                            <a role="button" data-toggle="collapse">
                                <span>字典目录</span>
                            </a>
                            <ul class="dictionary">
                                <#list dictionaries as d>
                                    <li data-did="${d.id}"><a role="button">${d.title}</a></li>
                                </#list>
                            </ul>
                        </li>
                    </ul>
                </div>
                <#--显示当前字典的明细,有pageInfo时才显示-->
                <div class="col-sm-8">
                    <#if pageInfo??>
                        <a role="button" class="btn btn-success inputBtn">
                            <span class="glyphicon glyphicon-plus"></span> 添加明细
                        </a>
                        <table class="table table-striped table-hover" >
                            <tr>
                                <th>编号</th>
                                <th>明细标题</th>
                                <th>明细序号</th>
                                <th>操作</th>
                            </tr>
                            <#list pageInfo.list as entity>
                                <tr>
                                    <td>${entity_index + 1}</td>
                                    <td>${entity.title}</td>
                                    <td>${entity.sequence}</td>
                                    <td>
                                        <a class="btn btn-info btn-xs inputBtn" role="button" data-json='${entity.json}'>
                                            <span class="glyphicon glyphicon-pencil"></span> 编辑
                                        </a>
                                        <a data-url="/systemDictionaryItem/delete.do?id=${entity.id}" class="btn btn-danger btn-xs deleteBtn">
                                            <span class="glyphicon glyphicon-trash"></span> 删除
                                        </a>
                                    </td>
                                </tr>
                            </#list>
                         </table>
                         <#include "../common/page.ftl"/>
                     </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#-- 模态框必须作为body的子标签 -->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">

                    <div class="form-group" >
                        <label class="col-sm-4 control-label">字典标题：</label>
                        <div class="col-sm-6">
                            <#--隐藏目录ID-->
                            <input type="hidden" name="parentId" >
                            <input type="text" class="form-control" name="parentTitle" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">明细标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title"
                                   value="${(entity.title)!}" placeholder="请输入明细标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">明细序列：</label>
                        <div class="col-sm-6">
                            <input type="number" class="form-control" name="sequence"
                                   value="${(entity.sequence)!}" placeholder="请输入明细序列">
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
               $("#editForm input[name='sequence']").val(json.sequence);
                // 回显当前字典ID
               $("#editForm input[name='parentId']").val(json.parentId);
            }
            //3:显示模态框
            $("#inputModal").modal("show");
            //4:回显当前字典目录
            var parentTitle = $(".dictionary li.active a").html();
            $("#editForm input[name='parentTitle']").val(parentTitle);
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

        //给字典目录绑定点击事件
        $(".dictionary li").click(function () {
            //取出设置的字典目录的id,存入表单中,然后提交表单
            var parentId = $(this).data("did");
            $("#searchForm input[name='parentId']").val(parentId);
            $("#searchForm").submit();
        });

        //回显字典目录
        $(".dictionary li[data-did='${qo.parentId}']").addClass("active");
    });
</script>
</body>
</html>