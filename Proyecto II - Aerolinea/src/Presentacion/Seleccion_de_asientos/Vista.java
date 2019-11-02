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
import java.util.List;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar jMenuBarAyuda;
    private javax.swing.JMenuItem jMenuItemInstrucciones;
    public List<Asiento> asientos_seleccionados;
    public Vista() throws IOException{
       this.asientos_seleccionados= new ArrayList<>();
       
       
       
       
       
       
        
      this.setTitle("SELECCION DE ASIENTOS DEL VUELO");
  
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelPasillo = new javax.swing.JLabel();
        jLabelVentana = new javax.swing.JLabel();
        jLabelVentana1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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

        jMenu1.setText("Ayuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Instrucciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelVentana)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelVentana1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jButtonAceptar)
                                .addGap(97, 97, 97)
                                .addComponent(jButtonCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabelPasillo)))
                        .addGap(0, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVentana1)
                    .addComponent(jLabelVentana))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
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
        clickAsiento(e);
        }
            });
        
    }

    public List<Asiento> getAsientos_seleccionados() {
        return asientos_seleccionados;
    }

    public void setAsientos_seleccionados(List<Asiento> asientos_seleccionados) {
        this.asientos_seleccionados = asientos_seleccionados;
    }
    
    
  private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {  
      refresh();
      JOptionPane.showMessageDialog(null,"Clickee sobre los asientos que desea reservar una vez, cambiaran de color negro a blanco."+ "\n"+"Si deseea revertir la eleccion, solamente vuelva a dar click sobre el asiento para desmarcarlo. Una vez seleccionados correctamente, presione aceptar para continuar"+ "\n"+ "**importante** DESPUES DE ACEPTAR LOS ASIENTOS, NO EXISTE POSIBILIDAD DE MODIFICAR LA SELECCION DE NUEVO **importante**" , "INSTRUCCIONES DE ELECCION DE ASIENTO ",JOptionPane.PLAIN_MESSAGE);
       
    }  
        
 public void clickAsiento(MouseEvent e){
     Asiento imagen;
        try{
             imagen=buscarAsiento(e.getPoint());
             if(!imagen.getVendido()){
                 
             if(this.asientos_seleccionados.contains(imagen)){
              this.asientos_seleccionados.remove(imagen);
                 
             }else if(!this.asientos_seleccionados.contains(imagen)){
                 this.asientos_seleccionados.add(imagen);
             }
                 
             controller.cambiarTipo(imagen);
             if(this.asientos_seleccionados.isEmpty()){
                 System.out.println("Lista Vacia");
             }else{
                 for(int i=0;i<this.asientos_seleccionados.size();i++){
                 System.out.println(this.asientos_seleccionados.get(i).toString());
             } 
             }
            
                }
             
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
       
        refresh();
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
        String mensaje;
        if(this.asientos_seleccionados.isEmpty()){
         mensaje="Seleccione un asiento para poder continuar";
        }else{
        System.out.println("Presiono Aceptar, modificar codigo aquí");
        
        mensaje="Usted ha elegido los asientos: ";
        for(int i=0;i<this.asientos_seleccionados.size();i++){
           Integer num=this.asientos_seleccionados.get(i).getNumero_asiento();
           mensaje= mensaje + num.toString();
           if(i!=this.asientos_seleccionados.size()-1){
               mensaje=mensaje +", ";
            }
           else{
               mensaje=mensaje +".";
           }         
        }
        mensaje=mensaje+"\n"+"Para un total de: ";
        Integer numeroAsientos=this.asientos_seleccionados.size();
        mensaje=mensaje+ numeroAsientos.toString()+ " asientos reservados";
        
        controller.bloquearAsientos(asientos_seleccionados);
        this.asientos_seleccionados.clear();
        JOptionPane.showMessageDialog(null,mensaje , "ACEPTAR ASIENTOS",JOptionPane.PLAIN_MESSAGE);
        refresh(); //
        this.dispose();
        
        }
    }                                              

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                                
       JOptionPane.showMessageDialog(null,"Ha cancelado la eleccion de asientos" , "OPERACION CANCELADA ",JOptionPane.PLAIN_MESSAGE);
       this.dispose();
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
    public Asiento buscarAsiento(Point punto){
        Asiento asiento=null;
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
     
     for(int i=0;i<modelo.filas;i++){
         
         for(int y=0;y<modelo.columnas;y++){
             media.drawImage(modelo.asientos[i][y].getImage(),modelo.asientos[i][y].getX(),modelo.asientos[i][y].getY(),this);
             
         }
       
     }
    
    
  }
    

}