<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire fournisseur</title>
    <link rel="stylesheet" type="text/css" href="styles/form.css">
    
</head>
<body>
    <h1>Formulaire fournisseur</h1>
    <form action="fournisseurServlet" method="post">
        <input type="hidden" name="action" value="${empty fournisseur ? 'add' : 'update'}">
        <input type="hidden" name="idFournisseur" value="${empty fournisseur ? '' : fournisseur.idFournisseur}">
        <label for="nomFournisseur">Nom :</label>
        <input type="text" name="nomFournisseur" value="${empty fournisseur ? '' : fournisseur.nomFournisseur}" required>
        <br>
        <label for="contact">Contact :</label>
        <input type="number" name="contact" value="${empty fournisseur ? '' : fournisseur.contact}" required>
        <br>
        <label for="adresseMail">Adresse email :</label>
        <input type="email" name="adresseMail" value="${empty fournisseur ? '' : fournisseur.adresseMail}" required>
        <br>
        <input type="submit" value="${empty fournisseur ? 'Ajouter' : 'Mettre à jour'}">
    </form>
    <br>
    <a href="fournisseurServlet">Retour à la liste</a>
</body>
</html>
