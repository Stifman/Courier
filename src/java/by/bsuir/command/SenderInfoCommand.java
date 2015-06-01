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
public class SenderInfoCommand implements ActionCommand {
    public void SenderInfoCommand(){
    }
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("nameSender");
        ParcelDAO ob = new ParcelDAO();
        request.setAttribute("nameSender", name);
        request.setAttribute("list", ob.getParcelForRecipient(name));
        return "jobParcel6.2.jsp";
    }
}
