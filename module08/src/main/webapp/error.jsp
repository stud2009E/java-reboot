<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE HTML>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>${title}</title>
</head>

<html>
    <a href="/">Домой</a>
    <h1>${title}</h1>
    <br>
    <c:forEach items="${requestScope.messages}" var="msg">
        <p>${msg}</p>
    </c:forEach>
</html>