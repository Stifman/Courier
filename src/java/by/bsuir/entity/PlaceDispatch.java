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
@Table(name = "place_dispatch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaceDispatch.findAll", query = "SELECT p FROM PlaceDispatch p"),
    @NamedQuery(name = "PlaceDispatch.findByIdplaceDispatch", query = "SELECT p FROM PlaceDispatch p WHERE p.idplaceDispatch = :idplaceDispatch"),
    @NamedQuery(name = "PlaceDispatch.findByName", query = "SELECT p FROM PlaceDispatch p WHERE p.name = :name")})
public class PlaceDispatch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplace_dispatch")
    private Integer idplaceDispatch;
    @Basic(optional = false)
  //  @NotNull
   // @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkPlaceDispatch")
    private Collection<Parcel> parcelCollection;

    public PlaceDispatch() {
    }

    public PlaceDispatch(Integer idplaceDispatch) {
        this.idplaceDispatch = idplaceDispatch;
    }

    public PlaceDispatch(Integer idplaceDispatch, String name) {
        this.idplaceDispatch = idplaceDispatch;
        this.name = name;
    }

    public Integer getIdplaceDispatch() {
        return idplaceDispatch;
    }

    public void setIdplaceDispatch(Integer idplaceDispatch) {
        this.idplaceDispatch = idplaceDispatch;
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
        hash += (idplaceDispatch != null ? idplaceDispatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaceDispatch)) {
            return false;
        }
        PlaceDispatch other = (PlaceDispatch) object;
        if ((this.idplaceDispatch == null && other.idplaceDispatch != null) || (this.idplaceDispatch != null && !this.idplaceDispatch.equals(other.idplaceDispatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.bsuir.entity.PlaceDispatch[ idplaceDispatch=" + idplaceDispatch + " ]";
    }
    
}
