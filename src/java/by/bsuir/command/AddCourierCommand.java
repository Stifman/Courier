/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.SenderDAO;
import by.bsuir.entity.Courier;
import by.bsuir.entity.Sender;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class AddCourierCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        
        Courier courier = new Courier();
        courier.setName(request.getParameter("nameCourier"));
        courier.setNumberTelephon(Long.parseLong(request.getParameter("numberTelephonCourier")));
        CourierDAO ob = new CourierDAO();
        int i = 0;
        i = ob.create(courier);
        if(i!=0){
            request.setAttribute("massage", i);
        }
        return "jobCourier0.jsp";
    }
}
