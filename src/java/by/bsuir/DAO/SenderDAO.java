/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Sender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Степан
 */
public class SenderDAO implements DAO {

    public SenderDAO() {
    }
    
    public List getSenderName(){
        Sender sender = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT DISTINCT name FROM sender");
            rs = ps.executeQuery();
            while (rs.next()) {
                sender = new Sender(0);
                sender.setName(rs.getString("name"));
                list.add(sender);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return list;
    }
    
    public List getSender() {
        Sender sender = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM sender");
            rs = ps.executeQuery();
            while (rs.next()) {
                sender = new Sender(rs.getInt("idsender"));
                sender.setName(rs.getString("name"));
                sender.setNumberTelephon(rs.getLong("number_telephon"));
                list.add(sender);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return list;
    }

    @Override
    public int create(Object ob) {
        Sender sender = (Sender) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idsender FROM sender WHERE name = ? and number_telephon = ?");
            ps.setString(1, sender.getName());
            ps.setLong(2, sender.getNumberTelephon());
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                return rs.getInt("idsender");
            }
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
        }
        try {
            //finallyBlock(ps, null);
            ps = connection.prepareStatement("INSERT INTO sender (name, number_telephon) VALUES (?,?)");
            ps.setString(1, sender.getName());
            ps.setLong(2, sender.getNumberTelephon());
            ps.execute();
            ps = connection.prepareStatement("SELECT MAX(idsender) AS maxid FROM sender");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("maxid");
        } catch (SQLException ex) {
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return 0;

    }

    @Override
    public Sender read(int id) {
        Sender sender = new Sender(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name, number_telephon FROM sender WHERE idsender= ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            sender.setName(rs.getString("name"));
            sender.setNumberTelephon(rs.getLong("number_telephon"));

        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return sender;
    }

    @Override
    public void update(Object ob) {
        Sender sender = (Sender) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE sender SET name = ?, number_telephon = ?  WHERE idsender = ?");
            ps.setString(1, sender.getName());
            ps.setLong(2, sender.getNumberTelephon());
            ps.setInt(3, sender.getIdsender());
            ps.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("DELETE FROM sender WHERE idsender = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    private void finallyBlock(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {

        }
    }
}
