/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Agregar_Viaje;

import Logica.Viaje;
import Logica.Vuelo;
import java.util.List;

/**
 *
 * @author sergi
 */
public class Controller {
   public Model model;
    
  public Controller(){
      model= new Model();
  }
  
  public List<Vuelo> getListadoVuelosBD(){
      return this.model.listadoVuelos();
  }
  
  public void agregarViajeBD(Viaje viaje){
      this.model.agregarViaje(viaje);
  }
}
