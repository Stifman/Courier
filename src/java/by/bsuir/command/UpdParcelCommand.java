/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.command.ActionCommand;
import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.DAO.TipParcelDAO;
import by.bsuir.entity.Courier;
import by.bsuir.entity.Parcel;
import by.bsuir.entity.TipParcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class UpdParcelCommand implements ActionCommand { // возвращаю посылку с правильно выбранными значениями в выпадающем списке

    @Override
    public String execute(HttpServletRequest request) {
        Parcel parcel = new Parcel();
        String str, str2 = null;
        ParcelDAO ob = new ParcelDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        parcel = (Parcel) ob.read(id);
        request.setAttribute("parcel", parcel);
        request.setAttribute("nameCourierDel", parcel.getFkCourier().getName());

        CourierDAO courier = new CourierDAO();
        ArrayList<String> list = new ArrayList<String>();
        str = parcel.getFkCourier().getName();
        list.add(str);
        for (Iterator it = courier.getCourier().iterator(); it.hasNext();) {
            Courier c = (Courier) it.next();
            str2 = c.getName();
            if (!str.equals(str2)) {
                list.add(str2);
            }
        }
        request.setAttribute("listCourier", list);

        list = new ArrayList();
        TipParcelDAO object = new TipParcelDAO();
        str = parcel.getFkTipParcel().getName();
        list.add(str);
        for (Iterator it = object.getTipParcel().iterator(); it.hasNext();) {
            TipParcel pc = (TipParcel) it.next();
            str2 = pc.getName();
            if (!str.equals(str2)) {
                list.add(str2);
            }
        }
        request.setAttribute("listTypeParcel", list);
        try {
            if (!request.getParameter("nameSender").isEmpty()) {
                request.setAttribute("nameSender", request.getParameter("nameSender"));
            }
        } catch (NullPointerException ex) {
        }
        return "jobParcel2.1.jsp";
    }

}
