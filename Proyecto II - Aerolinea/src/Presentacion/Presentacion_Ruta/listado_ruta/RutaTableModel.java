/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.listado_ruta;

import Logica.Ruta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class RutaTableModel extends AbstractTableModel {
    List<Ruta> rutas;

    public RutaTableModel(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    @Override
    public int getRowCount() {
        return rutas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Ruta numero:";
            case 1: return "Duración:";
            case 2: return "Ciudad de salida:";
            case 3: return "Ciudad de llegada: ";
            default:return "";
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ruta r=rutas.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getRuta();
            case 1: return r.getDuracion();
            case 2: return r.getCiudadSalida().getNombre();
            case 3: return r.getCiudadLlegada().getNombre();
            default:return "";
        }
    }
    
}
