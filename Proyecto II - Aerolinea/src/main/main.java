/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Datos.BaseDatosAWS;
import Datos.DBQuerys;
import Datos.PaisJpaController;
import Logica.Pais;

/**
 *
 * @author Monica
 */
public class main {
  
    
     public static void main(String[] args) throws Exception {
      DBQuerys db= new DBQuerys();
      db.runTestQuery();
      login.View app= new  login.View();
      app.setLocationRelativeTo(null);
      app.setVisible(true);
    }
       
 
}
