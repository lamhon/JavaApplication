/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.*;

/**
 *
 * @author hoang
 */
public class DBContext {
    Connection con = null;
    public Connection getConnectDB(){
        try {
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JavaApp";
            String user = "javaApp";
            String pass = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
            //System.out.print("Connect DB success!");
        } catch (Exception e) {
            System.out.print("Connect error!");
        }
        return con;
    }
}
