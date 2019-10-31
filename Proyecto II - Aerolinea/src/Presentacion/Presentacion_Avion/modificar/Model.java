/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.modificar;

import Logica.AvionDisponible;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */
public class Model extends Observable{
    
    public AvionDisponible avion;

    public Model(AvionDisponible avion) {
        this.avion = avion;
    }

   

    public void setCiudad(AvionDisponible avion) {
        this.avion = avion;
        this.setChanged();
        this.notifyObservers();
    }

    public AvionDisponible getAvion() {
        return avion;
    }

   
    
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }    
}

