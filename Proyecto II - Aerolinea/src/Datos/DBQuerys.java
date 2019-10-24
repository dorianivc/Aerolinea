/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBQuerys {
   public BaseDatosAWS db;
   
   public DBQuerys(){
       db=new BaseDatosAWS();
   }
   
 public void runTestQuery() throws SQLException {
    Connection conn=db.conexion;
    Statement statement = null;
    try {

        System.out.println("Creating statement...");
        statement = conn.createStatement();
        String sql;
        sql = "select * from Aerolinea.Pais; ";
        //STEP 5: Extract data from result set
        try (ResultSet rs = statement.executeQuery(sql)) {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String atributo = rs.getString("pais");
                String atributo2= rs.getString("nombre");
                //Display values
                System.out.println("Informacion Pais de la base de datos en AWS--> " + "Codigo de Pais: "+atributo+ " Nombre: "+ atributo2);
            }
            //STEP 6: Clean-up environment
        }
        statement.close();
        conn.close();
    } catch (SQLException se) {
        //Handle errors for JDBC

    } catch (Exception e) {
        //Handle errors for Class.forName

    } finally {
        //finally block used to close resources
        if (statement != null) {
            statement.close();
        } // nothing we can do
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
        }//end finally try
    }//end try
}
    
}
