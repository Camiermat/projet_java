/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import static dao.MariaDbDataSource.getMariaDbDataSource;

/**
 *
 * @author p1805797
 */
public abstract interface DAO {
    public Connection connect = getMariaDbDataSource();//attribut public qui stock la connection
}
