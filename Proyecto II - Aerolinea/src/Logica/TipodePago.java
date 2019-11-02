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
@Table(name = "Tipo_de_Pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipodePago.findAll", query = "SELECT t FROM TipodePago t")
    , @NamedQuery(name = "TipodePago.findByTipoDePago", query = "SELECT t FROM TipodePago t WHERE t.tipoDePago = :tipoDePago")
    , @NamedQuery(name = "TipodePago.findByDescripcion", query = "SELECT t FROM TipodePago t WHERE t.descripcion = :descripcion")})
public class TipodePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_de_pago")
    private String tipoDePago;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPagocodigopago")
    private List<Pago> pagoList;

    public TipodePago() {
    }

    public TipodePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDePago != null ? tipoDePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipodePago)) {
            return false;
        }
        TipodePago other = (TipodePago) object;
        if ((this.tipoDePago == null && other.tipoDePago != null) || (this.tipoDePago != null && !this.tipoDePago.equals(other.tipoDePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoDePago;
    }
    
}
