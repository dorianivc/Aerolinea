/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.Listado_De_Pagos;

import Logica.TipodePago;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sergi
 */
public class Model extends Observable{
    List<TipodePago> tipos;

    public Model(List<TipodePago> tipos) {
        this.tipos = tipos;
    }

    public Model() {
        tipos = new ArrayList<>();
    }

    public List<TipodePago> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipodePago> tipos) {
        this.tipos = tipos;
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
