package Presentacion.Seleccion_de_asientos;






import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



    


public class Vista extends JFrame implements java.util.Observer {
 
    public Model modelo;
    public Controlador controller;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelPasillo;
    private javax.swing.JLabel jLabelVentana;
    private javax.swing.JLabel jLabelVentana1;
    public Vista() throws IOException{
       
        
      this.setTitle("SELECCION DE ASIENTOS DEL VUELO");
     
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelPasillo = new javax.swing.JLabel();
        jLabelVentana = new javax.swing.JLabel();
        jLabelVentana1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelPasillo.setText("Pasillo");

        jLabelVentana.setText("Ventana");

        jLabelVentana1.setText("Ventana");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVentana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelVentana1)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButtonAceptar)
                        .addGap(97, 97, 97)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabelPasillo)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVentana1)
                    .addComponent(jLabelVentana))
                .addGap(62, 62, 62)
                .addComponent(jLabelPasillo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar))
                .addGap(62, 62, 62))
        );
        
        
        
        
        
        
        this.setSize(400,400);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(Color.RED);
        this.addMouseListener(new MouseAdapter() 
        {
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Imagen imagen;
        try{
             imagen=buscarAsiento(e.getPoint());
             controller.cambiarTipo(imagen);
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
       
        refresh();
        }
            });
        
    }
 
    public Model getModelo() {
        return modelo;
    }

    public void setModelo(Model modelo) {
        this.modelo = modelo;
    }

    public Controlador getControl() {
        return controller;
    }
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        System.out.println("Presiono Aceptar, modificar codigo aquí");
    }                                              

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                                
        System.out.println("Presiono Cancelar, modificar codigo aquí");
    }     
    public void setControl(Controlador control) {
        this.controller = control;
    }
    
    @Override
   public void update(Observable o, Object arg){
       this.repaint();
   }
public void refresh(){
    this.repaint();
}
    public Imagen buscarAsiento(Point punto){
        Imagen asiento=null;
        try{
          asiento =this.controller.buscar(punto.x, punto.y);
        }catch(Exception es){
            System.out.println(es.getMessage());
        }
        
        System.out.println("Numero de asiento clickeado: "+asiento.getNumero_asiento());
        return asiento;
    }
    

    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.renderModel(modelo,g);
        
        
    }

    /*@Override
    public void update(Observable o, Object o1) {
    StringBuilder html=new StringBuilder();   
    this.renderModel(modelo, html);
    System.out.println(html);
    }*/
       private void JMenuFileExitMouseClicked(java.awt.event.MouseEvent evt) {                                           
       System.exit(0);
    }                                          

                    


    
    void renderModel(Model m, Graphics media){
     int ejeX=50;
     for(int i=0;i<modelo.filas;i++){
         int ejeY=50;
         for(int y=0;y<modelo.columnas;y++){
             media.drawImage(modelo.asientos[i][y].getImage(),modelo.asientos[i][y].getX(),modelo.asientos[i][y].getY(),this);
             ejeY+=25;
         }
         ejeX+=25;
     }
    
    
  }
    

}