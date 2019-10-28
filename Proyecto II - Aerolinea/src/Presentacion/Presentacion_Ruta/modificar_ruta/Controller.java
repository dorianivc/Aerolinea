/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.modificar_ruta;

import Datos.DBQuerys;
import Datos.RutaJpaController;
import Logica.Ciudad;
import Logica.Ruta;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Controller {
    public Model model;
    public View view;
    public Datos.DBQuerys db;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.db = new Datos.DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
    
    public void ModificarTipoDePago(Ruta route){
        try{
            RutaJpaController rutaPagoDao = new RutaJpaController(db.db.EntityManager);
            rutaPagoDao.edit(route);
            JOptionPane.showMessageDialog(null, "Ruta modificada con éxito", "Modificando la ruta", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }     
}
