
package Presentacion.Presentacion_Pais.agregar_pais;

import Datos.PaisJpaController;
import Logica.Pais;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Model  extends Observable {
    public Datos.DBQuerys db;
    
    public Model(){
        db=new Datos.DBQuerys();
    }
    
    void agregarPais(String Pais, String nombre){
        Pais pais= new Pais(Pais);
        pais.setNombre(nombre);
        Icon icon= new ImageIcon(getClass().getResource("check_verde.png"));
        try{
            db.agregarPais(pais);
          
       
       JOptionPane.showMessageDialog(null, "Pais agregado satisfactoriamente", "Pais Agregado",JOptionPane.PLAIN_MESSAGE, icon);
        
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "Pais ya existe en los registros", "Pais Agregado",JOptionPane.PLAIN_MESSAGE, icon);
        }
    }
    
      public List<Pais> buscar(String nombre){
        List<Pais> result = db.PaisSearch(nombre);
         return result;
    }
      
      public Pais find (String key){
      PaisJpaController  paisDao = new  PaisJpaController(db.db.EntityManager);
     Pais p = paisDao.findPais(key);
     return p;
      }
    
      
  
    
    public void addObserver(Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();   
    }
    
        static Model the_instance;
    public static Model instance(){
        if (the_instance==null){
            the_instance = new Model();
        }
        return the_instance;
    }
}

    
