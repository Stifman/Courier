/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

/**
 *
 * @author Степан
 */

import java.io.Serializable;
import java.sql.SQLException;

public interface DAO extends Serializable{
    
    public int create(Object object);
    public Object read(int id);
    public void update(Object newObject);
    public void delete  (int id) throws SQLException;
}
