/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.listado_horarios;

import Datos.DBQuerys;
import Datos.HorarioJpaController;
import Logica.Horario;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Controller {
    Model model;
    View view; 
    DBQuerys db;
    
        public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        db = new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
    public void buscar(){
        try{
            HorarioJpaController horarioDao= new HorarioJpaController(db.db.EntityManager);
            List<Horario> lista = horarioDao.findHorarioEntities();
            model.setHorarios(lista);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    public void eliminar(int row){
        try{
            HorarioJpaController HorarioDao = new HorarioJpaController(db.db.EntityManager);
            Horario horario = model.getHorarios().get(row);
            HorarioDao.destroy(horario.getHorario());
            JOptionPane.showMessageDialog(null, "Eliminado el horario con exito", "Eliminando el horario", JOptionPane.PLAIN_MESSAGE, null);            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
}
