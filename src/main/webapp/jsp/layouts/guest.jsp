<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="parakeet" uri="/META-INF/tags/parakeet.tld"%>

<html>
<head>
    <title>Cafe : Adore it</title>

    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">

    <!-- CSS Reset -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">

    <!-- Milligram CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/milligram/1.4.1/milligram.css">

    <style type="text/css">
        body{
            padding:20px;
        }
    </style>

    <decorator:head />

</head>
<body>

<parakeet:anonymous>
    <a href="${pageContext.request.contextPath}/signin">Signin</a>
</parakeet:anonymous>

<parakeet:isAuthenticated>
    <parakeet:username/> | <a href="${pageContext.request.contextPath}/logout">Logout</a> |
    <a href="${pageContext.request.contextPath}/create">Create</a>
</parakeet:isAuthenticated>

<decorator:body />

</body>
</html>