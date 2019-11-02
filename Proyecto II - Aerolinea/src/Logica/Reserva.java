/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Monica
 */
@Entity
@Table(name = "Reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByReserva", query = "SELECT r FROM Reserva r WHERE r.reserva = :reserva")
    , @NamedQuery(name = "Reserva.findByNumeroAsiento", query = "SELECT r FROM Reserva r WHERE r.numeroAsiento = :numeroAsiento")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reserva")
    private Integer reserva;
    @Column(name = "numero_asiento")
    private String numeroAsiento;
    @JoinColumn(name = "pago", referencedColumnName = "pago")
    @ManyToOne(optional = false)
    private Pago pago;
    @JoinColumn(name = "ticket", referencedColumnName = "ticket")
    @ManyToOne(optional = false)
    private Ticket ticket;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "viaje", referencedColumnName = "viaje")
    @ManyToOne(optional = false)
    private Viaje viaje;

    public Reserva() {
    }

    public Reserva(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }
    public String imprimeTiquete(){
        String tiquete;
        tiquete="Reserva # "+ this.reserva+ "\n"+ "Viaje: "+ this.viaje.getViaje() + "\n"+ "Numero de asiento: "+ this.numeroAsiento+ "\n"+ "Numero de pago: "+ this.pago.getPago()+"\n"+"Usuario: "+ this.usuario.getNombre()+ " "+ this.usuario.getApellidos();
        return tiquete;
    }
    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reserva != null ? reserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reserva == null && other.reserva != null) || (this.reserva != null && !this.reserva.equals(other.reserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica.Reserva[ reserva=" + reserva + " ]";
    }
    
}
