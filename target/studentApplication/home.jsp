<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>

<h1>Home Page</h1>
<h2>Welcome, ${user.username}!</h2>

<a href="logout">
    <button type="button">Logout</button>
</a>

<a href="createpost">
    <button type="button">Create Post</button>
</a>

<a href="message">
    <button type="button">Message</button>
</a>

<h2>Recent Posts</h2>
<c:if test="${not empty posts}">
    <ul>
        <c:forEach var="post" items="${posts}" varStatus="status">
            <li>
                <strong>${post.className} Request for help by ${post.username}</strong>
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>