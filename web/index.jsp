<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="top-title">Товары</div>
        <div id="menu">
            <a href="customer">Customer</a> <a href="seller">Seller</a> <a href="manager">Добавить продукт</a>
        </div>
    <dir id="wrapper">
        Покупатель: 
        <c:forEach var="customer" items="${listPersons.customers}">
            ${customer.firstname} ${customer.lastname}
        </c:forEach>
        <div id="container">
            <ol>
                <c:forEach var="seller" items="${listPersons.sellers}">
                    Продавец: ${seller.firstname} ${seller.lastname}
                    <li>
                        <ul>
                            <c:forEach var="product" items="${seller.products}">
                                <li>
                                    ${product.name} ${product.price} <a href="buy?product_id=${product.id}&seller_id=${seller.id}&customer_id=1">ÐÑÐ¿Ð¸ÑÑ</a>
                                </li>
                            </c:forEach>
                        </ul>

                    </li>
                </c:forEach>
            </ol>
        </div>
    </dir>


