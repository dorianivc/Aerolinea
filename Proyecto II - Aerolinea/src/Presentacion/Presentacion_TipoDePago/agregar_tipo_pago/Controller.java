/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.agregar_tipo_pago;

import Logica.TipodePago;

/**
 *
 * @author Gabriel
 */
public class Controller {
    public Model model;
    
    public Controller(){
        model = new Model();
    }    
    
    public void agregarTipoDePago(TipodePago tipoPago){
        model.agregarMetodoDePago(tipoPago);
    }
    
}
