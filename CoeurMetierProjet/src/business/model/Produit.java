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
    private int id;
    private String nom;
    private int quantité;

    public Produit(int id, String nom, int quantité) {
        this.id = id;
        this.nom = nom;
        this.quantité = quantité;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantité() {
        return quantité;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", quantit\u00e9=" + quantité + '}';
    }

}
