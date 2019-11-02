/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.historial_Usuario;

import Datos.DBQuerys;
import Logica.Reserva;
import Logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable {
    Usuario usuario;
    List<Reserva> reservas;
    DBQuerys db;

    public Model(List<Reserva> reservas) {
        this.reservas = reservas;
        db = new DBQuerys();
    }

    public Model(Usuario usuario) {
        db = new DBQuerys();
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }

    public List<Reserva> buscarReservas() {
        List<Reserva> resultado = new ArrayList<Reserva>();
        try {
            resultado = db.reservaSearchId(usuario.getUsuario());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
        return resultado;
    }

}
