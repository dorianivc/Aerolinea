/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.Modificar;


import Logica.Pais;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */
public class Model extends Observable{
    
    public Pais pais;
    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.setChanged();
        this.notifyObservers();
    }
    
    
    public Model(Pais pais){
        this.pais=pais;
        
    }
    
     
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }    
}
