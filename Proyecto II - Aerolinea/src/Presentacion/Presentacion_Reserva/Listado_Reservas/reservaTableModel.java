/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Reserva.Listado_Reservas;

import Logica.Reserva;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class reservaTableModel extends AbstractTableModel{
    
    List<Reserva> reservas;

    public reservaTableModel(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    

    @Override
    public int getRowCount() {
        return this.reservas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reserva r = reservas.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getReserva();
            case 1: return r.getViaje().mostrarViaje();
            case 2: return r.getNumeroAsiento();
            case 3: return r.getPago();
            case 4: return r.getUsuario();
            default: return "";
        }
    }
    
            @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Reserva:";
            case 1: return "Viaje:";
            case 2: return "Asientos:";
            case 3: return "Pago:";
            case 4: return "Usuario:";
            default: return "";
        }        
    }
    
    
}
