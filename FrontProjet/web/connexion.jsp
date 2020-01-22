<%-- 
    Document   : connexion
    Created on : 17 janv. 2020, 13:42:06
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
        <h2>Se connecter</h2><!--Formulaire pour se connecter -->
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="connect">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="Entrer">
        </form>
        <br>
        <%
            if((request.getAttribute("erreur").equals("erreurConnection"))){//si il y a une erreur de connection
                request.setAttribute("erreur","");
                out.println("<div>Login ou mot de passe incorrecte</div><br>");
            }
        %>
        <h2>S'inscrire</h2>
        <form method="post" action="controleur"><!--Formulaire pour s'inscrire -->
            <input type="hidden" name="todo" value="inscription">
            Login : <input type="text" name="login">
            Password : <input type="password" name="password">
            <input type="submit" value="S'incrire">
        </form>
        <br>
        <%
            if((request.getAttribute("erreur").equals("erreurInscription"))){//si il y a un problème d'inscription
                request.setAttribute("erreur","");
                out.println("<div>Login déjà utiliser</div><br>");
            }
        %>
    </body>
</html>
