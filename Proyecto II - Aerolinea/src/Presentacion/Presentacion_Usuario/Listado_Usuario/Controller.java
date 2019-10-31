/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Listado_Usuario;

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
        view.setModel(model);
        view.setController(this);
       
    }
    
    public void buscar(String nombre){ 
      model.setUsuarios(Presentacion.Registrar_usuario.Model.instance().buscar(nombre));
    }
    
    public void eliminar(int row){
        model.eliminar(row);
    }
    
    
 
    public void show(){
        view.setVisible(true);
    }
    
  
}
