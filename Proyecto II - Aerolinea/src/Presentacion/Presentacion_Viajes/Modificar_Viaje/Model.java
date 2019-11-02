/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Modificar_Viaje;

import Datos.DBQuerys;
import Datos.ViajeJpaController;
import Logica.Viaje;
import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    public Viaje viaje;
    public DBQuerys db;
    
    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
        this.setChanged();
        this.notifyObservers();
    }
        
    public Model(Viaje viaje){
        this.viaje=viaje;
        db = new Datos.DBQuerys();
        this.setChanged();
        this.notifyObservers();
    }
        
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
    public void modificar(Viaje v){
        try{
            db.viajeUpdate(v);
            JOptionPane.showMessageDialog(null, "Viaje modificado con éxito", "Modificando el viaje", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);            
        }
    }
    
    public List<Vuelo> buscarVuelos(){
        List<Vuelo> v = new ArrayList<>();
        try{
            v = db.VueloSearch("");
        }catch(Exception ex){
            
        }
        return v;
    }
}
