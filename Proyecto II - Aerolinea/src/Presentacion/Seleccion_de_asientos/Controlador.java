package Presentacion.Seleccion_de_asientos;

import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monica
 */
public class Controlador {
    Model model;
   public Controlador(Model model){
        this.model=model;
    }
    
   public Asiento buscar(int x, int y){
       try{
       return this.model.buscarAsiento(x, y);
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       return null;
   }
   public void bloquearAsientos(List<Asiento> lista){
       this.model.bloquearAsientos(lista);
   }
   public void cambiarTipo(Asiento imagen){
       try{
           this.model.cambiarTipo(imagen);
       }catch(Exception se){
           System.out.println(se.getMessage());
       }
       
   }
}
