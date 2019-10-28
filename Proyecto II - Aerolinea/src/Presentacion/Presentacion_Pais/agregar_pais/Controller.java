
package Presentacion.Presentacion_Pais.agregar_pais;

import Logica.Pais;


public class Controller {
    public Model modelo;
    
   public Controller(){
       modelo= new Model();
   }
   
   public void agregarPais(String Pais, String nombre){
       modelo.agregarPais(Pais, nombre);
   }
  
    public void consultar(String key){
      
         
    }
}
