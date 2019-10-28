/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.Listado_De_Pagos;

import Logica.TipodePago;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class tipoPagoTableModel extends AbstractTableModel{
    List<TipodePago> tipos;
    
    public tipoPagoTableModel(List<TipodePago> tipos){
        this.tipos=tipos;
    }

    public void setAviones(List<TipodePago> tipos) {
        this.tipos = tipos;
    }

    public List<TipodePago> getTipos() {
        return tipos;
    }
    
        @Override
    public int getRowCount() {
        return tipos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
        @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Tipo de pago: ";
            case 1: return "Descripcion: ";
            default: return "";
        }        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipodePago t=tipos.get(rowIndex);
        switch(columnIndex){
            case 0: return t.getTipoDePago();
            case 1: return t.getDescripcion();
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int i){
        switch(i){
            default: return super.getColumnClass(i);
        }
        
    }    


   
    
}
