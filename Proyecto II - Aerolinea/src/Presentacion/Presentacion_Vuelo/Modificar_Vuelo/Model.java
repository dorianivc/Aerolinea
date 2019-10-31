/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Modificar_Vuelo;

import Datos.AvionDisponibleJpaController;
import Datos.DBQuerys;
import Datos.HorarioJpaController;
import Datos.RutaJpaController;
import Datos.VueloJpaController;
import Logica.AvionDisponible;
import Logica.Horario;
import Logica.Ruta;
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
public class Model extends Observable {

    Vuelo vuelo;
    DBQuerys db;

    public Model(Vuelo vuelo) {
        this.vuelo = vuelo;
        db = new DBQuerys();
    }

    public List<AvionDisponible> getAvionList() {
        List<AvionDisponible> lista = new ArrayList();
        try {
            AvionDisponibleJpaController avionDao = new AvionDisponibleJpaController(db.db.EntityManager);
            lista = db.AvionSearch("");

        } catch (Exception ex) {

        }
        return lista;
    }

    public List<Horario> getHorarioList() {
        List<Horario> lista = new ArrayList();
        try {
            HorarioJpaController horarioDao = new HorarioJpaController(db.db.EntityManager);
            lista = horarioDao.findHorarioEntities();
        } catch (Exception ex) {

        }
        return lista;
    }

    public List<Ruta> getRutaList() {
        List<Ruta> lista = new ArrayList();
        try {
            RutaJpaController rutaDao = new RutaJpaController(db.db.EntityManager);
            lista = rutaDao.findRutaEntities();
        } catch (Exception ex) {

        }
        return lista;
    }

    public void ModificarVuelo(Vuelo vuelo) {
        try {
            db.VueloUpdate(vuelo);
            JOptionPane.showMessageDialog(null, "Vuelo modificado con éxito", "Modificando el vuelo", JOptionPane.PLAIN_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelos(Vuelo vuelo) {
        this.vuelo = vuelo;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }

}
