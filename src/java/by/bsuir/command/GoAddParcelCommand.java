/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.TipParcelDAO;
import by.bsuir.entity.Courier;
import by.bsuir.entity.TipParcel;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoAddParcelCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        ArrayList<String> list = new ArrayList<String>();
        for (Iterator it = ob.getCourier().iterator(); it.hasNext();) {
            Courier c = (Courier) it.next();
            list.add(c.getName());
        }
        request.setAttribute("list", list);
        list = new ArrayList();
        TipParcelDAO object = new TipParcelDAO();
        for (Iterator it = object.getTipParcel().iterator(); it.hasNext();) {
            TipParcel pc = (TipParcel) it.next();
            list.add(pc.getName());
        }
        request.setAttribute("listTypeParcel", list);
        return "jobParcel1.jsp";
    }

}
