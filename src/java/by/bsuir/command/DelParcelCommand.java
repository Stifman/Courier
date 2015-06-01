/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.entity.Parcel;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class DelParcelCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        int id = Integer.parseInt(request.getParameter("id"));

        ob.delete(id);
        try {
            if (!request.getParameter("nameSender").isEmpty()) {
                request.setAttribute("nameSender", request.getParameter("nameSender"));
                return new SenderInfoCommand().execute(request);
            }
        } catch (NullPointerException ex) {
        }
        ParcelDAO ob2 = new ParcelDAO();
        request.setAttribute("list", ob2.getParcelForDelete());
        return "jobParcel3.jsp";
    }

}
