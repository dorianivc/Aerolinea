package Presentacion.Seleccion_de_asientos;






import static Logica.Reserva_.usuario;
import Logica.Usuario;
import Logica.Viaje;
import Logica.Vuelo;
import Presentacion.Seleccion_de_asientos.Asiento;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Model extends Observable{
  

   
   public int filas;
   public int columnas;
   public Asiento[][] asientos;
   File archivoFisicoOcupado= new File("src/media/cuadrado_en_uso1.png");
   File archivoFisicoDisponible= new File("src/media/negro.png");
   BufferedImage ocupado= ImageIO.read(archivoFisicoOcupado);
   BufferedImage disponible= ImageIO.read(archivoFisicoDisponible);
   public Viaje viaje;
   public Vuelo vuelo;
   public Usuario usuario;

    public int getFilas() {
        return filas;
    }
    public void bloquearAsientos(List<Asiento> lista){
        for(int x=0;x<filas;x++){
            for(int y=0;y<columnas;y++){
                
                for(int i=0;i<lista.size();i++){
                    if(lista.get(i).getNumero_asiento()==(asientos[x][y].getNumero_asiento())){
                        asientos[x][y].setVendido(Boolean.TRUE);
                        asientos[x][y].setImage(ocupado);
                    }
                }
                
                
            }
        }
    }
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Asiento[][] getAsientos() {
        return asientos;
    }

    public void setAsientos(Asiento[][] asientos) {
        this.asientos = asientos;
    }

    public File getArchivoFisicoOcupado() {
        return archivoFisicoOcupado;
    }

    public void setArchivoFisicoOcupado(File archivoFisicoOcupado) {
        this.archivoFisicoOcupado = archivoFisicoOcupado;
    }

    public File getArchivoFisicoDisponible() {
        return archivoFisicoDisponible;
    }

    public void setArchivoFisicoDisponible(File archivoFisicoDisponible) {
        this.archivoFisicoDisponible = archivoFisicoDisponible;
    }

    public BufferedImage getOcupado() {
        return ocupado;
    }

    public void setOcupado(BufferedImage ocupado) {
        this.ocupado = ocupado;
    }

    public BufferedImage getDisponible() {
        return disponible;
    }

    public void setDisponible(BufferedImage disponible) {
        this.disponible = disponible;
    }
   
   public Model(int filas, int columnas) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
       this.filas=filas;
       this.columnas=columnas;
       this.viaje=null;
       this.vuelo=null;
   
     this.asientos= new Asiento[filas][columnas];
     
     int ejeX=75;
     int asiento=0;
     for(int i=0;i<filas;i++){
         int ejeY=60;
         if(i==(filas/2)){
             ejeX+=35;
         }
         for(int y=0;y<columnas;y++){
             int plano=20;
          
             asientos[i][y]= new Asiento(disponible,asiento,ejeX,ejeY);
             ejeY+=25;
             asiento+=1;
         }
         ejeX+=25;
     }
 
   }

  public Asiento buscarAsiento(int X,int Y){
      for(int i=0;i<filas;i++){
         for(int y=0;y<columnas;y++){
             
             if(asientos[i][y].getX()+25>X && asientos[i][y].getY()+25>Y){
                 return asientos[i][y];
             }
         }
     }
      return null;
  }

  public void cambiarTipo(Asiento imagen){
      if(imagen.getImage()==disponible){
         imagen.setImage(ocupado); 
      }else if(imagen.getImage()==ocupado){
          imagen.setImage(disponible);
      }
      
  }
   
    public void start(){
        final int delay= 20;
        Runnable code = null;
       code = new Runnable(){@Override public void run(){
           while(true){
           setChanged();
           notifyObservers();
           try {
            Thread.sleep(delay);
                    }
            catch(InterruptedException ex) {
 
                }
                   
                   
          }
           }
       };
       Thread thread = new Thread(code);
       thread.start();
    }
                 
   


   @Override
   public void addObserver(java.util.Observer O){
    super.addObserver(O);
    setChanged();
    notifyObservers();
}


}

