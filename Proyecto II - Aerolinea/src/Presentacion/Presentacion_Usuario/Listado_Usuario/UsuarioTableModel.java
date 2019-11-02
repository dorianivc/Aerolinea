/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Presentacion_Usuario.Listado_Usuario;

import Logica.Ciudad;
import Logica.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sergi
 */
public class UsuarioTableModel extends AbstractTableModel {
    
  List<Usuario> usuarios;   

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

  
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }
    
    @Override    
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Id: ";
            case 1: return "Nombre: ";
            case 2: return "Apellido: ";
            case 3: return "Contraseña: ";
            case 4: return "Correo: ";
            case 5: return "Nacimiento: ";
            case 6: return "Direccion: ";
            case 7: return "Telefono: ";
            case 8: return "Celular: ";
            default: return "Admin: ";
        }        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Usuario  u =usuarios.get(rowIndex);
        switch(columnIndex){
            case 0: return u.getUsuario();
            case 1: return u.getNombre();
            case 2: return u.getApellidos();
            case 3: return u.getContrasena();
            case 4: return u.getCorreoElectronico();
            case 5: return u.getFechaNacimiento().toString();
            case 6: return u.getDireccion();
            case 7: return u.getTelefono();
            case 8: return u.getCelular();
            case 9: if( u.getAdmin()==1){
                return "SI";
            }else return "NO";
            default: return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int i){
        switch(i){
            default: return super.getColumnClass(i);
        }
    }
    
}
    