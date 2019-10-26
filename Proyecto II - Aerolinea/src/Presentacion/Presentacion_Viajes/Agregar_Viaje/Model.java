/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Agregar_Viaje;

import Datos.DBQuerys;
import Datos.ViajeJpaController;
import Datos.VueloJpaController;
import Logica.Viaje;
import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Model {
   public DBQuerys db;
    
   public Model(){
      db=new DBQuerys();
   }
   
   public List<Vuelo> listadoVuelos(){
       List<Vuelo> lista= new ArrayList<>();
       try{
           VueloJpaController vueloDao= new VueloJpaController(db.db.EntityManager);
           lista=vueloDao.findVueloEntities();
       }catch(Exception se){
           JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR AL OBTENER VUELOS",JOptionPane.PLAIN_MESSAGE);
       }
       return lista;
   }
   
   public void agregarViaje(Viaje viaje){
       try{
           ViajeJpaController viajeDao= new ViajeJpaController(db.db.EntityManager);
           viajeDao.create(viaje);
           JOptionPane.showMessageDialog(null, "Viaje agregado con exito", "VIAJE AGREGADO",JOptionPane.PLAIN_MESSAGE);
       }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "EEROR AL AGREGAR VIAJE",JOptionPane.PLAIN_MESSAGE);
       }
   }
}
