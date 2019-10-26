
package Presentacion.Presentacion_Horario.agregar_horario;

import Datos.DBQuerys;
import Datos.HorarioJpaController;
import Logica.Horario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Model {
    public DBQuerys db;
    
    public Model(){
        this.db=new DBQuerys();
        
    }
    public void agregarHorario(Horario horario){
        HorarioJpaController horarioDao= new HorarioJpaController(db.db.EntityManager);
        try{
            horarioDao.create(horario);
            JOptionPane.showMessageDialog(null, "Se ha agregado el horario satisfactoriamente", "Horario Agregado",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al ingresar Horario",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
