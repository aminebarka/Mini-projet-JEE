<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des fournisseurs</title>
    <link rel="stylesheet" type="text/css" href="styles/list.css">
    
</head>
<body>
    <h1>Liste des fournisseurs</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Contact</th>
                <th>Adresse email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="fournisseur" items="${fournisseurs}">
                <tr>
                    <td>${fournisseur.idFournisseur}</td>
                    <td>${fournisseur.nomFournisseur}</td>
                    <td>${fournisseur.contact}</td>
                    <td>${fournisseur.adresseMail}</td>
                    <td>
                        <a href="fournisseurServlet?action=edit&idFournisseur=${fournisseur.idFournisseur}">Modifier</a>
                        <a href="fournisseurServlet?action=delete&idFournisseur=${fournisseur.idFournisseur}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="fournisseurServlet?action=add" method="post">
        <input type="text" name="idFournisseur" placeholder="ID Fournisseur" required>
        <input type="text" name="nomFournisseur" placeholder="Nom Fournisseur" required>
        <input type="text" name="contact" placeholder="Contact" required>
        <input type="text" name="adresseMail" placeholder="Adresse Email" required>
        <button type="submit">Ajouter un fournisseur</button>
    </form>
</body>
</html>
