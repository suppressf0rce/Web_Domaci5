<%@ page import="model.ProductsEntity" %>
<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: suppressf0rce
  Date: 4/19/18
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<h1>Cart:</h1>


  <%

    if(session.getAttribute("cartItems")!=null){
    ConcurrentHashMap<ProductsEntity, Integer> cart = (ConcurrentHashMap<ProductsEntity, Integer>) session.getAttribute("cartItems");
    List<ProductsEntity> products = new ArrayList<>();

    for (Map.Entry<ProductsEntity, Integer> entry : cart.entrySet()) {
		products.add(entry.getKey());
	}


	%>
<table border="1">
    <tr bgcolor="lightgrey">
        <th>Name</th>
        <th>Price per Unit</th>
        <th>Number of Units</th>
        <th>Total</th>
        <th>&nbsp</th>
    </tr>
    <%

    double total = 0;
    for (ProductsEntity p : products) {
        total += p.getPrice()*cart.get(p);
  %>
    <form method="GET" action="remove_from_cart">
        <tr>
            <td><%=p.getName()%></td>
            <td><%=p.getPrice()%></td>
            <td><%=cart.get(p)%></td>
            <td><%=cart.get(p)*p.getPrice()%></td>
            <td><input type="text" size="3" name="itemCount" required>
                <input type="hidden" name="itemId" value="<%=p.getId()%>">
                <input type="submit" value="Remove"></td>
        </tr>
    </form>
    <%
        }
    }else{
        session.setAttribute("message", "Your cart is empty");
        %>
        <h3>${message}</h3>
        <%
        session.setAttribute("message", null);
    }
  %>
</table>
            <form action="index.jsp">
                <input type="submit" value="Back">
            </form>
            <form action="checkout.jsp">
                <input type="submit" value="Checkout">
            </form>

</body>
</html>
