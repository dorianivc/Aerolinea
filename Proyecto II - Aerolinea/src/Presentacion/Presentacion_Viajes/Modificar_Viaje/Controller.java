/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Modificar_Viaje;

import Logica.Viaje;

/**
 *
 * @author Gabriel
 */
public class Controller {
    public Model model;
    public View view;
    
    public Controller(Model model,View view){    
        this.model=model;
        this.view=view;
        view.setModel(model);
        view.setController(this);
    }
    
    public void modificar(Viaje viaje){
        model.setViaje(viaje);
    }
}
