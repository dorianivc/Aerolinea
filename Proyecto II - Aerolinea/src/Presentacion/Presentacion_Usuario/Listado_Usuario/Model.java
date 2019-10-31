/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Listado_Usuario;

import Datos.DBQuerys;
import Datos.UsuarioJpaController;
import Logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    List<Usuario> usuarios;
  public Datos.DBQuerys db;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

   
    public Model() {
       usuarios = new ArrayList<>();
        this.db= new DBQuerys();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.setChanged();
        this.notifyObservers();         
    }
    
    public void eliminar(int row){
        UsuarioJpaController usuarioDa = new UsuarioJpaController (db.db.EntityManager);
        try{
        Usuario user = getUsuarios().get(row);
        usuarioDa.destroy(user.getUsuario());
        JOptionPane.showMessageDialog(null, "Se elimino el usuario correctamente", "Se elimino el usuario correctamente",JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception es){
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario", "Error ",JOptionPane.PLAIN_MESSAGE,null);
        }
    }

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
}