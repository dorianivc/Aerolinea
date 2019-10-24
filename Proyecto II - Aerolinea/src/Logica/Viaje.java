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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Viaje")
@NamedQueries({
    @NamedQuery(name = "Viaje.findAll", query = "SELECT v FROM Viaje v")
    , @NamedQuery(name = "Viaje.findByViaje", query = "SELECT v FROM Viaje v WHERE v.viaje = :viaje")
    , @NamedQuery(name = "Viaje.findByFecha", query = "SELECT v FROM Viaje v WHERE v.fecha = :fecha")})
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "viaje")
    private Integer viaje;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje")
    private List<Reserva> reservaList;
    @JoinColumn(name = "vuelo", referencedColumnName = "vuelo")
    @ManyToOne(optional = false)
    private Vuelo vuelo;

    public Viaje() {
    }

    public Viaje(Integer viaje) {
        this.viaje = viaje;
    }

    public Integer getViaje() {
        return viaje;
    }

    public void setViaje(Integer viaje) {
        this.viaje = viaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viaje != null ? viaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaje)) {
            return false;
        }
        Viaje other = (Viaje) object;
        if ((this.viaje == null && other.viaje != null) || (this.viaje != null && !this.viaje.equals(other.viaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Viaje[ viaje=" + viaje + " ]";
    }
    
}