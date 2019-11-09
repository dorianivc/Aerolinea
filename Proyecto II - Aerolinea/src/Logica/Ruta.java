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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "Ruta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r")
    , @NamedQuery(name = "Ruta.findByRuta", query = "SELECT r FROM Ruta r WHERE r.ruta = :ruta")
    , @NamedQuery(name = "Ruta.findByDuracion", query = "SELECT r FROM Ruta r WHERE r.duracion = :duracion")})
public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ruta")
    private Integer ruta;
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @JoinColumn(name = "ciudad_salida", referencedColumnName = "ciudad")
    @ManyToOne(optional = false)
    private Ciudad ciudadSalida;
    @JoinColumn(name = "ciudad_llegada", referencedColumnName = "ciudad")
    @ManyToOne(optional = false)
    private Ciudad ciudadLlegada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutaAsignada")
    private List<Vuelo> vueloList;

    public Ruta() {
    }

    public Ruta(Integer ruta) {
        this.ruta = ruta;
    }

    public Integer getRuta() {
        return ruta;
    }

    public void setRuta(Integer ruta) {
        this.ruta = ruta;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public Ciudad getCiudadSalida() {
        return ciudadSalida;
    }

    public void setCiudadSalida(Ciudad ciudadSalida) {
        this.ciudadSalida = ciudadSalida;
    }

    public Ciudad getCiudadLlegada() {
        return ciudadLlegada;
    }

    public void setCiudadLlegada(Ciudad ciudadLlegada) {
        this.ciudadLlegada = ciudadLlegada;
    }

    @XmlTransient
    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruta != null ? ruta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.ruta == null && other.ruta != null) || (this.ruta != null && !this.ruta.equals(other.ruta))) {
            return false;
        }
        return true;
    }

        @Override
    public String toString() {
       Integer rute= this.ruta;
       String ruta= rute.toString();
       return ruta+" - "+this.ciudadSalida.getCiudad()+" - "+this.ciudadLlegada.getCiudad();}
    
}
