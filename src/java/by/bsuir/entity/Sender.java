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
@Table(name = "sender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sender.findAll", query = "SELECT s FROM Sender s"),
    @NamedQuery(name = "Sender.findByIdsender", query = "SELECT s FROM Sender s WHERE s.idsender = :idsender"),
    @NamedQuery(name = "Sender.findByName", query = "SELECT s FROM Sender s WHERE s.name = :name"),
    @NamedQuery(name = "Sender.findByNumberTelephon", query = "SELECT s FROM Sender s WHERE s.numberTelephon = :numberTelephon")})
public class Sender implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsender")
    private Integer idsender;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "number_telephon")
    private long numberTelephon;
    @OneToMany(mappedBy = "fkSender")
    private Collection<Parcel> parcelCollection;

    public Sender() {
    }

    public Sender(Integer idsender) {
        this.idsender = idsender;
    }

    public Sender(Integer idsender, String name, long numberTelephon) {
        this.idsender = idsender;
        this.name = name;
        this.numberTelephon = numberTelephon;
    }

    public Integer getIdsender() {
        return idsender;
    }

    public void setIdsender(Integer idsender) {
        this.idsender = idsender;
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
        hash += (idsender != null ? idsender.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sender)) {
            return false;
        }
        Sender other = (Sender) object;
        if ((this.idsender == null && other.idsender != null) || (this.idsender != null && !this.idsender.equals(other.idsender))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\u0441onverter.ejb.Sender[ idsender=" + idsender + " ]";
    }
    
}
