/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coeurmetierprojet;

import business.model.Produit;
import dao.ProduitDAO;
import java.util.ArrayList;

/**
 *
 * @author p1805797
 */
public class CoeurMetierProjet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProduitDAO dao = new ProduitDAO();
        ArrayList<Produit> listProduit = dao.findAll();
        for(Produit p : listProduit){
            System.out.println(p);
        }
    }
}
