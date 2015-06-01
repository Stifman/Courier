/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.entity.Courier;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class FinishUpdCourierCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        Courier courier = new Courier(Integer.parseInt(request.getParameter("idCourier")),request.getParameter("nameCourier"),Long.parseLong(request.getParameter("numberTelephonCourier")));
        ob.update(courier);
        request.setAttribute("list", ob.getCourier());
        return "jobCourier2.0.jsp";
    }
    
}
