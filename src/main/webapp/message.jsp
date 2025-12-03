<!DOCTYPE html>
<html>
<body>

<h2>Create Post</h2>
<h3>Welcome, ${user.firstName}!</h3>
<form action="message" method="POST">
    <input type="text" name="message" placeholder="Message" required><br>
    <input type="text" name="receiverName" placeholder="Receiver Username" required><br>
    <button type="submit">Create Post</button>
</form>

</body>
</html>