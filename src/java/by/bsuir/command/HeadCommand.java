/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.entity.Parcel;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class HeadCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        ArrayList<Parcel> list = (ArrayList<Parcel>) ob.getParcelNoMark();
        long summ = 0;
        for (Parcel elem : list) {
            summ += Double.parseDouble(elem.getCost());
        }
        request.setAttribute("summ", summ);
        int numberParcel = list.size();
        request.setAttribute("numberParcel", numberParcel);
        list = (ArrayList<Parcel>) ob.getParcel();
        long summAll = 0;
        for (Parcel elem : list) {
            summAll += Double.parseDouble(elem.getCost());
        }
        request.setAttribute("summAll", summAll - summ);
        int numberParcelAll = list.size();
        request.setAttribute("numberParcelAll", numberParcelAll - numberParcel);
        long midCost = 0;
        if (numberParcelAll != numberParcel) {
            midCost = (summAll - summ) / (numberParcelAll - numberParcel);
            request.setAttribute("midCost", midCost);
        } else {
            request.setAttribute("midCost", "0, так как ни одна посылка не доставлена");
        }

        CourierDAO object = new CourierDAO();
        request.setAttribute("numberOrdersMin", object.getNumberOrdersMin());
        request.setAttribute("numberOrdersMax", object.getNumberOrdersMax());

        return "index.jsp";
    }

}
