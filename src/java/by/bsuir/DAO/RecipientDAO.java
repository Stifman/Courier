/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Recipient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Степан
 */
public class RecipientDAO implements DAO{
    public RecipientDAO() {
    }

    public List getRecipient() {
        Recipient recipient = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM recipient");
            rs = ps.executeQuery();
            while (rs.next()) {
                recipient = new Recipient(rs.getInt("idrecipient"));
                recipient.setName(rs.getString("name"));
                recipient.setNumberTelephon(rs.getLong("number_telephon"));
                list.add(recipient);
            }
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return list;
    }

    @Override
    public int create(Object ob) {
        Recipient recipient = (Recipient) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idrecipient FROM recipient WHERE name = ? and number_telephon = ?");
            ps.setString(1, recipient.getName());
            ps.setLong(2, recipient.getNumberTelephon());
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                return rs.getInt("idrecipient");
            }
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
        }
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO recipient (name, number_telephon) VALUES (?,?)");
            ps.setString(1, recipient.getName());
            ps.setLong(2, recipient.getNumberTelephon());
            ps.execute();
            ps = connection.prepareStatement("SELECT MAX(idrecipient) AS maxid FROM recipient");
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
    public Recipient read(int id) {
        Recipient recipient = new Recipient(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name, number_telephon FROM recipient WHERE idrecipient= ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            recipient.setName(rs.getString("name"));
            recipient.setNumberTelephon(rs.getLong("number_telephon"));

        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return recipient;
    }

    @Override
    public void update(Object ob) {
        Recipient recipient = (Recipient) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE recipient SET name = ?, number_telephon = ?  WHERE idrecipient = ?");
            ps.setString(1, recipient.getName());
            ps.setLong(2, recipient.getNumberTelephon());
            ps.setInt(3, recipient.getIdrecipient());
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
            ps = connection.prepareStatement("DELETE FROM recipient WHERE idrecipient = ?");
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
