/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Datos.BaseDatosAWS;
import Datos.DBQuerys;
import Datos.PaisJpaController;
import Datos.RutaJpaController;
import Datos.TipodePagoJpaController;
import Datos.VueloJpaController;
import Logica.Pais;
import Logica.Ruta;
import Logica.TipodePago;
import Logica.Vuelo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Monica
 */
public class main {
  
    
     public static void main(String[] args) throws SQLException, Exception {
         
   
      Presentacion.login.View app= new  Presentacion.login.View();
      Presentacion.login.Model model = new Presentacion.login.Model();
      Presentacion.login.Controller controller = new Presentacion.login.Controller(model, app);
      app.setLocationRelativeTo(null);
      app.setVisible(true);
    }
       
 
}
