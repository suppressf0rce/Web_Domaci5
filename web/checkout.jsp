<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%@ page import="model.ProductsEntity" %><%--
  Created by IntelliJ IDEA.
  User: suppressf0rce
  Date: 4/19/18
  Time: 7:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>

<%
    if(session.getAttribute("userId") == null)
        response.sendRedirect("login.jsp");

    if(session.getAttribute("cartItems") == null) {
%>

<h1>Your cart is empty!</h1>

<%
}else{
    if(((ConcurrentHashMap<ProductsEntity, Integer>)session.getAttribute("cartItems")).size() <= 0){
%>

<h1>Your cart is empty!</h1>

<%
}
else if (session.getAttribute("userId") != null){
    session.setAttribute("cartItems",null);
%>

<h1>You have shopped the items!</h1>

<%
        }
    }
%>

<form action="index.jsp">
    <input type="submit" value="Back To SHOPPING!">
</form>

</body>
</html>
