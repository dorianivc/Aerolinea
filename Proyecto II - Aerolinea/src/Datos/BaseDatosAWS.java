/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import static com.mysql.cj.conf.PropertyKey.PORT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDatosAWS {
    public Connection conexion;
    
       public BaseDatosAWS(){
        System.out.println("----MySQL JDBC Connection Testing -------");
          try {
        Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                    return ;
                }
        System.out.println("MySQL JDBC Driver Registered!");
        this.conexion=null;
         try {
        conexion = DriverManager.
                getConnection("jdbc:mysql://" + "ec2-54-208-33-122.compute-1.amazonaws.com" + ":" +"3306" + "/" + "sys", "aerolinea", "progra3");
        } catch (SQLException e) {
        System.out.println("Connection Failed!:\n" + e.getMessage());
            }
    
           
           
    }
    
    

}
