/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model;

/**
 *
 * @author p1805797
 */
public class Produit {
    private String nom;
    private int quantité;

    public Produit(String nom, int quantité) {
        this.nom = nom;
        this.quantité = quantité;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom=" + nom + ", quantit\u00e9=" + quantité + '}';
    }
}
