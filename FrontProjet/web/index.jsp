<%--
    Document   : connexion
    Created on : 17 janv. 2020, 09:54:48
    Author     : p1805797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Connexion</title>
    </head>
    <body>
        <h2>Se connecter</h2>
        <form method="post" action="controleur"><!--Formulaire pour se connecter -->
            <input type="hidden" name="todo" value="connect">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="Entrer">
        </form>
        <br>
        <h2>S'inscrire</h2>
        <form method="post" action="controleur"><!--Formulaire pour s'inscrire -->
            <input type="hidden" name="todo" value="inscription">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="S'incrire">
        </form>
    </body>
</html>
