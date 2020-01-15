/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.model;

import java.sql.Connection;
import java.util.ArrayList;
import static business.model.MariaDbDataSource.getMariaDbDataSource;

/**
 *
 * @author p1805797
 */
public abstract interface DAO<T> {
    public Connection connect = getMariaDbDataSource();
    
    public abstract ArrayList<T> findAll();
}
