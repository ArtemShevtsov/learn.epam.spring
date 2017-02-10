<div id="header" style="display: inline-block; width: 100%">
    <h4 style="float: right;"><a href="/j_spring_security_logout">Log Out</a></h4>
</div>
<hr>
<form method="post" enctype="multipart/form-data" action="/upload">
    <div>
        <label for="users-upload">Upload JSON file with Users</label>
        <input type="file" name="users" id="users-upload">
    </div>

    <div>
        <label for="events-upload">Upload JSON file with Events</label>
        <input type="file" name="events" id="events-upload">
    </div>

    <input type="submit">
</form>