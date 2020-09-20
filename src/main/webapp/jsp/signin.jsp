<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cafe : Signin</title>
</head>
<body>

<h1>Signin</h1>

<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
</c:if>

<form action="${pageContext.request.contextPath}/auth" method="post">

    <label>Username</label>
    <input type="text" name="username" placeholder="Username"/>

    <br/>

    <label>Password</label>
    <input type="text" name="password" placeholder="Password ***"/>

    <br/>
    <br/>

    <input type="submit" value="Signin"/>
</form>

</body>
</html>
