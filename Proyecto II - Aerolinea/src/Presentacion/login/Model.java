/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.login;

import Datos.DBQuerys;
import Datos.UsuarioJpaController;
import Logica.Usuario;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Monica
 */
public class Model extends Observable {

    DBQuerys db;
    Usuario user;

    public Model() {
        db = new DBQuerys();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
        this.setChanged();
        this.notifyObservers();
    }
  
    public Usuario getUsuario(String llave, String contrasenia) {
        UsuarioJpaController usuarioDao = new UsuarioJpaController(db.db.EntityManager);
        user = usuarioDao.findUsuario(llave);
        if (user != null && user.getContrasena().equals(contrasenia)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean esAdmin(Usuario u) {
        return (u.getAdmin()==1);
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }

}
