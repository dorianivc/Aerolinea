/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Vuelo_Usuario;

import Datos.DBQuerys;
import Logica.Vuelo;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
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
    
       public void buscar(String o,String d){
        try{         
           List<Vuelo> lista = db.VueloSearch2(o,d);
           model.setVuelos(lista);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
}
