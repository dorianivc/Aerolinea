/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Agregar_Vuelo;

import Datos.DBQuerys;
import Logica.AvionDisponible;
import Logica.Vuelo;
import java.util.List;
import Datos.AvionDisponibleJpaController;
import Datos.HorarioJpaController;
import Datos.RutaJpaController;
import Datos.VueloJpaController;
import Logica.Horario;
import Logica.Ruta;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author sergi
 */
public class Model {
    public DBQuerys db;
    
    public Model(){
       db = new DBQuerys();
    }
    
    public List<AvionDisponible> listadoAviones(){
        List<AvionDisponible> lista= new ArrayList<>();
        try{
           lista=db.AvionSearch("");
        }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error al Obtener Aviones",JOptionPane.PLAIN_MESSAGE);
        }
        return lista;
    }
    
    public List<Horario> listadoHorarios(){
        List<Horario> lista= new ArrayList<>();
        try{
            HorarioJpaController horarioDao= new HorarioJpaController(db.db.EntityManager);
            lista=horarioDao.findHorarioEntities();
        }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error al Obtener Horarios",JOptionPane.PLAIN_MESSAGE);
        }
        return lista;
    }
    
    public List<Ruta> listadoRutas(){
        List<Ruta> lista= new ArrayList<>();
        try{
            RutaJpaController rutaDao= new RutaJpaController(this.db.db.EntityManager);
            lista=rutaDao.findRutaEntities();
        }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error al Obtener Rutas",JOptionPane.PLAIN_MESSAGE);
        }
        return lista;
    }
    
    public void agregarVuelo(Vuelo vuelo) throws Exception{
        try{
            db.VueloAdd(vuelo);
            JOptionPane.showMessageDialog(null, "Vuelo agregado exitosamente", "VUELO AGREGADO",JOptionPane.PLAIN_MESSAGE);
        }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error al agregar Vuelo",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
