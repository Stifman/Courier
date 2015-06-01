/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
///import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Степан
 */
@Entity
@Table(name = "courier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courier.findAll", query = "SELECT c FROM Courier c"),
    @NamedQuery(name = "Courier.findByIdcourier", query = "SELECT c FROM Courier c WHERE c.idcourier = :idcourier"),
    @NamedQuery(name = "Courier.findByName", query = "SELECT c FROM Courier c WHERE c.name = :name"),
    @NamedQuery(name = "Courier.findByNumberTelephon", query = "SELECT c FROM Courier c WHERE c.numberTelephon = :numberTelephon"),
    @NamedQuery(name = "Courier.findByNumberOrders", query = "SELECT c FROM Courier c WHERE c.numberOrders = :numberOrders")})
public class Courier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcourier")
    private Integer idcourier;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
   // @NotNull
    @Column(name = "number_telephon")
    private long numberTelephon;
    @Column(name = "number_orders")
    private Integer numberOrders;
    @OneToMany(mappedBy = "fkCourier")
    private Collection<Parcel> parcelCollection;

    public Courier() {
    }

    public Courier(Integer idcourier) {
        this.idcourier = idcourier;
    }

    public Courier(Integer idcourier, String name, long numberTelephon) {
        this.idcourier = idcourier;
        this.name = name;
        this.numberTelephon = numberTelephon;
    }

    public Integer getIdcourier() {
        return idcourier;
    }

    public void setIdcourier(Integer idcourier) {
        this.idcourier = idcourier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberTelephon() {
        return numberTelephon;
    }

    public void setNumberTelephon(long numberTelephon) {
        this.numberTelephon = numberTelephon;
    }

    public Integer getNumberOrders() {
        return numberOrders;
    }

    public void setNumberOrders(Integer numberOrders) {
        this.numberOrders = numberOrders;
    }

    @XmlTransient
    public Collection<Parcel> getParcelCollection() {
        return parcelCollection;
    }

    public void setParcelCollection(Collection<Parcel> parcelCollection) {
        this.parcelCollection = parcelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcourier != null ? idcourier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courier)) {
            return false;
        }
        Courier other = (Courier) object;
        if ((this.idcourier == null && other.idcourier != null) || (this.idcourier != null && !this.idcourier.equals(other.idcourier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.bsuir.entity.Courier[ idcourier=" + idcourier + " ]";
    }
    
}
