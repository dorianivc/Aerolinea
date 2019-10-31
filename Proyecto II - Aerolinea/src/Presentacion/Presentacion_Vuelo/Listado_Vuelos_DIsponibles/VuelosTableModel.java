/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Listado_Vuelos_DIsponibles;

import Logica.Vuelo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class VuelosTableModel extends AbstractTableModel {
    
    List<Vuelo> vuelos;

    public VuelosTableModel(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
    
            @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Código:";
            case 1: return "Horario:";
            case 2: return "Avion asignado";
            case 3: return "Ruta:";
            default: return "";
        }
    }

    @Override
    public int getRowCount() {
        return vuelos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vuelo v = vuelos.get(rowIndex);
        switch(columnIndex){
            case 0: return v.getVuelo();
            case 1: return v.getHorario();
            case 2: return v.getAvionAsignado();
            case 3: return v.getRutaAsignada();
            default:return "";
        }
    }
    
}
