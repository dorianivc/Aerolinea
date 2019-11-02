/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.listado_horarios;

import Logica.Horario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class horarioTableModel extends AbstractTableModel{

    public horarioTableModel(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
    List<Horario> horarios;
    

    @Override
    public int getRowCount() {
        return horarios.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Horario h = horarios.get(rowIndex);
        switch(columnIndex){
            case 0: return h.getHorario();
            case 1: return h.getSalida().getHours()-18+":"+h.getSalida().getMinutes();
            case 2: return h.getLlegada().getHours()-18+":"+h.getLlegada().getMinutes();
            case 3: return h.getDiaDeLaSemana();
            default:return "";
        }
    }
    
         @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Horario: ";
            case 1: return "Sale: ";
            case 2: return "Llega: ";
            case 3: return "Sale el dia: ";
            default:return "";
        }
    }
    
    
    
}
