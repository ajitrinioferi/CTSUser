/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;

import java.sql.*;
import java.util.logging.*; 


/**
 *
 * @author ADI
 */
public class AFAUtils {
    public static Connection conn = koneksi.getConnection();
    private static Statement st = null;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
    
    
    public static ResultSet Select(String qry){
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            return rs;
        } catch (SQLException e){
           LOGGER.log(Level.WARNING, "SQL Select Exception : ", e);
        }
        return null;
    }
}
