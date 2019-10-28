/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.modificar_tipo_pago;

import Datos.TipodePagoJpaController;
import Logica.TipodePago;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
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
    
    public void ModificarTipoDePago(TipodePago tipoPago){
        try{
            TipodePagoJpaController tipoPagoDao = new TipodePagoJpaController(db.db.EntityManager);
            tipoPagoDao.edit(tipoPago);
            JOptionPane.showMessageDialog(null, "Tipo de pago modificado con éxito", "Modificando el tipo de pago", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    } 
    
    public void show(){
        view.setVisible(true);
    }
}
