/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.SenderDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoSenderInfoCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        SenderDAO ob = new SenderDAO();
        request.setAttribute("list", ob.getSenderName());
        return "jobParcel6.1.jsp";
    }
    
}
