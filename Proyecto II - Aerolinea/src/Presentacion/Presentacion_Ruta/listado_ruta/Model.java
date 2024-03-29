/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.listado_ruta;

import Logica.Ruta;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Monica
 */
public class Model extends Observable {
    List<Ruta> rutas;
    
    public Model(List<Ruta> rutas){
        this.rutas = rutas;
    }
    
    public Model(){
        rutas = new ArrayList<>();
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
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
