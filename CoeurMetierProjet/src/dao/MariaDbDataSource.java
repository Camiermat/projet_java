/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1805797
 */
public class MariaDbDataSource {
    private static Connection cnx;

    private MariaDbDataSource() throws SQLException {
   }
   
    public static Connection getMariaDbDataSource() {
        if (cnx==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MariaDbDataSource.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cnx = DriverManager.getConnection("jdbc:mysql://iutdoua-web.univ-lyon1.fr:3306/p1812104","p1812104","374922");       
            } catch (SQLException ex) {
                Logger.getLogger(MariaDbDataSource.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        return cnx;
    }
}
