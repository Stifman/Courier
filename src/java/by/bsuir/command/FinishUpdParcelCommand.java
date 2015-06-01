/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.entity.Courier;
import by.bsuir.entity.Parcel;
import by.bsuir.entity.PlaceDelivery;
import by.bsuir.entity.PlaceDispatch;
import by.bsuir.entity.Recipient;
import by.bsuir.entity.Sender;
import by.bsuir.entity.TipParcel;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class FinishUpdParcelCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        Parcel parcel = new Parcel(Integer.parseInt(request.getParameter("idParcel")));
        parcel.setName(request.getParameter("nameParcel"));
        parcel.setDeliveryDate(request.getParameter("deliveryDate"));
        parcel.setFkTipParcel(new TipParcel(null, request.getParameter("tipParcel")));
        parcel.setFkCourier(new Courier(null, request.getParameter("nameCourier"), 0));
        parcel.setFkRecipient(new Recipient(Integer.parseInt(request.getParameter("idRecipient")), request.getParameter("nameRecipient"), Long.parseLong(request.getParameter("numberTelephonRecipient"))));
        parcel.setFkSender(new Sender(Integer.parseInt(request.getParameter("idSender")), request.getParameter("nameSenderParcel"), Long.parseLong(request.getParameter("numberTelephonSender"))));
        parcel.setFkPlaceDelivery(new PlaceDelivery(Integer.parseInt(request.getParameter("idPlaceDelivery")), request.getParameter("namePlaceDelivery")));
        parcel.setFkPlaceDispatch(new PlaceDispatch(Integer.parseInt(request.getParameter("idPlaceDispatch")), request.getParameter("namePlaceDispatch")));
        ob.update(parcel);

        CourierDAO object = new CourierDAO();
        object.delOrder(request.getParameter("nameCourierDel"));
        object.addOrder(request.getParameter("nameCourier"));
        try {
            if (!request.getParameter("nameSender").isEmpty()) {
                request.setAttribute("nameSender", request.getParameter("nameSender"));
                return new SenderInfoCommand().execute(request);
            }
        } catch (NullPointerException ex) {
        }
        request.setAttribute("listParcel", ob.getParcelNoMark());

        return "jobParcel2.0.jsp";
    }
}
