/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Pais;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DBQuerys {
   public BaseDatosAWS db;
   
   public DBQuerys(){
       db=new BaseDatosAWS();
   }
   public List<Pais> listadoPaises() throws SQLException{
      Connection conn=db.conexion;
    Statement statement = null;
    List<Pais> lista= new ArrayList<>();
    try {

        System.out.println("Creating statement...");
        statement = conn.createStatement();
        String sql;
        sql = "select * from Aerolinea.Pais; ";
        System.out.println(sql);
        //STEP 5: Extract data from result set
        try (ResultSet rs = statement.executeQuery(sql)) {
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String llave = rs.getString("pais");
                String nombre= rs.getString("nombre");
                //Display values
               Pais pais= new Pais(llave);
               pais.setNombre(nombre);
               lista.add(pais);
            }
            //STEP 6: Clean-up environment
           
        }
        statement.close();
        
        
    } catch (SQLException se) {

        }

    return lista;
       
   }
   
    public int executeUpdate(String statement) {
        try {
            Statement stm = db.conexion.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    
   public void agregarPais(Pais pais) throws SQLException, Exception{
       Connection conn=db.conexion;
       String sql="insert into Aerolinea.Pais (pais, nombre) "+
                "values('%s','%s')";
        sql=String.format(sql,pais.getPais(),pais.getNombre());
        System.out.println(sql);
        int count=executeUpdate(sql);
        if (count==0){
            throw new Exception("Pais ya existe");
        }
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
