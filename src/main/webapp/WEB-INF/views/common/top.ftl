<style>
     .lg2 {
        position: absolute;
        top: 26px;
        left: 150px;
    }
</style>
<div class="navbar cm-navbar">
    <img class="logo" alt="Brand" src="/images/logo.png">
    <img src="/images/logo1.png" class="lg2">
    <span class="pageTitle">&nbsp;</span>
    <ul class="nav navbar-nav navbar-right cm-navbar-nav ">
        <li>
           <p class="navbar-text text-info">
                <span class="glyphicon glyphicon-user"></span>
	            <#--从主身份对象去name属性的值-->
                <@shiro.principal property="name" />
           </p>
        </li>
        <li><a href="/logout.do">安全退出</a></li>
    </ul>
</div>