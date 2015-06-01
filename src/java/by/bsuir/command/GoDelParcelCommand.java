/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.ParcelDAO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoDelParcelCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
    ParcelDAO ob = new ParcelDAO();
        request.setAttribute("list", ob.getParcelForDelete());
        return "jobParcel3.jsp";
    }
    
}
