/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.listado_horarios;

import Datos.DBQuerys;

/**
 *
 * @author Gabriel
 */
public class Controller {
    Model model;
    View view; 
    
        public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        DBQuerys db = new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
}
