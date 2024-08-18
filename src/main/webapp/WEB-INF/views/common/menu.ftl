<ul id="menu" class="list-group">
	<li class="list-group-item">
		<a role="button" data-toggle="collapse" data-target="#system_detail">
			<span>系统管理</span>
		</a>
		<ul class="in" id="system_detail">
			<li class="department"><a href="/department/list.do">部门管理</a></li>
            <li class="employee"><a href="/employee/list.do">员工管理</a></li>
			<@shiro.hasRole name="admin">
            	<li class="permission"><a href="/permission/list.do">权限管理</a></li>
			</@shiro.hasRole>
            <li class="role"><a href="/role/list.do">角色管理</a></li>
		</ul>
		<a role="button" data-toggle="collapse" data-target="#customer_detail">
			<span>客户管理</span>
		</a>
		<ul class="in" id="customer_detail">
			<li class="customer"><a href="/customer/list.do">客户信息</a></li>
			<li class="customerTraceHistory"><a href="/customerTraceHistory/list.do">跟进历史</a></li>
			<li class="customerTransfer"><a href="/customerTransfer/list.do">移交历史</a></li>
			<li class="formalCustomer"><a href="/formalCustomer/list.do">正式客户</a></li>
			<li class="customerPool"><a href="/customerPool/list.do">客户池</a></li>
		</ul>
		<a role="button" data-toggle="collapse" data-target="#dictionary_detail">
			<span>数据字典管理</span>
		</a>
		<ul class="in" id="dictionary_detail">
			<li class="systemDictionary"><a href="/systemDictionary/list.do">字典目录管理</a></li>
            <li class="systemDictionaryItem"><a href="/systemDictionaryItem/list.do">字典明细管理</a></li>
		</ul>
		<a role="button" data-toggle="collapse" data-target="#chart_detail">
			<span>报表</span>
		</a>
		<ul class="in" id="chart_detail">
			<li class="chart"><a href="/chart/list.do">潜在客户报表</a></li>
		</ul>
	</li>
</ul>

<script type="text/javascript">
    $(".in li.${menu}").addClass("active");
</script>
