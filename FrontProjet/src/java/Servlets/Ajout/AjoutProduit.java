/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Ajout;

import business.model.Produit;
import dao.ProduitDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author p1805797
 */
public class AjoutProduit extends HttpServlet {

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
        ProduitDAO dao = new ProduitDAO();
        RequestDispatcher rd;
        String modif = "";
        int quantite;
        if (request.getParameter("quantite").equals("")){
            rd = request.getRequestDispatcher("/controleur");
            request.setAttribute("todo", "erreurPasQuantité");
            rd.forward(request,response);
        }
        quantite = Integer.parseInt(request.getParameter("quantite"));
        Produit p = new Produit(request.getParameter("nom"),quantite);
        boolean exist = dao.find(p.getNom());
        if (request.getParameter("delete").equals("on")){
            if(dao.delete(p))modif="deleteProduit";else modif="problemeDeleteProduit";            
        } else {
            if (!exist){
                if(dao.insert(p))modif="insertProduit";else modif="problemeInsertProduit";
            } else {
                if(dao.update(p))modif="updateProduit";else modif="problemeUpdateProduit";
            }
        }
        rd = request.getRequestDispatcher("/controleur");
        request.setAttribute("todo", modif);
        rd.forward(request,response);
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
