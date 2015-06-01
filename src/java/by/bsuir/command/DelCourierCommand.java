/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.entity.Courier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class DelCourierCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CourierDAO ob = new CourierDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ob.delete(id);
        } catch (SQLException ex) {
            request.setAttribute("massage", "Невозможно удалить курьера пока к нему привязана посылка");
            request.setAttribute("list", ob.getCourier());
            return "jobCourier3.jsp";
        }
        request.setAttribute("list", ob.getCourier());
        return "jobCourier3.jsp";
    }
}
