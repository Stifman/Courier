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
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Степан
 */
@Entity
@Table(name = "recipient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipient.findAll", query = "SELECT r FROM Recipient r"),
    @NamedQuery(name = "Recipient.findByIdrecipient", query = "SELECT r FROM Recipient r WHERE r.idrecipient = :idrecipient"),
    @NamedQuery(name = "Recipient.findByName", query = "SELECT r FROM Recipient r WHERE r.name = :name"),
    @NamedQuery(name = "Recipient.findByNumberTelephon", query = "SELECT r FROM Recipient r WHERE r.numberTelephon = :numberTelephon")})
public class Recipient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecipient")
    private Integer idrecipient;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
   // @NotNull
    @Column(name = "number_telephon")
    private long numberTelephon;
    @OneToMany(mappedBy = "fkRecipient")
    private Collection<Parcel> parcelCollection;

    public Recipient() {
    }

    public Recipient(Integer idrecipient) {
        this.idrecipient = idrecipient;
    }

    public Recipient(Integer idrecipient, String name, long numberTelephon) {
        this.idrecipient = idrecipient;
        this.name = name;
        this.numberTelephon = numberTelephon;
    }

    public Integer getIdrecipient() {
        return idrecipient;
    }

    public void setIdrecipient(Integer idrecipient) {
        this.idrecipient = idrecipient;
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
        hash += (idrecipient != null ? idrecipient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipient)) {
            return false;
        }
        Recipient other = (Recipient) object;
        if ((this.idrecipient == null && other.idrecipient != null) || (this.idrecipient != null && !this.idrecipient.equals(other.idrecipient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\u0441onverter.ejb.Recipient[ idrecipient=" + idrecipient + " ]";
    }
    
}
