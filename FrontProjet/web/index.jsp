<%-- 
    Document   : connexion
    Created on : 17 janv. 2020, 09:54:48
    Author     : p1805797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        Se connecter
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="connect">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="Entrer">
        </form>
        <br>
        S'inscrire
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="inscription">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="S'incrire">
        </form>
    </body>
</html>