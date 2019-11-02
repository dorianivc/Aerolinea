/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Viajes.Listado_Viajes;

import Logica.Viaje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class ViajeTableModel extends AbstractTableModel{
    List<Viaje> viajes;

    public ViajeTableModel(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }
    
    

    @Override
    public int getRowCount() {
        return this.viajes.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Viaje: ";
            case 1: return "Fecha: ";
            case 2: return "Vuelo: ";
            default: return "";
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Viaje v = viajes.get(rowIndex);
        switch(columnIndex){
            case 0: return v.getViaje();
            case 1: return v.getFecha();
            case 3: return v.getVuelo().getVuelo();
            default: return "";
        }
    }
    
}
