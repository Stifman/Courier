/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.DAO.SenderDAO;
import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Courier;
import by.bsuir.entity.Parcel;
import by.bsuir.entity.PlaceDelivery;
import by.bsuir.entity.PlaceDispatch;
import by.bsuir.entity.Recipient;
import by.bsuir.entity.Sender;
import by.bsuir.entity.Status;
import by.bsuir.entity.TipParcel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class AddParcelCommand implements ActionCommand {
    
    public String execute(HttpServletRequest request) {
        Parcel parcel = new Parcel();
        ParcelDAO pcDAO = new ParcelDAO();
        
        parcel.setName(request.getParameter("nameParcel"));
        parcel.setDeliveryDate(request.getParameter("deliveryDate"));
        
        System.out.println(request.getParameter("deliveryDate"));
        
        TipParcel tp = new TipParcel();
        tp.setName(request.getParameter("tipParcel"));
        parcel.setFkTipParcel(tp);
        
        Courier c = new Courier();
        c.setName(request.getParameter("nameCourier"));
        parcel.setFkCourier(c);
        
        CourierDAO object = new CourierDAO();
        object.addOrder(request.getParameter("nameCourier"));
        
        Recipient r = new Recipient();
        r.setName(request.getParameter("nameRecipient"));
        r.setNumberTelephon(Long.parseLong(request.getParameter("numberTelephonRecipient")));
        parcel.setFkRecipient(r);
        
        Sender s = new Sender();
        s.setName(request.getParameter("nameSender"));
        s.setNumberTelephon(Long.parseLong(request.getParameter("numberTelephonSender")));
        parcel.setFkSender(s);
        
        PlaceDelivery pd = new PlaceDelivery();
        pd.setName(request.getParameter("namePlaceDelivery"));
        parcel.setFkPlaceDelivery(pd);
        
        PlaceDispatch pdi = new PlaceDispatch();
        pdi.setName(request.getParameter("namePlaceDispatch"));
        parcel.setFkPlaceDispatch(pdi);
        
        Status st = new Status();
        st.setName("Недоставлено");
        parcel.setFkStatus(st);
        if (request.getParameter("cost").isEmpty()) {
            parcel.setCost(null);
        } else {
            parcel.setCost(request.getParameter("cost"));
        }
        pcDAO.create(parcel);
        
        return "jobParcel0.jsp";
    }
}
