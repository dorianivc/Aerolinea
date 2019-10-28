/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.Modificar;

import Logica.Ciudad;
import Logica.Pais;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */
public class Model extends Observable{
    
    public Ciudad ciudad;

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        this.setChanged();
        this.notifyObservers();
    }

    public Model(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }    
}

