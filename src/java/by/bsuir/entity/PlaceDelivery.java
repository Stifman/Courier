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
@Table(name = "place_delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaceDelivery.findAll", query = "SELECT p FROM PlaceDelivery p"),
    @NamedQuery(name = "PlaceDelivery.findByIdplaceDelivery", query = "SELECT p FROM PlaceDelivery p WHERE p.idplaceDelivery = :idplaceDelivery"),
    @NamedQuery(name = "PlaceDelivery.findByName", query = "SELECT p FROM PlaceDelivery p WHERE p.name = :name")})
public class PlaceDelivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplace_delivery")
    private Integer idplaceDelivery;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkPlaceDelivery")
    private Collection<Parcel> parcelCollection;

    public PlaceDelivery() {
    }

    public PlaceDelivery(Integer idplaceDelivery) {
        this.idplaceDelivery = idplaceDelivery;
    }

    public PlaceDelivery(Integer idplaceDelivery, String name) {
        this.idplaceDelivery = idplaceDelivery;
        this.name = name;
    }

    public Integer getIdplaceDelivery() {
        return idplaceDelivery;
    }

    public void setIdplaceDelivery(Integer idplaceDelivery) {
        this.idplaceDelivery = idplaceDelivery;
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
        hash += (idplaceDelivery != null ? idplaceDelivery.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaceDelivery)) {
            return false;
        }
        PlaceDelivery other = (PlaceDelivery) object;
        if ((this.idplaceDelivery == null && other.idplaceDelivery != null) || (this.idplaceDelivery != null && !this.idplaceDelivery.equals(other.idplaceDelivery))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.bsuir.entity.PlaceDelivery[ idplaceDelivery=" + idplaceDelivery + " ]";
    }
    
}
