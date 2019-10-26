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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "Horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
    , @NamedQuery(name = "Horario.findByHorario", query = "SELECT h FROM Horario h WHERE h.horario = :horario")
    , @NamedQuery(name = "Horario.findBySalida", query = "SELECT h FROM Horario h WHERE h.salida = :salida")
    , @NamedQuery(name = "Horario.findByLlegada", query = "SELECT h FROM Horario h WHERE h.llegada = :llegada")
    , @NamedQuery(name = "Horario.findByDiaDeLaSemana", query = "SELECT h FROM Horario h WHERE h.diaDeLaSemana = :diaDeLaSemana")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "horario")
    private String horario;
    @Column(name = "salida")
    @Temporal(TemporalType.TIME)
    private Date salida;
    @Column(name = "llegada")
    @Temporal(TemporalType.TIME)
    private Date llegada;
    @Column(name = "dia_de_la_semana")
    private String diaDeLaSemana;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horario")
    private List<Vuelo> vueloList;

    public Horario() {
    }

    public Horario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Date getLlegada() {
        return llegada;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    public String getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setDiaDeLaSemana(String diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
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
        hash += (horario != null ? horario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horario == null && other.horario != null) || (this.horario != null && !this.horario.equals(other.horario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String salida= this.horario+" - "+ this.getDiaDeLaSemana().charAt(0)+this.getDiaDeLaSemana().charAt(1);
        Integer hora= this.salida.getHours();
        System.out.println(hora);
        Integer hora2=this.llegada.getHours();
        System.out.println(hora2);
        hora2-=18;
        hora-=18;
        String salida2= " S:"+ hora.toString()+"-L:"+ hora2.toString();
        return salida+salida2;
    }
    
}
