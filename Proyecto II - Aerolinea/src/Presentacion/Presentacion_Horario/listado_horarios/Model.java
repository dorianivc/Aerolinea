/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.listado_horarios;

import Logica.Horario;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable {

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
        this.setChanged();
        this.notifyObservers();      
    }

    public Model(List<Horario> horarios) {
        this.horarios = horarios;
    }
    
    public Model(){
        horarios = new ArrayList<>();
    }

    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
    List<Horario> horarios;
    
    
}
