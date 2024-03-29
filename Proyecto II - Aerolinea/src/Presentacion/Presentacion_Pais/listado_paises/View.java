/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Pais.listado_paises;

import Logica.Pais;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Monica
 */
public class View extends javax.swing.JFrame implements Observer{
 public static Presentacion.Presentacion_Pais.agregar_pais.Controller PaisController;
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
        Tablapaises = new javax.swing.JTable();
        nombre = new javax.swing.JTextField();
        jLabelNombre_Pais = new javax.swing.JLabel();
        JButtonBuscar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jLabel_Paises = new javax.swing.JLabel();
        jButtonModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTADO DE PAISES");

        Tablapaises.setModel(new javax.swing.table.DefaultTableModel(
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
        Tablapaises.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablapaisesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tablapaises);

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        jLabelNombre_Pais.setText("Nombre del Pais: ");

        JButtonBuscar.setText("Buscar");
        JButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonBuscarActionPerformed(evt);
            }
        });

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jLabel_Paises.setText("Paises");

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNombre_Pais)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(JButtonBuscar)))
                        .addGap(40, 40, 40))))
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel_Paises)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_Paises)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre_Pais)
                    .addComponent(JButtonBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        Presentacion.Presentacion_Pais.agregar_pais.View agregarPais= new Presentacion.Presentacion_Pais.agregar_pais.View();
        agregarPais.setVisible(true);
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void JButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonBuscarActionPerformed
          controller.buscar(nombre.getText());
    }//GEN-LAST:event_JButtonBuscarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int row = this.Tablapaises.getSelectedRow();
        controller.eliminar(row);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        int row = this.Tablapaises.getSelectedRow();
        Presentacion.Presentacion_Pais.Modificar.Model model = new Presentacion.Presentacion_Pais.Modificar.Model(this.getValueat(row));
        Presentacion.Presentacion_Pais.Modificar.View view = new Presentacion.Presentacion_Pais.Modificar.View();
        Presentacion.Presentacion_Pais.Modificar.Controller controller = new Presentacion.Presentacion_Pais.Modificar.Controller(model,view);
        view.setVisible(true);
      
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void TablapaisesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablapaisesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablapaisesMouseClicked

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

      @Override
    public void update(Observable o, Object arg) {
        this.Tablapaises.setRowHeight(40);
        this.Tablapaises.setModel(new PaisTableModel(model.getPaises()));
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
    
     public Pais getValueat(int row){
        return model.getPaises().get(row);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonBuscar;
    private javax.swing.JTable Tablapaises;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabelNombre_Pais;
    private javax.swing.JLabel jLabel_Paises;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
