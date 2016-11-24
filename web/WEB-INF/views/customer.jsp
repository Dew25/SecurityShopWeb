<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div id="top-title">Покупки</div>
        <div id="menu">
            <a href="toIndex">Главная</a>
        </div>
<div id="wrapper">
            <c:forEach var="customer" items="${customers}">
                ${customer.firstname} ${customer.lastname}, денег: ${customer.cash},  
                <br>покупки: 
                <c:forEach var="own" items="${customer.owns}">
                    <br>${own.name} ${own.price} <a href="customer?own_id=${own.id}">Удалить</a>
                </c:forEach>
                <br>
            </c:forEach>
               
</div>
                