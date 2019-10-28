/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.listado_ciudades;

import Datos.CiudadJpaController;
import Datos.DBQuerys;
import Datos.PaisJpaController;
import Logica.Ciudad;
import Logica.Pais;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author Monica
 */
public class Model extends Observable{
    List<Ciudad> ciudades;
  public Datos.DBQuerys db;

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

   
    public Model() {
       ciudades = new ArrayList<>();
        this.db= new DBQuerys();
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
        this.setChanged();
        this.notifyObservers();         
    }
    
    public void eliminar(int row){
        CiudadJpaController ciudadDa = new CiudadJpaController (db.db.EntityManager);
        try{
        Ciudad ciudad = getCiudades().get(row);
        ciudadDa.destroy(ciudad.getCiudad());
        JOptionPane.showMessageDialog(null, "Se elimino la ciudad correctamente", "Se elimino la ciudad correctamente",JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception es){
        JOptionPane.showMessageDialog(null, "No se pudo eliminar ciudad", "Error ",JOptionPane.PLAIN_MESSAGE,null);
        }
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
}