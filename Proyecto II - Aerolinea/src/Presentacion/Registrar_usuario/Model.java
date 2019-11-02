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
import Datos.DBQuerys;
import Datos.UsuarioJpaController;
import Logica.Usuario;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;


public class Model {
    
public DBQuerys db;

public Model(){
    this.db= new DBQuerys();
}


void agregarUsuario(Usuario usuario){
     UsuarioJpaController usuarioDao=new UsuarioJpaController(db.db.EntityManager);
    try{
          if (usuario.getNombre() == " " || usuario.getApellidos() == " " || usuario.getCelular() ==" " || usuario.getContrasena() ==" "
             || usuario.getCorreoElectronico() ==" " || usuario.getDireccion() ==" " || usuario.getFechaNacimiento() == null||
                usuario.getTelefono() == " " || usuario.getUsuario() ==" "){
              int x = 999;
              int x2;
        throw new Exception("Campos Vacios");
        
        }
          else usuarioDao.create(usuario);
    }catch(Exception es){
        JOptionPane.showMessageDialog(null, "Usuario ya registrado");
     
    }
}

 static Model the_instance;
    public static Model instance(){
        if (the_instance==null){
            the_instance = new Model();
        }
        return the_instance;
    }
    
       public List<Usuario> buscar(String nombre){
        List<Usuario> result = db.UsuarioSearch(nombre);
         return result;
    }

}

