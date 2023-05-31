<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire opération</title>
    <link rel="stylesheet" type="text/css" href="styles/form.css">
</head>
<body>
    <h1>Formulaire opération</h1>
    <form action="operationServlet" method="post">
        <input type="hidden" name="action" value="${empty operation ? 'add' : 'update'}">
        <input type="hidden" name="idOperation" value="${empty operation ? '' : operation.idOperation}">
        <label for="refOperation">Référence :</label>
        <input type="text" name="refOperation" value="${empty operation ? '' : operation.refOperation}" required>
        <br>
        <label for="natureOperation">Nature :</label>
        <input type="text" name="natureOperation" value="${empty operation ? '' : operation.natureOperation}" required>
        <br>
        <label for="dateOperation">Date :</label>
        <input type="date" name="dateOperation" value="${empty operation ? '' : operation.dateOperation}" required>
        <br>
        <label for="notes">Notes :</label>
        <textarea name="notes" required>${empty operation ? '' : operation.notes}</textarea>
        <br>
        <input type="submit" value="${empty operation ? 'Ajouter' : 'Mettre à jour'}">
    </form>
    <br>
    <a href="operationServlet">Retour à la liste</a>
</body>
</html>
