<%-- 
    Document   : index
    Created on : 06.11.2016, 14:52:58
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Магазин</title>
    </head>
    <body>
        <h1>Товары ${title}</h1>
        <div id="container">
            <c:forEach var="product" items="${products}">
                <a href="customer?product_id=${product.id}">${product.name} ${product.price}</a><br>
            </c:forEach>
        </div>
        
    </body>
</html>
