<%-- 
    Document   : liste_course
    Created on : 16 janv. 2020, 14:39:05
    Author     : p1805797
--%>

<%@page import="dao.UserDAO"%>
<%@page import="dao.ProduitDAO"%>
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
                ProduitDAO dao = new ProduitDAO();
                UserDAO daoU = new UserDAO();
                ArrayList<Produit> listProduit = dao.findAll((daoU.findU((String)request.getSession().getAttribute("name"))).getProprietaire());
                for (Produit p : listProduit){
                    out.println("<li>"+p.getNom()+" : "+p.getQuantité()+"</li>");
                }
            %>
        </ul>
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="retourAccueil"/>
            <imput type="submit" value="retour accueil">
        </form>
    </body>
</html>
