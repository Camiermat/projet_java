<%-- 
    Document   : ajout_produit
    Created on : 17 janv. 2020, 09:39:21
    Author     : p1805797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Ajout d'un produit</title>
    </head>
    <body>
        Pour ajouter un nouveau produit, veuillez compléter le formulaire suivant :
        <form method="post" action="controleur"><!--Formulaire pour ajouter un produit -->
            <input type="hidden" name="todo" value="nouveauProduit"/>
            Nom du produit : <input type="text" name="nom"/>
            <br>
            Quantité : <input type="number" name="quantite"/>
            <br>
            Si vous voulez supprimer le produit de votre liste : <input type="checkbox" name="delete"/>
            <br>
            <input type="submit" value="Valider"/>
        </form>
    </body>
</html>
