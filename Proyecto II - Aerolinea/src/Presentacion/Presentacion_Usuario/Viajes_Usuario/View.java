/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Viajes_Usuario;

import Datos.ReservaJpaController;
import Logica.Reserva;
import Logica.Usuario;
import Logica.Viaje;
import Logica.Vuelo;
import Presentacion.Presentacion_Viajes.Listado_Viajes.ViajeTableModel;
import Presentacion.Seleccion_de_asientos.Asiento;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaViajes = new javax.swing.JTable();
        escoger = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Viajes Disponibles");

        TablaViajes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaViajes);

        escoger.setText("Escoger");
        escoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escogerActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(escoger)
                        .addGap(18, 18, 18)
                        .addComponent(cancelar)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(escoger)
                    .addComponent(cancelar))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void escogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escogerActionPerformed
        int row=this.TablaViajes.getSelectedRow();
        Viaje viaje=getValueat(row);
        Vuelo vueloAux=this.model.vuelo;
        try {
            System.out.println("Filas: "+ vueloAux.getAvionAsignado().getFilas() + "Columnas: "+ vueloAux.getAvionAsignado().getColumnas());
            System.out.println("Precio del vuelo: "+ viaje.getPrecio());
            Presentacion.Seleccion_de_asientos.Model model=new Presentacion.Seleccion_de_asientos.Model(vueloAux.getAvionAsignado().getFilas(),vueloAux.getAvionAsignado().getColumnas());
            model.viaje=viaje;
            model.vuelo=vueloAux;
            model.usuario=this.controller.model.usuario;
            ReservaJpaController reservaDao= new  ReservaJpaController(this.model.db.db.EntityManager);
            List<Reserva> lista=reservaDao.findReservaEntities();
            List<Asiento> lista_de_asientos_reservados= new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                if(Objects.equals(lista.get(i).getViaje().getViaje(), viaje.getViaje())){
                    int numAsiento=Integer.parseInt(lista.get(i).getNumeroAsiento());
                    lista_de_asientos_reservados.add(new Asiento(numAsiento));
                }   
            }
            Presentacion.Seleccion_de_asientos.Controlador controller= new Presentacion.Seleccion_de_asientos.Controlador(model);
            controller.bloquearAsientos(lista_de_asientos_reservados);
            Presentacion.Seleccion_de_asientos.Vista view= new Presentacion.Seleccion_de_asientos.Vista();
            view.setModelo(model);
            view.setControl(controller);
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_escogerActionPerformed

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
    private javax.swing.JTable TablaViajes;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton escoger;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

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
    
    public Viaje getValueat(int row){
        return model.getViajes().get(row);
    }
    
    public Usuario getUsuario(){
        return model.getUsuario();
    }

        @Override
    public void update(Observable o, Object arg) {
        this.TablaViajes.setRowHeight(40);
        this.TablaViajes.setModel(new ViajeTableModel(model.getViajes())); 
    }

}
