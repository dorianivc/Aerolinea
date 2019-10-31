/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Avion.Agregar_Avion;

import Logica.AvionDisponible;
import java.util.Date;

public class Controller {

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public Model model;
    
    public Controller(){
        model = new Model();
    }
    
    public void agregarAvion(AvionDisponible avion){
        model.agregarAvion(avion);
    }
    
    public void modificarAvion(String Matricula,Date anio, String modelo,String marca,int filas, int columnas){
     //   model.modificarAvion(Matricula, anio, modelo, marca, filas, columnas);
    }
    
    public void eliminarAvion(String Matricula){
    //    model.eliminarAvion(Matricula);
    }
}
