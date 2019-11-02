/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Listado_Viajes;

import Logica.Viaje;
import java.util.List;

/**
 *
 * @author sergi
 */
public class Controller {
    Model model;
    View view;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }
    
    public void buscar(){
        model.buscar("");
    }
    
    public void eliminar(int row){
        model.eliminar(row);
    }
}
