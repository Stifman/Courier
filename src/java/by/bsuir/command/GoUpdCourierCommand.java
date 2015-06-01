/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoUpdCourierCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        request.setAttribute("list", ob.getCourier());
        return "jobCourier2.0.jsp";
    }
    
}
