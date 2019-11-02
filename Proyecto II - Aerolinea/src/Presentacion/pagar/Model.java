/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.pagar;

import Logica.Reserva;
import java.util.List;


public class Model {
    public List<Reserva> reservaciones;
    
    public Model(List<Reserva> reservaciones){
        
        this.reservaciones=reservaciones;
    }
    public Model(){
        this.reservaciones=null;
    }

    public List<Reserva> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reserva> reservaciones) {
        this.reservaciones = reservaciones;
    }
}
