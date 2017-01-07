<div id="header">
  <h2>User(s) By ${attribute} ${attributeName}:</h2>
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
        <h4>Sorry, there no users(s) with ${attribute} ${attributeName}</h4>
    </#if>
</div>