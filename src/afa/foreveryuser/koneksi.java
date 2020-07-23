/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;
import java.sql.*;

/**
 *
 * @author ADI
 */
public class koneksi {
    private static String url = "jdbc:mariadb://localhost:3306/dbmoviesystem?user=root&password=password";
    private static String driverName = "org.mariadb.jdbc.Driver";
    private static Connection con;
    public static Connection getConnection(){
        try{
            Class.forName(driverName);
            try{
                con = DriverManager.getConnection(url);
                System.out.println("Successed to create db connection");
            } catch(SQLException ex){
                System.out.println("Failed to create db connection");
            }
        } catch(ClassNotFoundException ex){
            System.out.println("Class not found");}
        return con;
    }
    
    public static void main(String[] args) {
        getConnection();
    }
}
