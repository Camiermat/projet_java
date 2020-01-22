<%-- 
    Document   : liste_course
    Created on : 16 janv. 2020, 14:39:05
    Author     : p1805797
--%>

<%@page import="dao.UserDAO"%>
<%@page import="dao.ProduitDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.model.Produit"%>
<%@ include file="WEB-INF/include/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste des produits</title>
    </head>
    <body>
        <%
            String modif = (String)request.getAttribute("modif");
            if(!modif.equals("")){
                request.setAttribute("modif","");
                out.println("<div>"+modif+"</div><br>");
            }
        %>
        Voici votre liste:
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
            <input type="submit" value="retour accueil"/>
        </form>
    </body>
</html>
