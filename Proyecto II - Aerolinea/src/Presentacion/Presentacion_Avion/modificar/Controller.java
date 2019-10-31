/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.modificar;

import Logica.AvionDisponible;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
   public Model model;
    public View view;
    public Datos.DBQuerys db;
    
    
    public Controller(Model model,View view){    
        this.model=model;
        this.view=view;
        db = new Datos.DBQuerys();
        view.setModel(model);
        view.setController(this);
    }    
    
    public void ModificarAvion(AvionDisponible avion){
        try{
//           
            db.AvionUpdate(avion);
            JOptionPane.showMessageDialog(null, "Avion modificado con éxito", "Modificando el avion", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar avion", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    } 
    
    public void show(){
        view.setVisible(true);
    }
}