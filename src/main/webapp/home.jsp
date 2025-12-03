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
        <c:forEach var="post" items="${posts}">
            <li>
                <strong>${post.className} Request for help by ${post.username}</strong>
                <p>${post.description}</p>
                <em>Posted on: ${post.datePosted}</em>

                <!-- Comment form always visible -->
                <form action="comment" method="post">
                    <input type="hidden" name="postId" value="${post.postId}" />
                    <textarea name="commentText" rows="3" cols="30" required></textarea>
                    <button type="submit">Submit</button>
                </form>

                <form action="acceptance" method="post">
                    <input type="hidden" name="postId" value="${post.postId}" />
                    <input type="hidden" name="acceptance" value="true" />
                    <button type="submit">Accept Request</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>