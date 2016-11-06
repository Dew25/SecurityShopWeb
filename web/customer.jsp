<%-- 
    Document   : customer
    Created on : 06.11.2016, 19:46:30
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупатель</title>
    </head>
    <body>
        <h1>Покупки</h1>
        
        Покупатель: ${customer.firstname} ${customer.lastname}<br>
        <form action="/updateCash" method="POST" id="form-cash">
            Количество денег: <input type="text" id="input-cash" value="${customer.cash}"/>
            <input type="submit" value="Изменить"/>
        </form>
        <form action="clearOwnCustomer" method="POST" id="form-clear">
            <br>
            <c:forEach var="ownCustomer" items="${customer.ownsCustomer}">
                ${ownCustomer.name} ${ownCustomer.price}<br>
            </c:forEach>
                <input type="submit" id="clearOwnsCustomer" value="Очистить"/>
        </form>
    
    </body>
</html>
