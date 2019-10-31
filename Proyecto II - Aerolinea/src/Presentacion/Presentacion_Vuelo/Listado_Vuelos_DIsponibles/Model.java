/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Listado_Vuelos_DIsponibles;

import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */
public class Model extends Observable{
   List<Vuelo> vuelos;

    public Model(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

   public Model(){
       vuelos = new ArrayList();
   }
   
    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
        this.setChanged();
        this.notifyObservers();   
    }
    
   @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
   
   
}
