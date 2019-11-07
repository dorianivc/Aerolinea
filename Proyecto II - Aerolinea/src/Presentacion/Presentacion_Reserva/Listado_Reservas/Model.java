/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Reserva.Listado_Reservas;

import Datos.DBQuerys;
import Logica.Reserva;
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
public class Model extends Observable{
    List<Reserva> reservas;
    DBQuerys db;

    public Model(List<Reserva> reservas) {
        this.reservas = reservas;
        db = new DBQuerys();
    }
    
    public Model(){
        db = new DBQuerys();
        this.reservas = new ArrayList();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
        this.setChanged();
        this.notifyObservers();        
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }  
    
    public void buscarReservas(String id){
        List<Reserva> resultado = new ArrayList<Reserva>();
        try {
            setReservas(db.reservaSearchId(id));
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    
}
