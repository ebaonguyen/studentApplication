<!DOCTYPE html>
<html>
<body>

<h2>Create Post</h2>
<h3>Welcome, ${user.firstName}!</h3>
<form action="createpost" method="POST">
    <input type="text" name="description" placeholder="Description" required><br>
    <input type="text" name="className" placeholder="Class" required><br>
    
    <button type="submit">Create Post</button>
</form>

</body>
</html>