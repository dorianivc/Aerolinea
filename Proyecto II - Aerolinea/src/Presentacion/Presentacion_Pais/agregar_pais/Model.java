
package Presentacion.Presentacion_Pais.agregar_pais;

import Logica.Pais;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Model {
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
}

    
