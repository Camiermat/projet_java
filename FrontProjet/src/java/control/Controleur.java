/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import business.model.Produit;
import business.model.User;
import dao.ProduitDAO;
import dao.UserDAO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author p1805797
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String todo = request.getParameter("todo");
        RequestDispatcher rd;
        ProduitDAO daoProduit = new ProduitDAO();
        UserDAO daoUser = new UserDAO();
        boolean exist;
        HttpSession session = request.getSession();
        switch(todo){ //switch pour faire les redirections
            case "connect": //pour vérifier la connection sur le site
                if(!(Objects.equals(request.getParameter("login"), "on")&&Objects.equals(request.getParameter("password"), "on"))){
                    User u = daoUser.findU(request.getParameter("login"), request.getParameter("password"));
                    exist = u.getProprietaire()!=-1;
                    if(exist){ //connection réussi, redirection vers accueil.jsp
                        rd = request.getRequestDispatcher("accueil.jsp");
                        request.setAttribute("todo", "");
                        session.setAttribute("name",request.getParameter("login"));//définition des variables de session
                        session.setAttribute("first","1");
                        rd.forward(request,response);
                    } else {//erreur de connection, redirection vers connexion.jsp
                        rd = request.getRequestDispatcher("connexion.jsp");
                        request.setAttribute("todo", "");
                        request.setAttribute("erreur", "erreurConnection");
                        rd.forward(request,response);
                    }
                }
                break;
            case "inscription":// pour vérifier l'inscription sur le site
                if(!(Objects.equals(request.getParameter("login"), "on")&&Objects.equals(request.getParameter("password"), "on"))){
                    User newUser = daoUser.findU(request.getParameter("login"));
                    exist = newUser.getProprietaire()!=-1;
                    if(exist){//nom d'utilisateur déjà utiliser, redirection vers connexion.jsp
                        rd = request.getRequestDispatcher("connexion.jsp");
                        request.setAttribute("todo", "");
                        request.setAttribute("erreur", "erreurInscription");
                        rd.forward(request,response);
                    } else {//inscription réussi, redirection vers accueil
                        daoUser.insert(new User(daoUser.count()+1,request.getParameter("login"),request.getParameter("password")));
                        rd = request.getRequestDispatcher("accueil.jsp");
                        session.setAttribute("name",request.getParameter("login"));//définition des variables de session
                        session.setAttribute("first","1");
                        request.setAttribute("todo", "");
                        rd.forward(request,response);
                    }
                }
                break;
            case "choixPage"://sur la page d'accueil, pour savoir ce que l'utilisateur à choisit
                if(Objects.equals(request.getParameter("deconnexion"), "on")){// si il choisit déconnection, on retourne vers la page d'accueil
                    rd = request.getRequestDispatcher("index.jsp");
                    session.setAttribute("name","");//suppression des variables de session
                    session.setAttribute("first","");
                    request.setAttribute("todo", "");
                    rd.forward(request,response);
                } else {
                    if((Objects.equals(request.getParameter("ajouterProduit"), "on")) && (Objects.equals(request.getParameter("consult"), "on"))){//si l'utilisateur choisit et l'ajout et la liste, o fait d'abord l'ajout, puis on affiche la liste
                        rd = request.getRequestDispatcher("ajout_produit.jsp");
                        request.setAttribute("todo", "");
                        session.setAttribute("next","on");//pour dire que l'utilisateur veut consulter la liste
                        rd.forward(request,response);
                    } else {
                        if(Objects.equals(request.getParameter("ajouterProduit"), "on")){//si l'utilisateur veut ajouter un produit, redirection vers la page d'ajout
                            rd = request.getRequestDispatcher("ajout_produit.jsp");
                            request.setAttribute("todo", "");
                            session.setAttribute("next","off");
                            rd.forward(request,response);
                        } else {
                            if(Objects.equals(request.getParameter("consult"), "on")){//si l'utilisateur veut consulter sa liste, redirection vers la liste
                                rd = request.getRequestDispatcher("liste_course.jsp");
                                request.setAttribute("todo", "");
                                request.setAttribute("modif","");
                                rd.forward(request,response);
                            } else {//si problème, redirection vers accueil
                                rd = request.getRequestDispatcher("accueil.jsp");
                                rd.forward(request,response);
                            }
                        }
                    }
                }
            case "nouveauProduit"://lorsque l'utilisateur veut ajouter un produit
                String modif = "";
                String retourPage;
                session.setAttribute("first", "2");
                if ((Objects.equals(request.getParameter("quantite"), null))||(Objects.equals(request.getParameter("quantite"), ""))){//si il n'a pas entrer de quantité
                    exist = daoProduit.find(request.getParameter("nom"),daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                    if (exist){//si le nom existe
                        if (Objects.equals(request.getParameter("delete"), "on")){//si il veut supprimer, on supprime
                            daoProduit.delete(request.getParameter("nom"),daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                            modif="Le produit "+request.getParameter("nom")+" a été supprimer.";
                        } else {//sinon problème car pas de quantité
                            modif = "La quantité n'a pas été saisie";
                        }
                    } else {//si le nom n'existe pas et que pas de quantité
                        modif = "Le produit n'existe pas et aucune quantité n'a été rentrée";
                    }
                } else {//si il y a une quantité
                    int quantite = Integer.parseInt(request.getParameter("quantite"));
                    Produit p = new Produit(daoUser.findU((String)session.getAttribute("name")).getProprietaire(),request.getParameter("nom"),quantite);
                    exist = daoProduit.find(p.getNom(),daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                    if (!exist){//si le produit n'existe pas
                        if (Objects.equals(request.getParameter("delete"), "on")){//si on veut supprimer, erreur
                            daoProduit.delete(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                            modif="Le produit "+request.getParameter("nom")+" n'existe pas.";            
                        } else {//sinon on insert
                            daoProduit.insert(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                            modif="Le produit "+request.getParameter("nom")+" a été ajouter.";
                        }
                    } else {//si le produit existe
                        if (Objects.equals(request.getParameter("delete"), "on")){//si on veut le supprimer, on le supprime
                            daoProduit.delete(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                            modif="Le produit "+request.getParameter("nom")+" a été supprimer.";            
                        } else {//sinon on update
                            daoProduit.update(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                            modif="Le produit "+request.getParameter("nom")+" a été modifier. Nouvelle quantité :"+quantite;
                        }
                    }
                }
                if(Objects.equals(session.getAttribute("next"),"on")){//si l'utilisateur veut aller sur sa liste
                    retourPage = "liste_course.jsp";//redirection vers la liste
                    request.setAttribute("todo", "");
                } else {//sinon
                    retourPage = "accueil.jsp";//redirection vers accueil
                }
                request.setAttribute("modif", modif);
                rd = request.getRequestDispatcher(retourPage);
                rd.forward(request,response);
                break;
            case "retourAccueil"://sur la liste, lorsque l'on a fini de la consulter, on retourne sur accueil
                rd = request.getRequestDispatcher("accueil.jsp");
                rd.forward(request,response);
                break;
            default://en cas de problème, on sort du site
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
                break;
        }
        return;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
