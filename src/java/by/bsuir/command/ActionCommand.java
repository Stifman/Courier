/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Степан
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
