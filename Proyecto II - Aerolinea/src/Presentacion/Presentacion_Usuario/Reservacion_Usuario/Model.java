/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Reservacion_Usuario;

import Datos.DBQuerys;
import Logica.Pago;
import Logica.Usuario;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    Usuario usuario;
    Pago pago;
    DBQuerys db;

    public Model(Usuario usuario) {
        this.usuario = usuario;
        this.pago = new Pago();
        this.db = new DBQuerys();
        this.setChanged();
        this.notifyObservers();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }    
    
    
}
