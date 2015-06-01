/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Courier;
import by.bsuir.entity.PlaceDelivery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Степан
 */
public class PlaceDeliveryDAO implements DAO {

    public PlaceDeliveryDAO() {
    }

    public List getPlaceDelivery() {
        PlaceDelivery placeDel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM place_delivery");
            rs = ps.executeQuery();
            while (rs.next()) {
                placeDel = new PlaceDelivery(rs.getInt("idplace_delivery"));
                placeDel.setName(rs.getString("name"));
                list.add(placeDel);
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
        PlaceDelivery placeDel = (PlaceDelivery) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idplace_delivery FROM place_delivery WHERE name = ?");
            ps.setString(1, placeDel.getName());
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                return rs.getInt("idplace_delivery");
            }
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
        }
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO place_delivery (name) VALUES (?)");
            ps.setString(1, placeDel.getName());
            ps.execute();
            ps = connection.prepareStatement("SELECT MAX(idplace_delivery) AS maxid FROM place_delivery");
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
    public PlaceDelivery read(int id) {
        PlaceDelivery placeDel = new PlaceDelivery(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name FROM place_delivery WHERE idplace_delivery = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            placeDel.setName(rs.getString("name"));

        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return placeDel;
    }

    @Override
    public void update(Object ob) {
        PlaceDelivery placeDel = (PlaceDelivery) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE place_delivery SET name = ?  WHERE idplace_delivery = ?");
            ps.setString(1, placeDel.getName());
            ps.setInt(2, placeDel.getIdplaceDelivery());
            ps.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    @Override
    public void delete(int id){
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("DELETE FROM place_delivery WHERE idplace_delivery = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
