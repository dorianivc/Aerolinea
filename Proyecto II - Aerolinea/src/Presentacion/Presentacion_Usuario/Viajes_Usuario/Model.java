/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Viajes_Usuario;

import Datos.DBQuerys;
import Datos.ViajeJpaController;
import Logica.Usuario;
import Logica.Viaje;
import Logica.Vuelo;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    Usuario usuario;
    List<Viaje> viajes;
    DBQuerys db;
    Vuelo vuelo;

    public Model(Usuario usuario, Vuelo vuelo) {
        this.usuario = usuario;
        this.vuelo = vuelo;
        db = new DBQuerys();
        try{
            this.viajes=db.ViajeSearchVuelo(vuelo.getVuelo());
        }catch(Exception ex){
            System.out.print("error al mostrar los viajes");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.setChanged();
        this.notifyObservers();
        this.usuario = usuario;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setVuelos(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }   
}
