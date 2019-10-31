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

    public void ModificarVuelo(Vuelo vuelo){
        model.ModificarVuelo(vuelo);
    }
    
}
