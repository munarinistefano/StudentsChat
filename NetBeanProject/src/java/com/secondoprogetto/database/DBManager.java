/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author ©Povodev
 */

public class DBManager implements Serializable{

    public static Connection con;
    
    public DBManager(String dburl) throws SQLException{
                
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.toString(), e);
        }
        

        Connection con = DriverManager.getConnection(dburl);
        DBManager.con = con;
        
    }
    
    public static void shutdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
        }
    }
}
    