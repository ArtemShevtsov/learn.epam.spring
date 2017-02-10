<div id="header" style="display: inline-block; width: 100%">
    <#if attribute??>
        <h2 style="float: left;">User(s) By ${attribute} ${attributeName}:</h2>
    <#else>
        <h2 style="float: left;">All Users:</h2>
    </#if>
    <h4 style="float: right;"><a href="/j_spring_security_logout">Log Out</a></h4>
</div>
<hr>

<div>
    <#if usersModel?has_content>
        <#list usersModel>
            <table id="dataTable" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Birthday</th>
                        <th>Email</th>
                    </tr>
                <thead>
                <tbody>
                    <#items as u>
                        <tr>
                           <td>${u.id}</td>
                           <td>${u.firstName}</td>
                           <td>${u.lastName}</td>
                           <td>${u.birthDay?string("dd/MM/YYYY")}</td>
                           <td>${u.email}</td>
                        </tr>
                    </#items>
                </tbody>
            </table>
        </#list>
    <#else>
        <h4>Sorry, there no Users(s) found!</h4>
    </#if>
</div>