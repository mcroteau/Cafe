<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="parakeet" uri="/META-INF/tags/parakeet.tld"%>

<html>
<head>
    <title>Cafe : Posts</title>
</head>
<body>

<h1>Latest Posts</h1>

<c:if test="${posts != null || posts.size() > 0}">
    <div id="latest-posts">
        <c:forEach var="post" items="${posts}">
            <div class="post-container">
                <h2>${post.title}</h2>
                <span class="tiny">${post.date}</span>
                <div>${post.content}
                    <a href="${pageContext.request.contextPath}/edit?id=${post.id}">Edit</a>
                </div>

            </div>
        </c:forEach>

    </div>
</c:if>
<c:if test="${posts == null || posts.size() == 0}">
    <p>No posts created yet.
        <parakeet:isAuthenticated>
            <a href="${pageContext.request.contextPath}/create">Create</a>
        </parakeet:isAuthenticated>
    </p>
</c:if>
</body>
</html>
