<!DOCTYPE html>
<html>
<body>

<h2>Create Account</h2>

<form action="register" method="POST">
    <input type="text" name="first_name" placeholder="First Name" required><br>
    <input type="text" name="last_name" placeholder="Last Name" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="text" name="username" placeholder="Username" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <input type="text" name="major" placeholder="Major" ><br>
    <input type="number" name="school_year" placeholder="School Year" value = "0"><br>

    <button type="submit">Register</button>
</form>

</body>
</html>