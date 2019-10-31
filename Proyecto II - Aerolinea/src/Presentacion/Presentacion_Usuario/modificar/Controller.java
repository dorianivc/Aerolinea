/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.modificar;


import Datos.UsuarioJpaController;

import Logica.Usuario;

import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
     Model model;
    public View view;
    public Datos.DBQuerys db;
    
    
    public Controller(Model model,View view){    
        this.model=model;
        this.view=view;
        db = new Datos.DBQuerys();
        view.setModel(model);
        view.setController(this);
    }    
    
    public void ModificarUsuario(Usuario usuario){
         UsuarioJpaController UsuarioDao = new UsuarioJpaController(db.db.EntityManager);
        try{      
           // UsuarioDao.edit(usuario);
             db.UsuarioUpdate(usuario);
            JOptionPane.showMessageDialog(null, "Usuario modificado con éxito", "Modificando al usuario", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar usuario", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    } 
    
    public void show(){
        view.setVisible(true);
    }
}