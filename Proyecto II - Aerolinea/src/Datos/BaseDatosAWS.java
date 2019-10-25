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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BaseDatosAWS {
    public Connection conexion;
    public  EntityManagerFactory EntityManager=null;
    
    
    private  EntityManager createEntityManager(){
        try{
        if(EntityManager ==null){
            EntityManager=Persistence.createEntityManagerFactory("Proyecto_II_-_AerolineaPU");
        }
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
        
        return EntityManager.createEntityManager();
    }
    
    
    
    
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
                getConnection("jdbc:mysql://" + "ec2-54-208-33-122.compute-1.amazonaws.com" + ":" +"3306" + "/" + "Aerolinea", "aerolinea", "progra3");
             System.out.println("jdbc:mysql://" + "ec2-54-208-33-122.compute-1.amazonaws.com" + ":" +"3306" + "/" + "Aerolinea");
        } catch (SQLException e) {
        System.out.println("Connection Failed!:\n" + e.getMessage());
            }
         try{
             EntityManager=(EntityManagerFactory) createEntityManager();
         }catch(Exception se){
             System.out.println(se.getMessage());
         }
         }
   
    
    

}
