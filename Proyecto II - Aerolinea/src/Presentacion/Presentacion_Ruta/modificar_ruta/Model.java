/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.modificar_ruta;

import Datos.CiudadJpaController;
import Datos.DBQuerys;
import Logica.Ciudad;
import Logica.Ruta;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable {
    public Ruta ruta;

    public Model(Ruta ruta) {
        this.ruta = ruta;    
    }
    
    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    } 
}
