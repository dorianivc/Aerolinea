
package Presentacion.Presentacion_Pais.agregar_pais;


public class Controller {
    public Model modelo;
    
   public Controller(){
       modelo= new Model();
   }
   
   public void agregarPais(String Pais, String nombre){
       modelo.agregarPais(Pais, nombre);
   }
}
