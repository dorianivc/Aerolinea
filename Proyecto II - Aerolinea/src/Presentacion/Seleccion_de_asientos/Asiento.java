package Presentacion.Seleccion_de_asientos;





import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


public class Asiento extends ImageIcon  {
    private int numero_asiento;
    private int x;
    private int y;
    private Boolean vendido;
    public BufferedImage imageQ;

    @Override
    public String toString() {
        return "Imagen{" + "numero_asiento=" + numero_asiento + ", x=" + x + ", y=" + y + ", vendido=" + vendido + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asiento other = (Asiento) obj;
        if (this.numero_asiento != other.numero_asiento) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    public int getNumero_asiento() {
        return numero_asiento;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public void setNumero_asiento(int numero_asiento) {
        this.numero_asiento = numero_asiento;
    }

   
   
    public Asiento(BufferedImage image, int numAsiento, int x, int y){
        super(image);
        this.numero_asiento=numAsiento;
        this.x=x;
        this.y=y;
        this.vendido=false;
    }
    public Asiento(int numAsiento){
       super(new BufferedImage(1,1,1));
       this.numero_asiento=numAsiento;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
