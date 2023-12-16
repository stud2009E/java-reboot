<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Калькулятор</title>
</head>
<body>
    <h1>Калькулятор доходности</h1>

    <form action="/finance" method="post">
         Сумма на момент открытия <input name="sum" />
        <br/>
         Процентная ставка <input name="percentage"/>
        <br/>
        Количество лет <input name="years" />
        <br/>
        <input type="submit" value="Посчитать"/>
    </form>
</body>
</html>