<%--
  Created by IntelliJ IDEA.
  User: favianalopez
  Date: 10/4/16
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Budget</title>
</head>
<body>
Add a new budget item below:

<form action="/mybudget/create" method="post">
    <table>
        <tr> <td>Description:</td>      <td><input type="text" name="bDescription"></td></tr>
        <tr><td>Category:</td>          <td><input type="text" name="bCategory"></td></tr>
        <tr> <td>Budgeted Amount:</td>  <td><input type="text" name="bB_Amount"></td></tr>
        <tr><td>Actual Amount:</td>     <td><input type="text" name="bA_Amount"></td></tr>

    </table>
    <input type="submit" name="save"/>
</form>
</body>
</html>