/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.listado_paises;

import Logica.Pais;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sergi
 */
public class PaisTableModel extends AbstractTableModel{
    List<Pais> paises;   

    public PaisTableModel(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Pais> getPais() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }
    
    @Override
    public int getRowCount() {
        return paises.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Codigo Pais ";
            case 1: return "Nombre: ";
            default: return "";
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Pais p=paises.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getPais();
            case 1: return p.getNombre();
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
