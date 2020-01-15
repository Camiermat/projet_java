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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author p1805797
 */
public class ProduitDAO implements DAO<Produit>{

    @Override
    public ArrayList<Produit> findAll() {
        ArrayList<Produit> list = new ArrayList();
        try {
            Statement stmt = connect.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Produit");
            while(result.next()){
                list.add(new Produit(result.getString(1),result.getInt(2)));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }       
        return list;
    }

    @Override
    public boolean find(String nom) {
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM Produit WHERE Nom=?");
            prepare.setString(1, nom);
            ResultSet result = prepare.executeQuery();
            if(result.next())return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Produit p) {
        try{
            PreparedStatement prepare = connect.prepareStatement("INSERT INTO Produit VALUES (?,?)");
            prepare.setString(1, p.getNom());
            prepare.setInt(2,p.getQuantité());
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Produit p) {
        try{
            PreparedStatement prepare = connect.prepareStatement("UPDATE Produit SET Quantité=? where Nom=?");
            prepare.setInt(1,p.getQuantité());
            prepare.setString(2, p.getNom());
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Produit p) {
        try{
            PreparedStatement prepare = connect.prepareStatement("DELETE FROM Produit where Nom=?");
            prepare.setString(1, p.getNom());
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
