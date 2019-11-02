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
         
     /*
      DBQuerys db= new DBQuerys();
      db.db.reporte();
      
      
      List<Pais> lista= db.listadoPaises();
     for(int i=0;i<lista.size();i++){
              Pais pais= lista.get(i);
              System.out.println(pais.toString());
     }
     RutaJpaController daoRuta= new RutaJpaController(db.db.EntityManager);
     List<Ruta> lista2;
     lista2 = daoRuta.findRutaEntities();
     for(int i=0;i<lista2.size();i++){
              Ruta ruta= lista2.get(i);
              System.out.println("Ruta --> "+ruta.getCiudadSalida().getNombre()+ " - "+ ruta.getCiudadLlegada().getNombre());
     }
     
     TipodePagoJpaController daoPagos = new TipodePagoJpaController(db.db.EntityManager);
     List<TipodePago> lista3;
     lista3=daoPagos.findTipodePagoEntities();
     for(int i=0;i<lista3.size();i++){
         TipodePago pago=lista3.get(i);
         System.out.println(pago.getTipoDePago());
     }
     */
  
       
      Presentacion.login.View app= new  Presentacion.login.View();
      Presentacion.login.Model model = new Presentacion.login.Model();
      Presentacion.login.Controller controller = new Presentacion.login.Controller(model, app);
      app.setLocationRelativeTo(null);
      app.setVisible(true);
    }
       
 
}
