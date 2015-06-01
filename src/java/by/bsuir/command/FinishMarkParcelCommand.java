/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.entity.Courier;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class FinishMarkParcelCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        ob.markParcel(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("listParcels", ob.getParcelNoMark());
        CourierDAO objectCourier = new CourierDAO();
        objectCourier.delOrder(request.getParameter("nameCourierOrder"));
        
        ArrayList<String> listCouriers = new ArrayList<String>();
        for (Iterator it = objectCourier.getCourier().iterator(); it.hasNext();) {
            Courier c = (Courier) it.next();
            listCouriers.add(c.getName());
        }
        request.setAttribute("listCouriers", listCouriers);
        return "jobParcel5.jsp";
    }
    
}
