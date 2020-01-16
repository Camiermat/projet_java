/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        switch(todo){
            case "choixPage":
                if(request.getParameter("ajouterProduit").equals("on")){
                    rd = request.getRequestDispatcher("/controleur");
                    request.setAttribute("todo", "nouveauProduit");
                    rd.forward(request,response);
                }
                if(request.getParameter("consult").equals("on")){
                    rd = request.getRequestDispatcher("/controleur");
                    request.setAttribute("todo", "listeCourse");
                    rd.forward(request,response);
                }
                break;
            case "nouveauProduit":
                rd = request.getRequestDispatcher("ajoutProduit");
                rd.forward(request,response);
                break;
            case "listeCourse":
                rd = request.getRequestDispatcher("listeProduit");
                rd.forward(request,response);
                break;
            case "erreurPasQuantité":
                break;
            case "deleteProduit":
                break;
            case "problemeDeleteProduit":
                break;
            case "insertProduit":
                break;
            case "problemeInsertProduit":
                break;
            case "updateProduit":
                break;
            case "problemeUpdateProduit":
                break;
            default:
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
