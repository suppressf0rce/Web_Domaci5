<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.hibernate.Hibernate" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Web Shop</title>
</head>

<body>
<%
    List<ProductsEntity> products;
    if(request.getParameter("searchRegex")==null)
        products = HibUtils.getAllProducts();
    else
        products = HibUtils.searchProduct(request.getParameter("searchRegex"));

%>
<h1>Web Shop:</h1>
<form action="" >
    <input type="text" name="searchRegex">
    <input type="submit" value="Search">
</form>
<table border="1">
  <tr bgcolor="lightgrey">
    <th>Name</th>
    <th>Price</th>
    <th>&nbsp;</th>
  </tr>
  <%
    for (ProductsEntity p : products) {
  %>
  <form method="GET" action="add_to_cart">
    <tr>
      <td><%=p.getName()%></td>
      <td><%=p.getPrice()%></td>
      <td><input type="text" size="3" name="itemCount" required>
        <input type="hidden" name="itemId" value="<%=p.getId()%>">
        <input type="submit" value="Add"></td>
    </tr>
  </form>
  <%
    }
  %>
</table><br>
<form action="cart.jsp">
    <input type="submit" value="Cart">
</form>

<h3>${message}</h3>
</body>
</html>