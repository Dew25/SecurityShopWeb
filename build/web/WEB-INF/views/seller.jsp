
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div id="top-title">Продавец</h1></div>
        <div id="menu">
            <a href="toIndex">Главная</a>
        </div>
        <div id="wrapper">
        <!--Вывести список покупателей, чтобы передать в ссылку идентификфтор покупателя-->
            <c:forEach var="sell" items="${sellers}">
                ${sell.firstname} ${sell.lastname}, продавец: ${sell.nameShop}, денег: ${sell.profit},  
                <br>товары: <br>
                <c:forEach var="product" items="${sell.products}">
                    ${product.name} ${product.price} <a href="buy?product_id=${product.id}&seller_id=${sell.id}&customer_id=1">Купить</a><br>
                </c:forEach>
                <br>
            </c:forEach>
        </div>

