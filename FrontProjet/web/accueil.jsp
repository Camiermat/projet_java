<%-- 
    Document   : index
    Created on : 17 janv. 2020, 08:59:58
    Author     : p1805797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste de course</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Bienvenue <%=request.getSession().getAttribute("name")%></h1>
        <br>
        <%
            if(!(request.getSession().getAttribute("first").equals("1"))){
                String modif = (String)request.getAttribute("modif");
                if (!modif.equals("")){
                    request.setAttribute("modif","");
                    out.println("<div>"+modif+"</div><br>");
                }
            }
        %> 
        <form method="post" action="controleur">
            <input type="hidden" name="todo" value="choixPage"/>
            Ajouter un produit<input type="checkbox" name="ajouterProduit"/>
            <br>
            Consulter ma liste de course<input type="checkbox" name="consult"/>
            <br>
            <input type="submit" value="Poursuivre"/>
            <br>
            <input type="submit" value="deconnexion">
        </form>
    </body>
</html>
