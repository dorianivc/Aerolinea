/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.modificar_tipo_pago;

import Datos.DBQuerys;
import Datos.TipodePagoJpaController;
import Logica.TipodePago;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    
    public TipodePago pago;
    
    public TipodePago getPago() {
        return pago;
    }

    public void setPago(TipodePago pago) {
        this.pago = pago;
        this.setChanged();
        this.notifyObservers();
    }
    
    
    public Model(TipodePago pago){
        this.pago=pago;
        
    }
    
     
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }    
}
