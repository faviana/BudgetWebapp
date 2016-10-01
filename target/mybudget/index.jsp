<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Finance</title>

    <style> table, th, td {
        border: 1px solid black;
        padding: 5px;
    }table{
         border-spacing: 15px;
     }
    </style>

</head>

<body>

<h1>My Bucket List</h1>
<form method="post" action="/mybudget/budget">

    <table align="center">
        <tr><td>Description</td><td><input type="text" name="description" value="<c:out value="${abudgetlistItem.description}"/>"></td></tr>
        <tr><td>Category</td> <td><input type="text" name="category" value="<c:out value="${abudgetlistItem.category}"/>"></td></tr>
        <tr><td>Budgeted Amount</td> <td><input type="text" name="budgetedAmount" value="<c:out value="${abudgetlistItem.budgetedAmount}"/>"></td></tr>
        <tr><td>ID</td> <td><input type="text" name="id" value="<c:out value="${abudgetlistItem.id}"/>"></td></tr>
    </table>

    <p></p>
</form>

<p></p>

