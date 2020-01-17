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
        switch(todo){
            case "connect":
                if(!(Objects.equals(request.getParameter("login"), "on")&&Objects.equals(request.getParameter("password"), "on"))){
                    User u = daoUser.findU(request.getParameter("login"));
                    exist = u.getProprietaire()!=-1;
                    if(exist){
                        rd = request.getRequestDispatcher("accueil.jsp");
                        request.setAttribute("todo", "");
                        session.setAttribute("name",request.getParameter("login"));
                        session.setAttribute("first","1");
                        rd.forward(request,response);
                    } else {
                        rd = request.getRequestDispatcher("connexion.jsp");
                        request.setAttribute("todo", "");
                        request.setAttribute("erreur", "erreurConnection");
                        rd.forward(request,response);
                    }
                }
                break;
            case "inscription":
                if(!(Objects.equals(request.getParameter("login"), "on")&&Objects.equals(request.getParameter("password"), "on"))){
                    User newUser = daoUser.findU(request.getParameter("login"),request.getParameter("password"));
                    exist = newUser.getProprietaire()!=-1;
                    if(exist){
                        rd = request.getRequestDispatcher("connexion.jsp");
                        request.setAttribute("todo", "");
                        request.setAttribute("erreur", "erreurInscription");
                        rd.forward(request,response);
                    } else {
                        daoUser.insert(new User(daoUser.count()+1,request.getParameter("login"),request.getParameter("password")));
                        rd = request.getRequestDispatcher("accueil.jsp");
                        session.setAttribute("name",request.getParameter("login"));
                        session.setAttribute("first","1");
                        request.setAttribute("todo", "");
                        rd.forward(request,response);
                    }
                }
                break;
            case "choixPage":
                if(Objects.equals(request.getParameter("ajouterProduit"), "on")){
                    rd = request.getRequestDispatcher("ajout_produit.jsp");
                    request.setAttribute("todo", "");
                    rd.forward(request,response);
                }
                if(Objects.equals(request.getParameter("consult"), "on")){
                    rd = request.getRequestDispatcher("liste_course.jsp");
                    request.setAttribute("todo", "");
                    rd.forward(request,response);
                }
                rd = request.getRequestDispatcher("accueil.jsp");
                rd.forward(request,response);
            case "nouveauProduit":
                String modif = "";
                int quantite;
                if (request.getParameter("quantite").equals("")){
                    request.setAttribute("modif", "erreurPasQuantité");
                } else {
                    session.setAttribute("first", "2");
                    quantite = Integer.parseInt(request.getParameter("quantite"));
                    Produit p = new Produit(daoUser.findU((String)session.getAttribute("name")).getProprietaire(),request.getParameter("nom"),quantite);
                    exist = daoProduit.find(p.getNom(),daoUser.findU((String)session.getAttribute("name")).getProprietaire());
                    if (Objects.equals(request.getParameter("delete"), "on")){
                        if(daoProduit.delete(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire()))modif="Le produit "+request.getParameter("nom")+" a été supprimer.";            
                    } else {
                        if (!exist){
                            if(daoProduit.insert(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire()))modif="Le produit "+request.getParameter("nom")+" a été ajouter.";
                        } else {
                            if(daoProduit.update(p,daoUser.findU((String)session.getAttribute("name")).getProprietaire()))modif="Le produit "+request.getParameter("nom")+" a été modifier. Nouvelle quantité :"+quantite;
                        }
                    }
                }
                System.out.println(modif);
                rd = request.getRequestDispatcher("accueil.jsp");
                request.setAttribute("modif", modif);
                rd.forward(request,response);
                break;
            default:
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
                break;
        }
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
