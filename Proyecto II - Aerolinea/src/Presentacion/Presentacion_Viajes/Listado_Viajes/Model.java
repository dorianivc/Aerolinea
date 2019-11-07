/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Listado_Viajes;

import Datos.DBQuerys;
import Logica.Viaje;
import Logica.Vuelo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Model extends Observable {
    List<Viaje> viajes;
    DBQuerys db;

    public Model(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Model() {
        db= new DBQuerys();
        try {
            viajes = db.listadoViajes();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> tipos) {
        this.viajes = tipos;
        this.setChanged();
        this.notifyObservers();         
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }     
    
    public void buscar(String v){
        try{
            setViajes(db.ViajeSearchVuelo(v));
        }catch(Exception ex){}
    }
    
    public void eliminar(int row){
        try{
            Viaje viaje = getViajes().get(row);
            db.deleteViaje(viaje);
            JOptionPane.showMessageDialog(null, "ELiminado el viaje", "Eliminando el viaje", JOptionPane.PLAIN_MESSAGE, null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    public List<Vuelo> buscarVuelos(){
        List<Vuelo> vuelos = new ArrayList<>();
        try{
           vuelos = db.VueloSearch("");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
        return vuelos;
    }
    
}
