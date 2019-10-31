/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Registrar_usuario;

/**
 *
 * @author sergi
 */

import Logica.Usuario;
import java.sql.SQLException;
import java.util.List;


public class Controller {
    Model modelo;
    public Controller(){
        this.modelo= new Model();
        
    }
    
 
    public void agregarUsuario(Usuario usuario){ 
        modelo.agregarUsuario(usuario);
}

}