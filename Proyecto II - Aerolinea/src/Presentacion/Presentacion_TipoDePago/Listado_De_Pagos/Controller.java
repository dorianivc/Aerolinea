/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_TipoDePago.Listado_De_Pagos;

import Datos.DBQuerys;
import Datos.TipodePagoJpaController;
import Logica.TipodePago;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sergi
 */
public class Controller {
    Model model;
    View view;
    DBQuerys db;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        db= new DBQuerys();
        view.setModel(model);
        view.setController(this);
    }
    
    public void buscar(String tipoDePago){
        try{
            TipodePagoJpaController tipoPagoDao = new TipodePagoJpaController(db.db.EntityManager);
            List<TipodePago> lista = db.tipoDePagoSearch(tipoDePago);
            model.setTipos(lista);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public DBQuerys getDb() {
        return db;
    }

    public void setDb(DBQuerys db) {
        this.db = db;
    }
    
    public void eliminar(int row){
        try{
            TipodePagoJpaController tipoPagoDao = new TipodePagoJpaController(db.db.EntityManager);
            TipodePago pago = model.getTipos().get(row);
            tipoPagoDao.destroy(pago.getTipoDePago());
            JOptionPane.showMessageDialog(null, "ELiminado el metodo de pago con exito", "Eliminando el metodo de pago", JOptionPane.PLAIN_MESSAGE, null);
            view.update(null, null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.PLAIN_MESSAGE, null);
        }
    }
    
    public void show(){
        view.setVisible(true);
    }
    
    public void editar(int row){
//        personas.aplication.EdicionController.consultar(model.getPersonas().get(row).getId());
    }    
}
