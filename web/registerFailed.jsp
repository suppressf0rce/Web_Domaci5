<%--
  Created by IntelliJ IDEA.
  User: suppressf0rce
  Date: 4/11/18
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Shop Register</title>
</head>
<body>

<div align="center">
    <h1>Regsiter</h1>

    <form id="login" action="login.jsp"></form>
    <form action="register" method="POST">
        <table>
            <tr>
                <td>Username: </td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password" required /><br></td>
            </tr>

            <tr>
                <td><input type="submit" name="register" value="Register" /></td>
                <td><input type="submit" name="login" value="Login" form="login"/></td>
            </tr>
        </table>

        <h2>Username already taken. Please try again</h2>
    </form>
</div>

</body>
</html>