/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.pagar;


public class Controller {
    public Model model;
    
    public Controller(Model model){
        this.model=model;
    }
    public Controller(){
        model=null;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
