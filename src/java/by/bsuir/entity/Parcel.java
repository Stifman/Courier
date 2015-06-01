/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.entity;

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
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Степан
 */
@Entity
@Table(name = "parcel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcel.findAll", query = "SELECT p FROM Parcel p"),
    @NamedQuery(name = "Parcel.findByIdparcel", query = "SELECT p FROM Parcel p WHERE p.idparcel = :idparcel"),
    @NamedQuery(name = "Parcel.findByName", query = "SELECT p FROM Parcel p WHERE p.name = :name"),
    @NamedQuery(name = "Parcel.findByDeliveryDate", query = "SELECT p FROM Parcel p WHERE p.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "Parcel.findByCost", query = "SELECT p FROM Parcel p WHERE p.cost = :cost")})
public class Parcel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparcel")
    private Integer idparcel;
    @Basic(optional = false)
   // @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
   //@NotNull
   // @Size(min = 1, max = 45)
    @Column(name = "delivery_date")
    private String deliveryDate;
    //@Size(max = 45)
    @Column(name = "cost")
    private String cost;
    @JoinColumn(name = "fk_courier", referencedColumnName = "idcourier")
    @ManyToOne
    private Courier fkCourier;
    @JoinColumn(name = "fk_place_delivery", referencedColumnName = "idplace_delivery")
    @ManyToOne
    private PlaceDelivery fkPlaceDelivery;
    @JoinColumn(name = "fk_place_dispatch", referencedColumnName = "idplace_dispatch")
    @ManyToOne
    private PlaceDispatch fkPlaceDispatch;
    @JoinColumn(name = "fk_recipient", referencedColumnName = "idrecipient")
    @ManyToOne
    private Recipient fkRecipient;
    @JoinColumn(name = "fk_sender", referencedColumnName = "idsender")
    @ManyToOne
    private Sender fkSender;
    @JoinColumn(name = "fk_status", referencedColumnName = "idstatus")
    @ManyToOne
    private Status fkStatus;
    @JoinColumn(name = "fk_tip_parcel", referencedColumnName = "idtip_parcel")
    @ManyToOne
    private TipParcel fkTipParcel;

    public Parcel() {
    }

    public Parcel(Integer idparcel) {
        this.idparcel = idparcel;
    }

    public Parcel(Integer idparcel, String name, String deliveryDate) {
        this.idparcel = idparcel;
        this.name = name;
        this.deliveryDate = deliveryDate;
    }

    public Integer getIdparcel() {
        return idparcel;
    }

    public void setIdparcel(Integer idparcel) {
        this.idparcel = idparcel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        if (cost == null) {
            Integer k = 0;
            if (this.getFkTipParcel().getName().equals("Документы")) {
                k = 1;
            } else if (this.getFkTipParcel().getName().equals("Небольшой груз")) {
                k = 2;
            } else if (this.getFkTipParcel().getName().equals("Большой груз")) {
                k = 3;
            } else if (this.getFkTipParcel().getName().equals("Опастный груз")) {
                k = 4;
            }
            Double d = Math.random();
            d = 100000 + k * 100000 * d;

            d = d - d % 10000;
            k = d.intValue();
            cost = k.toString();
        }
        this.cost = cost;
    }

    public Courier getFkCourier() {
        return fkCourier;
    }

    public void setFkCourier(Courier fkCourier) {
        this.fkCourier = fkCourier;
    }

    public PlaceDelivery getFkPlaceDelivery() {
        return fkPlaceDelivery;
    }

    public void setFkPlaceDelivery(PlaceDelivery fkPlaceDelivery) {
        this.fkPlaceDelivery = fkPlaceDelivery;
    }

    public PlaceDispatch getFkPlaceDispatch() {
        return fkPlaceDispatch;
    }

    public void setFkPlaceDispatch(PlaceDispatch fkPlaceDispatch) {
        this.fkPlaceDispatch = fkPlaceDispatch;
    }

    public Recipient getFkRecipient() {
        return fkRecipient;
    }

    public void setFkRecipient(Recipient fkRecipient) {
        this.fkRecipient = fkRecipient;
    }

    public Sender getFkSender() {
        return fkSender;
    }

    public void setFkSender(Sender fkSender) {
        this.fkSender = fkSender;
    }

    public Status getFkStatus() {
        return fkStatus;
    }

    public void setFkStatus(Status fkStatus) {
        this.fkStatus = fkStatus;
    }

    public TipParcel getFkTipParcel() {
        return fkTipParcel;
    }

    public void setFkTipParcel(TipParcel fkTipParcel) {
        this.fkTipParcel = fkTipParcel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparcel != null ? idparcel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcel)) {
            return false;
        }
        Parcel other = (Parcel) object;
        if ((this.idparcel == null && other.idparcel != null) || (this.idparcel != null && !this.idparcel.equals(other.idparcel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.bsuir.entity.Parcel[ idparcel=" + idparcel + " ]";
    }

}
