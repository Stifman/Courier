/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoDelCourierCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        
            request.setAttribute("list", ob.getCourier());
        
        return "jobCourier3.jsp";
    }
}
