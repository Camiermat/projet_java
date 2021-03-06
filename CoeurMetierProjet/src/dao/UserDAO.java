/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.model.User;
import static dao.DAO.connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author p1805797
 */
public class UserDAO implements DAO{

    public boolean find(String login) {//recherche d'un utilisateur avec son identifiant, retour un boolean
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM User WHERE login=?");
            prepare.setString(1, login);
            ResultSet result = prepare.executeQuery();
            if(result.next())return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public User findU(String login) {//recherche d'un utilisateur avec son identifiant, retour un User si trouver
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM User WHERE login=?");
            prepare.setString(1, login);
            ResultSet result = prepare.executeQuery();
            if(result.next())return new User(result.getInt(1),result.getString(2),result.getString(3));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new User(-1,"","");
    }
    
    public User findU(String login, String password) {//recherche d'un utilisateur avec son identifiant et son mot de passe, retour un User si trouver
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM User WHERE login=? and password=?");
            prepare.setString(1, login);
            prepare.setString(2, password);
            ResultSet result = prepare.executeQuery();
            if(result.next())return new User(result.getInt(1),result.getString(2),result.getString(3));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new User(-1,"","");
    }

    public boolean insert(User c) {//insertion d'un nouveau utilisateur 
        try{
            PreparedStatement prepare = connect.prepareStatement("INSERT INTO User (`login`, `password`) VALUES (?,?)");
            prepare.setString(1, c.getLogin());
            prepare.setString(2,c.getPassword());
            int result = prepare.executeUpdate();
            if(result==1)return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean find(User u) {//recherche d'un utilisateur avec un utilisateur, retour un boolean
        try{
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM User WHERE login=? AND password=?");
            prepare.setString(1, u.getLogin());
            prepare.setString(2, u.getPassword());
            ResultSet result = prepare.executeQuery();
            if(result.next())return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public int count() {//retourne le nombre d'utlisateur
        try{
            Statement stmt = connect.createStatement();
            ResultSet result = stmt.executeQuery("SELECT count(*) FROM User");
            if(result.next())return result.getInt(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
