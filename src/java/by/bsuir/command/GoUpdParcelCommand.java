/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.DAO.CourierDAO;
import by.bsuir.DAO.ParcelDAO;
import by.bsuir.DAO.TipParcelDAO;
import by.bsuir.entity.Courier;
import by.bsuir.entity.TipParcel;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public class GoUpdParcelCommand implements ActionCommand{
    
    @Override
    public String execute(HttpServletRequest request) {
        ParcelDAO ob = new ParcelDAO();
        request.setAttribute("listParcel", ob.getParcelNoMark());
        
        return "jobParcel2.0.jsp";
    }
    
}
