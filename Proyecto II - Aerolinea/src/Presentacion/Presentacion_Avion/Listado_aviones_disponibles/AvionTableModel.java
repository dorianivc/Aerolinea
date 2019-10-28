/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Listado_aviones_disponibles;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import Logica.AvionDisponible;
/**
 *
 * @author Gabriel
 */
public class AvionTableModel extends AbstractTableModel{
    List<AvionDisponible> aviones;
    
    public AvionTableModel(List<AvionDisponible> aviones){
        this.aviones=aviones;
    }

    public void setAviones(List<AvionDisponible> aviones) {
        this.aviones = aviones;
    }

    public List<AvionDisponible> getAviones() {
        return aviones;
    }
    
        @Override
    public int getRowCount() {
        return aviones.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    
        @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Matricula: ";
            case 1: return "Año: ";
            case 2: return "Marca: ";
            case 3: return "Modelo: ";
            case 4: return "Filas: ";
            case 5: return "Columnas: ";
            case 6: return "Pasajeros: ";
            default: return "";
        }        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AvionDisponible a=aviones.get(rowIndex);
        switch(columnIndex){
            case 0: return a.getCodigoMatricula();
            case 1: return a.getAno();
            case 2: return a.getMarca();
            case 3: return a.getModelo();
            case 4: return a.getFilas();
            case 5: return a.getColumnas();
            case 6: return a.getCantidadDePasajeros();
            default: return "";
        }
    }    


   
    
}
