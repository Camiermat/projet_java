<%-- 
    Document   : liste_course
    Created on : 16 janv. 2020, 14:39:05
    Author     : p1805797
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="business.model.Produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste des produits</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <ul>
            <%
                ArrayList<Produit> listeProduit = (ArrayList<Produit>)request.getAttribute("listeProduit");
                for (Produit p : listeProduit){
            %><li>
                <%=p.getNom()+" : "+p.getQuantité()%>
              </li>
            <%
                }
            %>
        </ul>
    </body>
</html>
