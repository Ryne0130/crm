<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../common/header.ftl"/>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="role"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">角色编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/role/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" >
                        <label for="name" class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="name"
                               value="${(entity.name)!}" placeholder="请输入角色名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">角色编码：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="sn"
                               value="${(entity.sn)!}" placeholder="请输入角色编码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <label for="role" class="control-label" style="margin-left: 60px">权限：</label>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4 col-sm-offset-1">
                                <#--当前系统中所有的权限-->
                                <select multiple class="form-control allPermissions" size="15">
                                    <#list permissions as p>
                                        <option value="${p.id}">${p.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-sm-2" style="margin-top: 60px;" align="center">
                                <div >
                                    <a type="button" class="btn btn-info  "  style="margin-top: 10px"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">&nbsp;&gt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">&nbsp;&lt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">&gt;&gt;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">&lt;&lt;</a>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <#-- 当前角色拥有的权限-->
                                <select multiple class="form-control selfPermissions" size="15" name="ids">
                                    <#if entity??>
                                        <#list entity.permissions as p>
                                            <option value="${p.id}">${p.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="button" class="btn btn-success">
                                <span class="glyphicon glyphicon-saved"></span> 保存
                            </button>
                            <button type="reset" class="btn btn-default">
                                <span class="glyphicon glyphicon-repeat"></span> 重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        //选项去重
        $.each($(".selfPermissions option"), function (index, ele) {
            $(".allPermissions option[value='"+ele.value+"']").remove();
        });
        //提交表单时,自动选中右边所有的option
        $("#btn_submit").click(function () {
            $(".selfPermissions option").prop("selected", true);
            $("#editForm").submit();
        });
    });

    //移动所有
    function moveAll(src, target) {
        $("."+src+" option").appendTo("."+target);
    }
    //移动被选中
    function moveSelected(src, target) {
        $("."+src+" option:selected").appendTo("."+target);
    }
</script>
</body>
</html>