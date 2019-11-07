/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Vuelo_Usuario;

import Datos.DBQuerys;
import Logica.Ciudad;
import Logica.Usuario;
import Logica.Vuelo;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable {
    Usuario usuario;
    List<Vuelo> vuelos;
    DBQuerys db;

    public Model(Usuario usuario) {
        this.usuario = usuario;
        db = new DBQuerys();
        try{
            this.vuelos=db.VueloSearch("");
        }catch(Exception ex){
            System.out.print("error al mostrar los vuelos");
        }
        this.setChanged();
        this.notifyObservers();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.setChanged();
        this.notifyObservers();
      
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
        this.setChanged();
        this.notifyObservers();
    }
    
    public List<Ciudad> getCiudades(){
        List<Ciudad> ciudades = db.CiudadSearch("");
        return ciudades;
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }   
    
    public void buscarTodosVuelos(){
        try{
            this.setVuelos(db.VueloSearch(""));
        }catch(Exception ex){
            System.out.print("error al mostrar los vuelos");
        }
    }
    
    public void buscarHorario(String dia){
        try{
            this.setVuelos(db.VueloSearch3(dia));
        }catch(Exception ex){
            System.out.print("error al mostrar los vuelos");
        }
    }
    
}
