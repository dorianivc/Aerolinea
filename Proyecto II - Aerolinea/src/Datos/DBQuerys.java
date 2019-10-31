/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.AvionDisponible;
import Logica.Ciudad;
import Logica.Horario;
import Logica.Pais;
import Logica.Ruta;
import Logica.TipodePago;
import Logica.Usuario;
import Logica.Vuelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBQuerys {

    public BaseDatosAWS db;

    public DBQuerys() {
        db = new BaseDatosAWS();
    }

    public List<Pais> listadoPaises() throws SQLException {
        Connection conn = db.conexion;
        Statement statement = null;
        List<Pais> lista = new ArrayList<>();
        try {

            System.out.println("Creating statement...");
            statement = conn.createStatement();
            String sql;
            sql = "select * from Aerolinea.Pais; ";
            System.out.println(sql);
            //STEP 5: Extract data from result set
            try (ResultSet rs = statement.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    String llave = rs.getString("pais");
                    String nombre = rs.getString("nombre");
                    //Display values
                    Pais pais = new Pais(llave);
                    pais.setNombre(nombre);
                    lista.add(pais);
                }
                //STEP 6: Clean-up environment

            }
            statement.close();

        } catch (SQLException se) {

        }

        return lista;

    }

    public int executeUpdate(String statement) {
        try {
            Statement stm = db.conexion.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public void agregarPais(Pais pais) throws SQLException, Exception {
        Connection conn = db.conexion;
        String sql = "insert into Aerolinea.Pais (pais, nombre) "
                + "values('%s','%s')";
        sql = String.format(sql, pais.getPais(), pais.getNombre());
        System.out.println(sql);
        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Pais ya existe");
        }
    }

    public void runTestQuery() throws SQLException {
        Connection conn = db.conexion;
        Statement statement = null;
        try {

            System.out.println("Creating statement...");
            statement = conn.createStatement();
            String sql;
            sql = "select * from Aerolinea.Pais; ";
            //STEP 5: Extract data from result set
            try (ResultSet rs = statement.executeQuery(sql)) {
                //STEP 5: Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    String atributo = rs.getString("pais");
                    String atributo2 = rs.getString("nombre");
                    //Display values
                    System.out.println("Informacion Pais de la base de datos en AWS--> " + "Codigo de Pais: " + atributo + " Nombre: " + atributo2);
                }
                //STEP 6: Clean-up environment
            }
            statement.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC

        } catch (Exception e) {
            //Handle errors for Class.forName

        } finally {
            //finally block used to close resources
            if (statement != null) {
                statement.close();
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }//end finally try
        }//end try
    }

    public void agregarCiudad(Ciudad ciudad) throws Exception {
        Connection conn = db.conexion;
        String sql = "insert into Aerolinea.Ciudad (ciudad, nombre, pais) "
                + "values('%s','%s','%s')";
        sql = String.format(sql, ciudad.getCiudad(), ciudad.getNombre(), ciudad.getPais().getPais());
        System.out.println(sql);
        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Pais ya existe");
        }
    }

    public List<Pais> PaisSearch(String nombre) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<Pais> resultado = new ArrayList<Pais>();
        try {
            statement = conn.createStatement();
            String sql = "select * from "
                    + "Aerolinea.Pais p  where pais like '%%%s%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("pais");
                String nombree = rs.getString("nombre");

                Pais pais = new Pais(llave);
                pais.setNombre(nombree);
                resultado.add(pais);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<Ciudad> CiudadSearch(String nombre) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<Ciudad> resultado = new ArrayList<Ciudad>();
        try {
            statement = conn.createStatement();
            String sql = "select * from "
                    + "Aerolinea.Ciudad c  where c.nombre like '%%%s%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("ciudad");
                String nombree = rs.getString("nombre");
                String pais = rs.getString("pais");

                Ciudad ciudad = new Ciudad();
                ciudad.setCiudad(llave);
                ciudad.setNombre(nombree);

                PaisJpaController paisDao = new PaisJpaController(db.EntityManager);
                Pais p = paisDao.findPais(pais);

                ciudad.setPais(p);
                resultado.add(ciudad);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<TipodePago> tipoDePagoSearch(String tipo) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<TipodePago> resultado = new ArrayList<TipodePago>();
        try {
            statement = conn.createStatement();
            String sql = "select * from "
                    + "Aerolinea.Tipo_de_Pago p  where tipo_de_pago like '%%%s%%'";
            sql = String.format(sql, tipo);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("tipo_de_pago");
                String descripcion = rs.getString("descripcion");

                TipodePago tipoPago = new TipodePago(llave);
                tipoPago.setDescripcion(descripcion);
                resultado.add(tipoPago);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void CiudadUpdate(Ciudad c) throws Exception {
        String sql = "update Aerolinea.Ciudad c  set c.nombre  ='%s'"
                + "where c.ciudad ='%s'";
        sql = String.format(sql, c.getNombre(), c.getCiudad());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal");
        }
    }

    public void PaisUpdate(Pais p) throws Exception {
        String sql = "update Aerolinea.Pais c  set c.nombre  ='%s'"
                + "where c.pais ='%s'";
        sql = String.format(sql, p.getNombre(), p.getPais());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal ");
        }
    }

    public List<Ruta> rutaNombrePaisSearch(String nombre) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<Ruta> resultado = new ArrayList<Ruta>();
        try {
            CiudadJpaController ciudadDao = new CiudadJpaController(this.db.EntityManager);
            statement = conn.createStatement();
            String sql = "select * from Aerolinea.Ruta r inner join  Aerolinea.Ciudad c on r.ciudad_llegada = c.ciudad ||"
                    + "r.ciudad_salida = c.ciudad where c.nombre like '%%%s%%%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                int llave = rs.getInt("ruta");
                Date anio = (Date) rs.getObject("duracion");
                String salida = rs.getString("ciudad_salida");
                String llegada = rs.getString("ciudad_llegada");

                Ruta ruta = new Ruta(llave);
                ruta.setDuracion(anio);
                ruta.setCiudadSalida(ciudadDao.findCiudad(salida));
                ruta.setCiudadLlegada(ciudadDao.findCiudad(llegada));
                resultado.add(ruta);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void UsuarioUpdate(Usuario u) throws Exception {
        String sql = "update Aerolinea.Usuario u  set u.contrasena  ='%s', u.nombre ='%s',  u.apellidos ='%s', "
                + " u.correo_electronico ='%s',  u.direccion ='%s',  u.telefono ='%s', "
                + " u.celular ='%s' "
                + "where u.usuario ='%s'";
        sql = String.format(sql, u.getContrasena(), u.getNombre(), u.getApellidos(), u.getCorreoElectronico(),
                u.getDireccion(), u.getTelefono(), u.getCelular(), u.getUsuario());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal ");
        }
    }

    public void AvionAdd(AvionDisponible a) throws Exception {
        String sql = "insert into Aerolinea.Avion_Disponible (codigo_matricula, ano, modelo, marca,  "
                + " filas, columnas, cantidad_de_pasajeros) "
                + " values('%s','%s','%s','%s','%s','%s','%s')";
        sql = String.format(sql, a.getCodigoMatricula(), a.getAno().getYear(), a.getModelo(), a.getMarca(), a.getFilas(),
                a.getColumnas(), a.getCantidadDePasajeros());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Avion ya existe");
        }
    }

    public List<AvionDisponible> AvionSearch(String nombre) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<AvionDisponible> resultado = new ArrayList<AvionDisponible>();
        try {
            statement = conn.createStatement();
            String sql = "select * from "
                    + "Aerolinea.Avion_Disponible a  where a.codigo_matricula like '%%%s%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("codigo_matricula");
                int ano = Integer.parseInt(rs.getString("ano"));
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                int filas = Integer.parseInt(rs.getString("filas"));
                int columnas = Integer.parseInt(rs.getString("columnas"));
                int can = Integer.parseInt(rs.getString("cantidad_de_pasajeros"));
                Date fecha = new Date(ano, 0, 1);
                AvionDisponible avion = new AvionDisponible();
                avion.setCodigoMatricula(llave);
                avion.setModelo(modelo);
                avion.setMarca(marca);
                avion.setAno(fecha);
                avion.setFilas(filas);
                avion.setColumnas(columnas);
                avion.setCantidadDePasajeros(can);

                resultado.add(avion);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void AvionDelete(AvionDisponible a) throws Exception {
        String sql = "delete from Aerolinea.Avion_Disponible where codigo_matricula='%s'";
        sql = String.format(sql, a.getCodigoMatricula());
        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal");
        }
    }

    public void AvionUpdate(AvionDisponible a) throws Exception {
        String sql = "update Aerolinea.Avion_Disponible a  set a.ano  ='%s', a.modelo ='%s',  a.marca ='%s', "
                + " a.filas ='%s',  a.columnas ='%s',  a.cantidad_de_pasajeros ='%s' "
                + "where a.codigo_matricula ='%s'";
        sql = String.format(sql, a.getAno().getYear(), a.getModelo(), a.getMarca(), a.getFilas(),
                a.getColumnas(), a.getCantidadDePasajeros(), a.getCodigoMatricula());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal ");
        }
    }

    public List<Usuario> UsuarioSearch(String nombre) {
        Statement statement = null;
        Connection conn = db.conexion;
        List<Usuario> resultado = new ArrayList<Usuario>();
        try {
            statement = conn.createStatement();
            String sql = "select * from "
                    + "Aerolinea.Usuario u  where u.usuario like '%%%s%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("usuario");
                String nombree = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String password = rs.getString("contrasena");
                String correo = rs.getString("correo_electronico");
                String nacimiento = rs.getString("fecha_nacimiento");
                int year = Integer.parseInt(nacimiento.substring(0, 4));
                year = year - 1900;
                int month = Integer.parseInt(nacimiento.substring(5, 7));
                month--;
                int day = Integer.parseInt(nacimiento.substring(8, 10));
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String cel = rs.getString("celular");
                short admin = 0;

                Date nacimiento2 = new Date(year, month, day);

                Usuario user = new Usuario(llave, password, nombree, apellidos, correo, nacimiento2, direccion, telefono, cel, admin);

                resultado.add(user);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<Vuelo> VueloSearch(String nombre) throws Exception {
        Statement statement = null;
        Connection conn = db.conexion;
        List<Vuelo> resultado = new ArrayList<Vuelo>();
        try {
            statement = conn.createStatement();
            String sql = "select * from Aerolinea.Vuelo v "
                    + "where v.vuelo like '%%%s%%'";
            sql = String.format(sql, nombre);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                String llave = rs.getString("vuelo");
                String horario = rs.getString("horario");
                String avionn = rs.getString("avion_asignado");
                int ruta = Integer.parseInt(rs.getString("ruta_asignada"));

                AvionDisponible a = new AvionDisponible();
                // AvionDisponibleJpaController aDao = new  AvionDisponibleJpaController(db.EntityManager);
                // a= aDao.findAvionDisponible(avionn);
                a = AvionGet(avionn);

                Ruta r = new Ruta();
                RutaJpaController rDao = new RutaJpaController(db.EntityManager);
                r = rDao.findRuta(ruta);

                Horario h = new Horario();
                HorarioJpaController hDao = new HorarioJpaController(db.EntityManager);
                h = hDao.findHorario(horario);

                Vuelo v = new Vuelo();
                v.setVuelo(llave);
                v.setAvionAsignado(a);
                v.setRutaAsignada(r);
                v.setHorario(h);

                resultado.add(v);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public AvionDisponible AvionGet(String id) throws Exception {
        Statement statement = null;
        Connection conn = db.conexion;
        statement = conn.createStatement();
        String sql = "select * from "
                + " Aerolinea.Avion_Disponible a where a.codigo_matricula like '%%%s%%' ";
        sql = String.format(sql, id);
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            String llave = rs.getString("codigo_matricula");
            int ano = Integer.parseInt(rs.getString("ano"));
            String modelo = rs.getString("modelo");
            String marca = rs.getString("marca");
            int filas = Integer.parseInt(rs.getString("filas"));
            int columnas = Integer.parseInt(rs.getString("columnas"));
            int can = Integer.parseInt(rs.getString("cantidad_de_pasajeros"));
            Date fecha = new Date(ano, 0, 1);
            AvionDisponible avion = new AvionDisponible();
            avion.setCodigoMatricula(llave);
            avion.setModelo(modelo);
            avion.setMarca(marca);
            avion.setAno(fecha);
            avion.setFilas(filas);
            avion.setColumnas(columnas);
            avion.setCantidadDePasajeros(can);
            return avion;
        } else {
            throw new Exception("avion no Existe");
        }
    }

    public void VueloUpdate(Vuelo v) throws Exception {
        String sql = " update Aerolinea.Vuelo v "
                + "set v.horario  ='%s', v.avion_asignado ='%s',  v.ruta_asignada ='%s'"
                + " where v.vuelo ='%s'";
        sql = String.format(sql, v.getHorario().getHorario(), v.getAvionAsignado().getCodigoMatricula(),
                v.getRutaAsignada().getRuta(), v.getVuelo());

        int count = executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Algo anda mal ");

        }
    }
}
