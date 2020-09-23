<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cafe : Edit Post</title>
</head>
<body>
<h1>Edit Post</h1>

<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
</c:if>

<form action="${pageContext.request.contextPath}/update" method="post">
    <input type="hidden" name="id" value="${post.id}"/>

    <label>Title</label>
    <input type="text" name="title" placeholder="title" value="${post.title}"/>

    <br/>

    <label>Content</label>
    <textarea name="content">${post.content}</textarea>

    <br/>
    <br/>

    <input type="submit" value="update"/>
</form>


<form action="${pageContext.request.contextPath}/delete" method="post">
    <input type="hidden" name="id" value="${post.id}"/>
    <input type="submit" value="Delete"/>
</form>


</body>
</html>
