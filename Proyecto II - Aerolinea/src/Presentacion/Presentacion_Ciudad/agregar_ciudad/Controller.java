/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ciudad.agregar_ciudad;

import Logica.Ciudad;
import Logica.Pais;
import java.sql.SQLException;
import java.util.List;


public class Controller {
    Model modelo;
    public Controller(){
        this.modelo= new Model();
        
    }
    
    public List<Pais> getListadoPaises() throws SQLException{
        return modelo.listaPaises();
    }
    
    public void agregarCiudad(Ciudad ciudad){
        modelo.agregarCiudad(ciudad);
}

}

