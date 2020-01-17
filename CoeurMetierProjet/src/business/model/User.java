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
public class User {
    private int proprietaire;
    private String login;
    private String password;

    public User(int proprietaire, String login, String password) {
        this.proprietaire = proprietaire;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getProprietaire() {
        return proprietaire;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", proprietaire=" + proprietaire + '}';
    }
    
}
