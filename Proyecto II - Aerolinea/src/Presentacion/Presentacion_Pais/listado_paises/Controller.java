/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.listado_paises;

import Datos.DBQuerys;
import Datos.PaisJpaController;
import Logica.Pais;

import static Presentacion.Presentacion_Pais.listado_paises.View.PaisController;
import javax.swing.JOptionPane;

/**
 *
 * @author Monica
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
    
    public void buscar(String nombre){
     
      model.setPaises(Presentacion.Presentacion_Pais.agregar_pais.Model.instance().buscar(nombre));
    }
    
    public void eliminar(int row){
        model.eliminar(row);
    }
    
    
    public void editar(int row){
         PaisController.consultar(model.getPaises().get(row).getPais());
    
    }
    public void show(){
        view.setVisible(true);
    }
    
  
}
