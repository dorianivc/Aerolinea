/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Listado_aviones_disponibles;

import Logica.AvionDisponible;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */

public class Model extends Observable{
    List<AvionDisponible> aviones;

    public Model(List<AvionDisponible> aviones) {
        this.aviones = aviones;
    }

    public Model() {
        aviones = new ArrayList<>();
    }

    public List<AvionDisponible> getAviones() {
        return aviones;
    }

    public void setAviones(List<AvionDisponible> aviones) {
        this.aviones = aviones;
        this.setChanged();
        this.notifyObservers();         
    }

    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
}

