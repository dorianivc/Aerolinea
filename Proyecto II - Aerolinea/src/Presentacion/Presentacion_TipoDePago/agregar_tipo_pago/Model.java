/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.agregar_tipo_pago;

import Datos.TipodePagoJpaController;
import Logica.TipodePago;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Model {
    public Datos.DBQuerys db;
    
    public Model(){
        db = new Datos.DBQuerys();
    }
    
    void agregarMetodoDePago(TipodePago tipoPago){
        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
        try{
            TipodePagoJpaController tipoPagoDao = new TipodePagoJpaController(db.db.EntityManager);
            tipoPagoDao.create(tipoPago);
            JOptionPane.showMessageDialog(null, "Tipo de pago agregado con éxito", "Agregando tipo de pago", JOptionPane.PLAIN_MESSAGE, icon);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se guardo el tipo de pago", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
}
