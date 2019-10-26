/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.agregar_ruta;

import Logica.Ciudad;
import Logica.Ruta;
import java.util.List;

/**
 *
 * @author Monica
 */
public class Controller {
    public Model model;
   public Controller(){
        model= new Model();
    }
   
   public void agregarRuta(Ruta nuevaRuta){
       model.agregarRuta(nuevaRuta);
   }
   
   public List<Ciudad> listadoCiudad(){
       return model.listadoCiudades();
   }
}
