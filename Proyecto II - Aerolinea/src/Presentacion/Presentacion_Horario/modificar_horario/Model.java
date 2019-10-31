/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.modificar_horario;

import Logica.Horario;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gabriel
 */
public class Model extends Observable{
    public Horario horario;

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Model(Horario horario) {
        this.horario = horario;
        this.setChanged();
        this.notifyObservers();
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    } 
    
    
    
}
