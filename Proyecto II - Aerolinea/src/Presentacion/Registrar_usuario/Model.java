/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Registrar_usuario;

import Datos.DBQuerys;
import Datos.UsuarioJpaController;
import Logica.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Model {
    public DBQuerys db;
    
    public Model(){
        db=new DBQuerys();
    }
    
   
}
