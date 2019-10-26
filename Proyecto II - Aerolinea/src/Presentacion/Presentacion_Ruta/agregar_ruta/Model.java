/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Ruta.agregar_ruta;

import Datos.CiudadJpaController;
import Datos.DBQuerys;
import Datos.RutaJpaController;
import Logica.Ciudad;
import Logica.Ruta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monica
 */
public class Model {
    public DBQuerys db;
    
    public Model(){
        db= new DBQuerys();
    }
    
   public void agregarRuta(Ruta nuevaRuta){
        RutaJpaController rutaDao= new RutaJpaController(db.db.EntityManager);
        try{
            rutaDao.create(nuevaRuta);
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
    }
    
    public List<Ciudad> listadoCiudades(){
        CiudadJpaController ciudadDao= new CiudadJpaController(db.db.EntityManager);
        List<Ciudad> lista=new ArrayList<>();
        try{
            lista=ciudadDao.findCiudadEntities();
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
        return lista;
    }
}
