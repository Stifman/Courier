/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.entity.Courier;
import by.bsuir.factory.ActionFactory;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class UpdCourierCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("courier",ob.read(id));
        return "jobCourier2.1.jsp";
    }
}
