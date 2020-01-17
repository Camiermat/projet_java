<%-- 
    Document   : ajout_produit
    Created on : 17 janv. 2020, 09:39:21
    Author     : p1805797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ajout d'un produit</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form method="post" action ="controleur">
            <input type="hidden" name="todo" value="nouveauProduit"/>
            Nom du produit : <input type="text" name="nom"/>
            <br>
            Quantité : <input type="number" name="quantite"/>
            <br>
            Suppression :<input type="checkbox" name="delete"/>
            <br>
            <input type="submit" value="OK"/>
        </form>
    </body>
</html>
