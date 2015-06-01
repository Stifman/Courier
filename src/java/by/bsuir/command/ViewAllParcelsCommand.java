/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.ParcelDAO;
import by.bsuir.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class ViewAllParcelsCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        request.setAttribute("list", ob.getParcel());
        return "jobParcel4.jsp";
    }

}
