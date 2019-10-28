/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.Modificar;

import Datos.PaisJpaController;
import Datos.TipodePagoJpaController;
import Logica.Pais;
import Logica.TipodePago;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
    public Presentacion.Presentacion_Pais.Modificar.Model model;
    public Presentacion.Presentacion_Pais.Modificar.View view;
    public Datos.DBQuerys db;
    
    
    public Controller(Presentacion.Presentacion_Pais.Modificar.Model model,Presentacion.Presentacion_Pais.Modificar.View view){    
        this.model=model;
        this.view=view;
        db = new Datos.DBQuerys();
        view.setModel(model);
        view.setController(this);
    }    
    
    public void ModificarPais(Pais pais){
        try{
            PaisJpaController PaisDao = new PaisJpaController(db.db.EntityManager);
//            PaisDao.edit(pais);
            db.PaisUpdate(pais);
            JOptionPane.showMessageDialog(null, "Pais modificado con éxito", "Modificando el Pais", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar pais", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    } 
    
    public void show(){
        view.setVisible(true);
    }
}