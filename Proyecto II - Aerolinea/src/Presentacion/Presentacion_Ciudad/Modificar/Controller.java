/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.Modificar;

import Datos.CiudadJpaController;
import Datos.PaisJpaController;
import Logica.Ciudad;
import Logica.Pais;
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
    
    public void ModificarCiudad(Ciudad ciudad){
         CiudadJpaController CiudadDao = new CiudadJpaController(db.db.EntityManager);
        try{
//            CiudadDao.edit(ciudad);
            db.CiudadUpdate(ciudad);
            JOptionPane.showMessageDialog(null, "Ciudad modificada con éxito", "Modificando la ciudad", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar ciudad", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    } 
    
    public void show(){
        view.setVisible(true);
    }
}