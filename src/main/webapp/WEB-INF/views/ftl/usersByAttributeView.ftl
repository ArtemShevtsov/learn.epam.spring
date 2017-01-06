<div id="header">
  <h2>Users By ${attribute} ${attributeName}:</h2>
</div>
<hr>

<div>
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
            <#list usersModel>
                <tr>
                    <#items as u>
                       <td>${u.id}</td>
                       <td>${u.firstName}</td>
                       <td>${u.lastName}</td>
                       <td>${u.birthDay?string("dd/MM/YYYY")}</td>
                       <td>${u.email}</td>
                    </#items>
                </tr>
            </#list>
        </tbody>
    </table>
</div>