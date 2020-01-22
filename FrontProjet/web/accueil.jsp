<%-- 
    Document   : index
    Created on : 17 janv. 2020, 08:59:58
    Author     : p1805797
--%>

<%@page import="java.util.Objects"%>
<%@ include file="WEB-INF/include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste de course</title>
    </head>
    <body>
        <h2>Bonjour <%=request.getSession().getAttribute("name")%></h2>
        <br>
        <%
            if(!(request.getSession().getAttribute("first").equals("1"))){
                if (!Objects.equals(request.getAttribute("modif"), null)){
                    out.println("<div>"+(String)request.getAttribute("modif")+"</div><br>");
                    request.setAttribute("modif","");
                }
            }
        %>
        <h2>Quels actions souhaitez-vous réaliser ?</h2>
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="choixPage"/>
            Ajouter un produit<input type="checkbox" name="ajouterProduit"/>
            <br>
            Consulter ma liste de course<input type="checkbox" name="consult"/>
            <br>
            Deconnexion<input type="checkbox" name="deconnexion"/>
            <input type="submit" value="Poursuivre"/>
        </form>
    </body>
</html>
