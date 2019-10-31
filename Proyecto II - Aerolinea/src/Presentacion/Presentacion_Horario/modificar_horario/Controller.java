/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Horario.modificar_horario;

import Datos.DBQuerys;
import Datos.HorarioJpaController;
import Logica.Horario;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Controller {
    public Model model;
    public View view;
    public DBQuerys db;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        db = new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
    public void ModificarHorario(Horario horario){
        try{
            HorarioJpaController horarioDao= new HorarioJpaController(db.db.EntityManager);
            horarioDao.edit(horario);
            JOptionPane.showMessageDialog(null, "Tipo de pago modificado con éxito", "Modificando el tipo de pago", JOptionPane.PLAIN_MESSAGE,null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    
    
}
