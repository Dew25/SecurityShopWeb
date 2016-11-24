<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
    <div id="top-title">Управление товарами</div>
      
    Продукт для продавца: 
    <sellect id="seller-sellect" name="seller_sellect">
        <c:forEach var="sell" items="${sellers}">
            <option value="${sell.id}">${sell.firstname} ${sell.lastname}</option>
        </c:forEach>
    </sellect>
        <form action="addProduct" method="POST">
            Наименование продукта: <input type="text" name="name" value=""><br>
            Цена продукта: <input type="text" name="price" value=""><br>
            <input type="submit" value="Добавить">
        </form>
    <div id="container">
        <c:forEach var="seller" items="${listPersons.sellers}">
        <ol>
            <li>Продавец: ${seller.firstname} ${seller.lastname}
                <ul>
                    <c:forEach var="product" items="${seller.products}">
                        <li>
                            ${product.name} ${product.price} <a href="deleteProduct?product_id=${product.id}&seller_id=getSellerId()&customer_id=1">Удалить</a>
                        </li>
                    </c:forEach>
                </ul>

            </li>
        </ol>
        </c:forEach>
    </div>
    <c:set var="fileNameJavaScript" scope="page" value="manager.js"/>