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