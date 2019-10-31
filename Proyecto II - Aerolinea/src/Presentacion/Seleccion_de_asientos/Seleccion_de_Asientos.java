package Presentacion.Seleccion_de_asientos;





import Presentacion.Seleccion_de_asientos.Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template archivoFisicoOcupado, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Monica
 */
public class Seleccion_de_Asientos {
   
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      
       Model model= new Model(9,9);//AQUI ES DONDE LE PASO LA CANTIDAD DE FILAS Y COLUMNAS
       Controlador controller= new Controlador(model);
       Vista vista= new Vista();
       vista.setModelo(model);
       vista.setControl(controller);
       vista.setLocationRelativeTo(null);
       vista.setVisible(true);
       JOptionPane.showMessageDialog(null,"Clickee sobre los asientos que desea reservar una vez, cambiaran de color negro a blanco."+ "\n"+"Si deseea revertir la eleccion, solamente vuelva a dar click sobre el asiento para desmarcarlo. Una vez seleccionados correctamente, presione aceptar para continuar"+ "\n"+ "**importante** DESPUES DE ACEPTAR LOS ASIENTOS, NO EXISTE POSIBILIDAD DE MODIFICAR LA SELECCION DE NUEVO **importante**" , "INSTRUCCIONES DE ELECCION DE ASIENTO ",JOptionPane.PLAIN_MESSAGE);
       
   
    }
    
}
