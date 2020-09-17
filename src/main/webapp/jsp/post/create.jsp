<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cafe : Create Post</title>
</head>
<body>
<h1>Create Post</h1>

<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
</c:if>

<form action="${pageContext.request.contextPath}/save" method="post">

    <label>Title</label>
    <input type="text" name="title" placeholder="title"/>

    <br/>

    <label>Content</label>
    <textarea name="content"></textarea>

    <br/>
    <br/>

    <input type="submit" value="Save"/>
</form>

</body>
</html>