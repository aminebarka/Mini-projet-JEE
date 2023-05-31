<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des opérations</title>
    <link rel="stylesheet" type="text/css" href="styles/list.css">
</head>
<body>
    <h1>Liste des opérations</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Référence</th>
                <th>Nature</th>
                <th>Date</th>
                <th>Notes</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="operation" items="${operations}">
                <tr>
                    <td>${operation.idOperation}</td>
                    <td>${operation.refOperation}</td>
                    <td>${operation.natureOperation}</td>
                    <td>${operation.dateOperation}</td>
                    <td>${operation.notes}</td>
                    <td>
                        <a href="operationServlet?action=edit&idOperation=${operation.idOperation}">Modifier</a>
                        <a href="operationServlet?action=delete&idOperation=${operation.idOperation}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="operationServlet?action=add" method="post">
        <input type="text" name="idOperation" placeholder="ID Opération" required>
        <input type="text" name="refOperation" placeholder="Référence" required>
        <input type="text" name="natureOperation" placeholder="Nature" required>
        <input type="date" name="dateOperation" required>
        <textarea name="notes" placeholder="Notes" required></textarea>
        <button type="submit">Ajouter une opération</button>
    </form>
</body>
</html>
