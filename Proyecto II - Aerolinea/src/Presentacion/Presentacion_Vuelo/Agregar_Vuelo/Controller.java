/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Vuelo.Agregar_Vuelo;

import Logica.AvionDisponible;
import Logica.Horario;
import Logica.Ruta;
import Logica.Vuelo;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
    Model modelo;
    public Controller(){
        modelo= new Model();
    }
    
    public List<AvionDisponible> getListadoAvionesDeBD(){
        return this.modelo.listadoAviones();
    }
    
    public List<Horario> getListadoHorarioDeBD(){
        return this.modelo.listadoHorarios();
    }
    
    public List<Ruta> getListadoRutadeBD(){
        return this.modelo.listadoRutas();
    }
    
    public void agregarVuelo(Vuelo vuelo) throws Exception{
        try{
            this.modelo.agregarVuelo(vuelo);
           
        }catch(Exception se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Error al agregar Vuelo",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
}
