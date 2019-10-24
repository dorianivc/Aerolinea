/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "Avion_Disponible")
@NamedQueries({
    @NamedQuery(name = "AvionDisponible.findAll", query = "SELECT a FROM AvionDisponible a")
    , @NamedQuery(name = "AvionDisponible.findByCodigoMatricula", query = "SELECT a FROM AvionDisponible a WHERE a.codigoMatricula = :codigoMatricula")
    , @NamedQuery(name = "AvionDisponible.findByAno", query = "SELECT a FROM AvionDisponible a WHERE a.ano = :ano")
    , @NamedQuery(name = "AvionDisponible.findByModelo", query = "SELECT a FROM AvionDisponible a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "AvionDisponible.findByMarca", query = "SELECT a FROM AvionDisponible a WHERE a.marca = :marca")
    , @NamedQuery(name = "AvionDisponible.findByFilas", query = "SELECT a FROM AvionDisponible a WHERE a.filas = :filas")
    , @NamedQuery(name = "AvionDisponible.findByColumnas", query = "SELECT a FROM AvionDisponible a WHERE a.columnas = :columnas")
    , @NamedQuery(name = "AvionDisponible.findByCantidadDePasajeros", query = "SELECT a FROM AvionDisponible a WHERE a.cantidadDePasajeros = :cantidadDePasajeros")})
public class AvionDisponible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_matricula")
    private String codigoMatricula;
    @Column(name = "ano")
    @Temporal(TemporalType.DATE)
    private Date ano;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "filas")
    private Integer filas;
    @Column(name = "columnas")
    private Integer columnas;
    @Column(name = "cantidad_de_pasajeros")
    private Integer cantidadDePasajeros;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avionAsignado")
    private List<Vuelo> vueloList;

    public AvionDisponible() {
    }

    public AvionDisponible(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(String codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    public Integer getColumnas() {
        return columnas;
    }

    public void setColumnas(Integer columnas) {
        this.columnas = columnas;
    }

    public Integer getCantidadDePasajeros() {
        return cantidadDePasajeros;
    }

    public void setCantidadDePasajeros(Integer cantidadDePasajeros) {
        this.cantidadDePasajeros = cantidadDePasajeros;
    }

    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMatricula != null ? codigoMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvionDisponible)) {
            return false;
        }
        AvionDisponible other = (AvionDisponible) object;
        if ((this.codigoMatricula == null && other.codigoMatricula != null) || (this.codigoMatricula != null && !this.codigoMatricula.equals(other.codigoMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.AvionDisponible[ codigoMatricula=" + codigoMatricula + " ]";
    }
    
}
