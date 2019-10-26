
package Presentacion.Presentacion_Horario.agregar_horario;

import Logica.Horario;

public class Controller {
    Model model;
    Controller(){
        model=new Model();
    }
    
    public void agregarHorarioaBD(Horario horario){
        this.model.agregarHorario(horario);
    }
}
