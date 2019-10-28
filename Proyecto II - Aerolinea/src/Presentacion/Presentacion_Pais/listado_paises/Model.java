/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.listado_paises;

import Datos.DBQuerys;
import Datos.PaisJpaController;
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
    List<Pais> paises;
  public Datos.DBQuerys db;

    public List<Pais> getPaises() {
        return paises;
    }

   
    public Model() {
       paises = new ArrayList<>();
        this.db= new DBQuerys();
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
        this.setChanged();
        this.notifyObservers();         
    }
    
    public void eliminar(int row){
        PaisJpaController paisDa = new PaisJpaController (db.db.EntityManager);
        try{
        Pais pais = getPaises().get(row);
        paisDa.destroy(pais.getPais());
        JOptionPane.showMessageDialog(null, "Se elimino el pais correctamente", "Se elimino el pais correctamente",JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception es){
        JOptionPane.showMessageDialog(null, "No se pudo eliminar pais", "Error ",JOptionPane.PLAIN_MESSAGE,null);
        }
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
}