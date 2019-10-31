/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Listado_Vuelos_DIsponibles;

import Datos.DBQuerys;
import Datos.VueloJpaController;
import Logica.Vuelo;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
    Model model;
    View view;
    DBQuerys db;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        db= new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }    
    
    public void buscar(String codigo){
        try{         
            List<Vuelo> lista = db.VueloSearch(codigo);
            model.setVuelos(lista);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    public void eliminar(int row){
        try{
           VueloJpaController vueloDao = new VueloJpaController(db.db.EntityManager);
           Vuelo vuelo = model.getVuelos().get(row);
           vueloDao.destroy(vuelo.getVuelo());
            JOptionPane.showMessageDialog(null, "ELiminado el vuelo con exito", "Eliminando el vuelo", JOptionPane.PLAIN_MESSAGE, null);
        }catch(Exception ex){
            
        }
    }
    
}
