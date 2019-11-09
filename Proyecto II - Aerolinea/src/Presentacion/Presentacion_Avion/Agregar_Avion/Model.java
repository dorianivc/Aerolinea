/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Agregar_Avion;

import Datos.AvionDisponibleJpaController;
import Logica.AvionDisponible;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Model {
    public Datos.DBQuerys db;
    
    public Model(){
        db = new Datos.DBQuerys();
    }
    
    void agregarAvion(AvionDisponible avion){
        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
        AvionDisponibleJpaController avionDao = new AvionDisponibleJpaController(db.db.EntityManager);
        try{
           avionDao.create(avion);
          //db.AvionAdd(avion);
           JOptionPane.showMessageDialog(null, "Avión agregado con éxito", "Avion agregado", JOptionPane.PLAIN_MESSAGE, icon);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Avion no agregado", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    
         public List<AvionDisponible> buscar(String nombre){
           List<AvionDisponible> result = db.AvionSearch(nombre);
         return result;
    }
   
         
              static Model the_instance;
    public static Model instance(){
        if (the_instance==null){
            the_instance = new Model();
        }
        return the_instance;
    }
    
}
