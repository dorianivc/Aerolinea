/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Agregar_Avion;

import Datos.AvionDisponibleJpaController;
import Logica.AvionDisponible;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Model {
    public Datos.DBQuerys db;
    
    public Model(){
        db = new Datos.DBQuerys();
    }
    
    void agregarAvion(AvionDisponible avion){
        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
        AvionDisponibleJpaController avionDao = new AvionDisponibleJpaController(db.db.EntityManager);
        try{
           avionDao.create(avion);
           JOptionPane.showMessageDialog(null, "Avión agregado con éxito", "Avion agregado", JOptionPane.PLAIN_MESSAGE, icon);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Avion no agregado", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    void modificarAvion(String Matricula,Date anio, String modelo,String marca,int filas, int columnas){
//        AvionDisponible avion= new AvionDisponible(Matricula);
//        avion.setAno(anio);
//        avion.setModelo(modelo);
//        avion.setMarca(marca);
//        avion.setFilas(filas);
//        avion.setColumnas(columnas);
//        avion.setCantidadDePasajeros(columnas*filas);
//        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
//        try{
//           AvionDisponibleJpaController avionDao = new AvionDisponibleJpaController(db.db.EntityManager);
//           avionDao.edit(avion);
//           JOptionPane.showMessageDialog(null, "Avion editado con éxito", "Editando avion", JOptionPane.PLAIN_MESSAGE, icon);
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
//        }
    }
    
    void eliminarAvion(String Matricula){
//        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
//        try{
//           AvionDisponibleJpaController avionDao = new AvionDisponibleJpaController(db.db.EntityManager);
//           avionDao.destroy(Matricula);
//           JOptionPane.showMessageDialog(null, "Avion eliminado con éxito", "Eliminando Avion", JOptionPane.PLAIN_MESSAGE, icon);
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
//        }
    }
    
}
