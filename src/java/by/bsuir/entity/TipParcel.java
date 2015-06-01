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
@Table(name = "tip_parcel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipParcel.findAll", query = "SELECT t FROM TipParcel t"),
    @NamedQuery(name = "TipParcel.findByIdtipParcel", query = "SELECT t FROM TipParcel t WHERE t.idtipParcel = :idtipParcel"),
    @NamedQuery(name = "TipParcel.findByName", query = "SELECT t FROM TipParcel t WHERE t.name = :name")})
public class TipParcel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtip_parcel")
    private Integer idtipParcel;
    @Basic(optional = false)
   // @NotNull
    //@Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkTipParcel")
    private Collection<Parcel> parcelCollection;

    public TipParcel() {
    }

    public TipParcel(Integer idtipParcel) {
        this.idtipParcel = idtipParcel;
    }

    public TipParcel(Integer idtipParcel, String name) {
        this.idtipParcel = idtipParcel;
        this.name = name;
    }

    public Integer getIdtipParcel() {
        return idtipParcel;
    }

    public void setIdtipParcel(Integer idtipParcel) {
        this.idtipParcel = idtipParcel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idtipParcel != null ? idtipParcel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipParcel)) {
            return false;
        }
        TipParcel other = (TipParcel) object;
        if ((this.idtipParcel == null && other.idtipParcel != null) || (this.idtipParcel != null && !this.idtipParcel.equals(other.idtipParcel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.bsuir.entity.TipParcel[ idtipParcel=" + idtipParcel + " ]";
    }
    
}
