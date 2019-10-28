/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.listado_ciudades;

import Logica.Ciudad;
import Logica.Pais;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sergi
 */
public class CiudaTableModel extends AbstractTableModel{
    List<Ciudad> ciudades;   

    public CiudaTableModel(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    @Override
    public int getRowCount() {
        return ciudades.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Codigo Ciudad ";
            case 1: return "Nombre: ";
            case 2: return "Pais: ";
            default: return "";
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Ciudad c=ciudades.get(rowIndex);
        switch(columnIndex){
            case 0: return c.getCiudad();
            case 1: return c.getNombre();
            case 2: return c.getPais();
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