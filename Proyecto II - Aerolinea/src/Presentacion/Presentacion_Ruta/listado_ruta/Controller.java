/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.listado_ruta;

import Datos.DBQuerys;
import Datos.RutaJpaController;
import Logica.Ruta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Monica
 */
public class Controller {
    Model model;
    View view;
    DBQuerys db;
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        db = new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
    public void buscar(String pais){
        try{
            
            List<Ruta> rutas = db.rutaNombrePaisSearch(pais);
            model.setRutas(rutas);
        }
        catch(Exception ex){
            
        }
    }
    
    public void eliminar(int row){
        try{
            RutaJpaController RutaDao = new RutaJpaController(db.db.EntityManager);
            Ruta ruta = model.getRutas().get(row);
            RutaDao.destroy(ruta.getRuta());
            JOptionPane.showMessageDialog(null, "Eliminada la ruta con exito", "Eliminando la ruta", JOptionPane.PLAIN_MESSAGE, null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
}
