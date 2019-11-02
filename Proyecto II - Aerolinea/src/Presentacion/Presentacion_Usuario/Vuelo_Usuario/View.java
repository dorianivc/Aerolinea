/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Vuelo_Usuario;

import Logica.Ciudad;
import Logica.Usuario;
import Logica.Vuelo;
import Presentacion.Presentacion_Vuelo.Listado_Vuelos_DIsponibles.VuelosTableModel;
import Presentacion.Seleccion_de_asientos.Asiento;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Gabriel
 */
public class View extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form View
     */
    public View() { 
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableVuelos = new javax.swing.JTable();
        jButtonEscoger = new javax.swing.JButton();
        Vuelos_Disponibles = new javax.swing.JLabel();
        DestinoComboBox = new javax.swing.JComboBox<>();
        BuscarButton = new javax.swing.JButton();
        OrigenComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bienvenido");

        TableVuelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableVuelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVuelosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableVuelos);

        jButtonEscoger.setText("Escoger");
        jButtonEscoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEscogerActionPerformed(evt);
            }
        });

        Vuelos_Disponibles.setText("Vuelos Disponibles");

        BuscarButton.setText("Buscar");

        jLabel1.setText("Origen:");

        jLabel2.setText("Destino: ");

        jButton1.setText("Ver reservaciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OrigenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DestinoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BuscarButton)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(Vuelos_Disponibles))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonEscoger)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Vuelos_Disponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DestinoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarButton)
                    .addComponent(OrigenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEscoger)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEscogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEscogerActionPerformed
        int fila=9;//aqui pasa las filas del avion
        int columna=8;//aqui pasa las columnas del avion
        List<Asiento> listaDeAsientoReservados= new ArrayList<>(); //Aqui tiene que traerse los asientos desde las reservas, crealos y pasarselos a la lista;
        Asiento prueba= new Asiento(34);
        listaDeAsientoReservados.add(prueba);
        System.out.println(prueba.toString());
        Presentacion.Seleccion_de_asientos.Model modelEscoger;
        Presentacion.Seleccion_de_asientos.Controlador controllerEscoger;
        Presentacion.Seleccion_de_asientos.Vista viewEscoger ;   
        List<Asiento> listaDeAsientoEscogidos;
        try {
             
             modelEscoger= new Presentacion.Seleccion_de_asientos.Model(fila, columna); 
             controllerEscoger= new Presentacion.Seleccion_de_asientos.Controlador(modelEscoger);
             controllerEscoger.bloquearAsientos(listaDeAsientoReservados);
             viewEscoger = new Presentacion.Seleccion_de_asientos.Vista() ;
             viewEscoger.setControl(controllerEscoger);
             viewEscoger.setModelo(modelEscoger);
             viewEscoger.setLocationRelativeTo(null);
             viewEscoger.setVisible(true);
             listaDeAsientoEscogidos=viewEscoger.asientos_seleccionados;
             
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButtonEscogerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void TableVuelosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVuelosMouseClicked
        if(evt.getClickCount()==2){
           int row =this.TableVuelos.getSelectedRow();
           ///no seeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
       }
    }//GEN-LAST:event_TableVuelosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarButton;
    private javax.swing.JComboBox<Ciudad> DestinoComboBox;
    private javax.swing.JComboBox<Ciudad> OrigenComboBox;
    private javax.swing.JTable TableVuelos;
    private javax.swing.JLabel Vuelos_Disponibles;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEscoger;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        String nombre="USUARIO NO LOGEADO";
        try{
            nombre=model.usuario.getNombre();
        }catch(Exception se){
            System.out.println(se.getMessage());
        }
        this.setTitle("Bienvenido "+nombre);
        List<Ciudad> lista = model.getCiudades();
        for(int i=0;i<lista.size();i++){
            this.DestinoComboBox.addItem(lista.get(i));
            this.OrigenComboBox.addItem(lista.get(i));
        }
        this.TableVuelos.setRowHeight(40);
        this.TableVuelos.setModel(new VuelosTableModel(model.getVuelos())); 
    }
    
    Model model;
    Controller controller;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    public Vuelo getValueat(int row){
        return model.getVuelos().get(row);
    }
    
    public Usuario getUsuario(){
        return model.getUsuario();
    }
}
