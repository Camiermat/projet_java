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
            String modif = (String)request.getAttribute("modif");//si l'utilisateur à modifier un produit avant d'aller sur la liste, on affiche la modification
            if(!modif.equals("")){
                request.setAttribute("modif","");
                out.println("<div>"+modif+"</div><br>");
            }
        %>
        <h2>Voici votre liste de course :</h2>
        <ul>
            <%
                ProduitDAO dao = new ProduitDAO();
                UserDAO daoU = new UserDAO();
                ArrayList<Produit> listProduit = dao.findAll((daoU.findU((String)request.getSession().getAttribute("name"))).getProprietaire());
                if (listProduit.size()==0){//on affiche la liste des produits, et si il n'y en a pas, on affiche un message
                    out.println("Vous n'avez encore aucun produit dans votre liste");
                } else {
                    for (Produit p : listProduit){
                        out.println("<li>"+p.getNom()+" : "+p.getQuantite()+"</li>");
                    }
                }
            %>
        </ul>
        <form method="post" action="controleur"><!--Formulaire pour revenir sur l'accueil -->
            <input type="hidden" name="todo" value="retourAccueil"/>
            Pour revenir sur la page d'accueil : <input type="submit" value="retour accueil"/>
        </form>
    </body>
</html>
