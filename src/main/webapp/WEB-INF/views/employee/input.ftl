<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../common/header.ftl"/>
    <script src="/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="/js/plugins/validate/messages_cn.js"></script>
    <script>
        var role; //保存删除的角色div

    	$(function() {
    	    //表单校验,必须放在最上面
            $("#editForm").validate({
                rules: {
            <#--编辑操作不需要再校验用户名了-->
                    <#if !entity??>
                        name: {
                            required:true,
                                    rangelength:[2,6],
                                    remote:"/employee/checkName.do"
                        },
                    </#if>
                password: {
                    required:true,
                            rangelength:[2,6]
                },
                repassword: {
                    equalTo:"#pwd"
                },
                email: {
                    required:true,
                            email:true
                },
                age: {
                    required:true,
                            range:[16,70]
                }
            },
            messages: {
                name: {
                    required:"必填"
                }
            }
        });

    	    //超级管理员的特殊处理
            $("#admin").click(function () {
                if (this.checked) {
                    //勾选了超管,删除角色div保存起来
                    role = $("#role").remove();
                } else {
                    //取消超管,恢复角色div
                    $(this).closest("div").after(role);
                }
            });

            //点击保存按钮的事件
            $("#btn_submit").click(function () {
                //自动帮我选中右边所有的option
                $(".selfRoles option").prop("selected", true);
                //再提交表单
                $("#editForm").submit();
            });

    	    //角色去重
            $.each($(".selfRoles option"), function (index, ele) {
                $(".allRoles option[value='" + ele.value + "']").remove();
            });

            <#-- 回显部门和超管 -->
            <#if entity??>
    	        <#-- 回显超级管理员-->
                <#if entity.admin>
                    //当时用户是超管,自动勾上超管选项
                    $("#admin").prop("checked", true);
                    //删除角色的div
                    role = $("#role").remove();
                </#if>
                <#--部门回显-->
                $("#editForm select[name='dept.id']").val(${entity.dept.id});
                <#--用户名改为只读-->
                $("#editForm input[name='name']").prop("readonly", true);
            </#if>
        });
    //列表移动
    function moveAll(src, target) {
        $("."+src+" option").appendTo("."+target);
    }
    function moveSelected(src, target) {
        $("."+src+" option:selected").appendTo("."+target);
    }
</script>
</head>
<body>

<div class="container " style="margin-top: 20px">
<#include "../common/top.ftl"/>
<div class="row">
    <div class="col-sm-3">
        <#assign menu="employee"/>
        <#include "../common/menu.ftl"/>
    </div>
    <div class="col-sm-9">
        <div class="row">
            <div class="col-sm-12">
                <h1 class="page-head-line">员工编辑</h1>
            </div>
        </div>
        <div class="row col-sm-10">
            <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                <input type="hidden" value="${(entity.id)!}" name="id">
                <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">用户名：</label>
                    <div class="col-sm-6">
                    <input type="text" class="form-control" name="name"
                           value="${(entity.name)!}" placeholder="请输入用户名">
                    </div>
                </div>

                <#-- 只有新增操作才显示填写密码-->
                <#if !entity??>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码：</label>
                        <div class="col-sm-6">
                        <input type="password" class="form-control" id="pwd" name="password"  placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                        <div class="col-sm-6">
                        <input type="password" class="form-control" name="repassword" placeholder="再输入一遍密码">
                        </div>
                    </div>
                </#if>
                    
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">Email：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="email"
                               value="${(entity.email)!}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="age"
                               value="${(entity.age)!}" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                        <select class="form-control" name="dept.id">
                            <#list depts as d>
                                <option value="${d.id}" >${d.name}</option>
                            </#list>
                        </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <label class="checkbox-inline" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin">
                        </label>
                    </div>
                    <div class="form-group" id="role">
                        <div>
                            <label for="role" class="control-label" style="margin-left: 60px">角色：</label>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4 col-sm-offset-1">
                                <select multiple class="form-control allRoles" size="15">
                                    <#list roles as r>
                                        <option value="${r.id}">${r.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-sm-2" style="margin-top: 60px;" align="center">
                                <div >
                                    <a type="button" class="btn btn-info  "  style="margin-top: 10px"
                                       onclick="moveSelected('allRoles', 'selfRoles')">&nbsp;&gt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveSelected('selfRoles', 'allRoles')">&nbsp;&lt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('allRoles', 'selfRoles')">&gt;&gt;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('selfRoles', 'allRoles')">&lt;&lt;</a>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <!-- 当前员工所有拥有的角色 -->
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <#if entity??>
                                        <#list entity.roles as r>
                                            <option value="${r.id}">${r.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="submit" class="btn btn-success">
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
</body>
</html>