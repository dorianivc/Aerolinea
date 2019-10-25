
package Presentacion.Presentacion_Pais.agregar_pais;

import Logica.Pais;

public class Model {
    public Datos.DBQuerys db;
    
    public Model(){
        db=new Datos.DBQuerys();
    }
    
    void agregarPais(String Pais, String nombre){
        Pais pais= new Pais(Pais);
        pais.setNombre(nombre);
        try{
            db.agregarPais(pais);
           }catch(Exception ex){
               System.out.println(ex);
           }
        }
    
    
    
    }
    
