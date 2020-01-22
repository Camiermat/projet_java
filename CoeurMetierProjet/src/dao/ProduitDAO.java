/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.model.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author p1805797
 */
public class ProduitDAO implements DAO{

    public boolean find(String nom, int id) {
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM Produit WHERE Nom=? AND proprietaire=?");
            prepare.setString(1, nom);
            prepare.setInt(2, id);
            ResultSet result = prepare.executeQuery();
            if(result.next())return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Produit p, int id) {
        try{
            PreparedStatement prepare = connect.prepareStatement("INSERT INTO Produit VALUES (?,?,?)");
            prepare.setInt(1,id);
            prepare.setString(2, p.getNom());
            prepare.setInt(3,p.getQuantité());
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Produit p, int id) {
        try{
            PreparedStatement prepare = connect.prepareStatement("UPDATE Produit SET Quantité=? where Nom=? and proprietaire=?");
            prepare.setInt(1,p.getQuantité());
            prepare.setString(2, p.getNom());
            prepare.setInt(3, id);
            int result = prepare.executeUpdate();
            if(result>=1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Produit p, int id) {
        try{
            PreparedStatement prepare = connect.prepareStatement("DELETE FROM Produit where Nom=? and proprietaire=?");
            prepare.setString(1, p.getNom());
            prepare.setInt(2,id);
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(String nom, int id) {
        try{
            PreparedStatement prepare = connect.prepareStatement("DELETE FROM Produit where Nom=? and proprietaire=?");
            prepare.setString(1, nom);
            prepare.setInt(2,id);
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Produit> findAll(int id) {
        ArrayList<Produit> list = new ArrayList();
        try {
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM Produit WHERE proprietaire=?");
            prepare.setInt(1,id);
            ResultSet result = prepare.executeQuery();
            while(result.next()){
                list.add(new Produit(result.getInt(1),result.getString(2),result.getInt(3)));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }       
        return list;
    }
}
