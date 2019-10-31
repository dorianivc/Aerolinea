/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "Vuelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT v FROM Vuelo v")
    , @NamedQuery(name = "Vuelo.findByVuelo", query = "SELECT v FROM Vuelo v WHERE v.vuelo = :vuelo")})
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vuelo")
    private String vuelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vuelo")
    private List<Viaje> viajeList;
    @JoinColumn(name = "avion_asignado", referencedColumnName = "codigo_matricula")
    @ManyToOne(optional = false)
    private AvionDisponible avionAsignado;
    @JoinColumn(name = "horario", referencedColumnName = "horario")
    @ManyToOne(optional = false)
    private Horario horario;
    @JoinColumn(name = "ruta_asignada", referencedColumnName = "ruta")
    @ManyToOne(optional = false)
    private Ruta rutaAsignada;

    public Vuelo() {
    }

    public Vuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    @XmlTransient
    public List<Viaje> getViajeList() {
        return viajeList;
    }

    public void setViajeList(List<Viaje> viajeList) {
        this.viajeList = viajeList;
    }

    public AvionDisponible getAvionAsignado() {
        return avionAsignado;
    }

    public void setAvionAsignado(AvionDisponible avionAsignado) {
        this.avionAsignado = avionAsignado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vuelo != null ? vuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.vuelo == null && other.vuelo != null) || (this.vuelo != null && !this.vuelo.equals(other.vuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.vuelo + " -" + this.rutaAsignada.toString() + " " + this.horario.getDiaDeLaSemana().charAt(0) + this.horario.getDiaDeLaSemana().charAt(1);
    }

    public String gethorario() {

        return "Salida " + horario.getDiaDeLaSemana() + "-"
                + "Llegada " + horario.getDiaDeLaSemana();

    }

    public String getruta() {
        return rutaAsignada.getCiudadLlegada().getNombre() + " " + rutaAsignada.getCiudadSalida().getNombre();
    }

    public String getavion() {
        return "Modelo Avion: " + avionAsignado.getModelo() + " Espacios " + avionAsignado.getCantidadDePasajeros();
    }

}
