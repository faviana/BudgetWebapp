<%--
  Created by IntelliJ IDEA.
  User: favianalopez
  Date: 10/5/16
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Budget</title>
</head>
<body>

Update your budget and click Save!

<form action="/mybudget/update" method="post">
    <table>

        <tr> <td>Description:</td>      <td><input type="text" name="bDescription" value ="<c:out value="${myUpdate.description}"/>"></td></tr>
        <tr><td>Category:</td>          <td><input type="text" name="bCategory" value ="<c:out value="${myUpdate.category}"/>"></td></tr>
        <tr> <td>Budgeted Amount:</td>  <td><input type="text" name="bB_Amount" value ="<c:out value="${myUpdate.budgetedAmount}"/>"></td></tr>
        <tr><td>Actual Amount:</td>     <td><input type="text" name="bA_Amount" value ="<c:out value="${myUpdate.actualAmount}"/>"></td></tr>

    </table>
    <input type="submit" name="Update"/>
    <input type="hidden" name="bId" value="<c:out value="${myUpdate.id}"/>"/>
</form>
</body>
</html>

