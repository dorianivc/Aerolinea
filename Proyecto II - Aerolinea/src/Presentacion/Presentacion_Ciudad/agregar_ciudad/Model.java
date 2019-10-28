/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.agregar_ciudad;

import Datos.DBQuerys;
import Logica.Ciudad;
import Logica.Pais;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import javax.swing.JOptionPane;


public class Model extends Observable{
    
public DBQuerys db;

public Model(){
    this.db= new DBQuerys();
}

public List<Pais> listaPaises() throws SQLException{
    List<Pais> lista = null;
    try{
       lista= db.listadoPaises(); 
    }catch(Exception es){
        System.out.println(es);
    }
    return lista;
}

void agregarCiudad(Ciudad ciudad){
    
    try{
        db.agregarCiudad(ciudad);
    }catch(Exception es){
        Object[] message={
            "Ciudad ya existe"
               
        };
          int option=JOptionPane.showConfirmDialog(null, message, "Datos Existente",JOptionPane.PLAIN_MESSAGE);
        if(option==JOptionPane.OK_OPTION){
            try{
            }catch(NumberFormatException e){
                
            }
    }
}
}

    static Model the_instance;
    public static Model instance(){
        if (the_instance==null){
            the_instance = new Model();
        }
        return the_instance;
    }
    
       public List<Ciudad> buscar(String nombre){
        List<Ciudad> result = db.CiudadSearch(nombre);
         return result;
    }

}
