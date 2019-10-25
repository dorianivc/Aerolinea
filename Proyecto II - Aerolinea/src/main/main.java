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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monica
 */
public class main {
  
    
     public static void main(String[] args) throws SQLException, Exception {
      DBQuerys db= new DBQuerys();
      List<Pais> lista= db.listadoPaises();
     for(int i=0;i<lista.size();i++){
              Pais pais= lista.get(i);
              System.out.println(pais.toString());
      }
     PaisJpaController daoPais= new PaisJpaController(db.db.EntityManager);
     Pais Corea= new Pais("COR");
     Corea.setNombre("COREA");
     //daoPais.destroy(Corea.getPais());
      Presentacion.login.View app= new  Presentacion.login.View();
      app.setLocationRelativeTo(null);
      app.setVisible(true);
    }
       
 
}
