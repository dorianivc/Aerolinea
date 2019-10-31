/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Listado_aviones_disponibles;

import Datos.AvionDisponibleJpaController;
import Datos.CiudadJpaController;
import Datos.DBQuerys;
import Logica.AvionDisponible;
import Logica.Ciudad;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */

public class Model extends Observable{
    List<AvionDisponible> aviones;
    public Datos.DBQuerys db;

    public Model(List<AvionDisponible> aviones) {
        this.aviones = aviones;
    }

      public Model() {
       aviones = new ArrayList<>();
        this.db= new DBQuerys();
    }
    public List<AvionDisponible> getAviones() {
        return aviones;
    }

    public void setAviones(List<AvionDisponible> aviones) {
        this.aviones = aviones;
        this.setChanged();
        this.notifyObservers();         
    }
    
    
     public void eliminar(int row){
       AvionDisponibleJpaController avionDa = new AvionDisponibleJpaController (db.db.EntityManager);
        try{
        AvionDisponible avion = getAviones().get(row);
   //     avionDa.destroy(avion.getCodigoMatricula());
        db.AvionDelete(avion);
        JOptionPane.showMessageDialog(null, "Se elimino el avion correctamente", "Se elimino el avion correctamente",JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception es){
        JOptionPane.showMessageDialog(null, "No se pudo eliminar avion", "Error ",JOptionPane.PLAIN_MESSAGE,null);
        }
    }
    

    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
}

