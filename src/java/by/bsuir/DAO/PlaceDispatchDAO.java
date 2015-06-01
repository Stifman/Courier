/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.PlaceDispatch;
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
public class PlaceDispatchDAO implements DAO {

    public PlaceDispatchDAO() {
    }

    public List getPlaceDispatch() {
        PlaceDispatch placeDis = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM place_dispatch");
            rs = ps.executeQuery();
            while (rs.next()) {
                placeDis = new PlaceDispatch(rs.getInt("idplace_dispatch"));
                placeDis.setName(rs.getString("name"));
                list.add(placeDis);
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
        PlaceDispatch placeDis = (PlaceDispatch) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idplace_dispatch FROM place_dispatch WHERE name = ?");
            ps.setString(1, placeDis.getName());
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                return rs.getInt("idplace_dispatch");
            }
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
        }
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO place_dispatch (name) VALUES (?)");
            ps.setString(1, placeDis.getName());
            ps.execute();
            ps = connection.prepareStatement("SELECT MAX(idplace_dispatch) AS maxid FROM place_dispatch");
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
    public PlaceDispatch read(int id) {
        PlaceDispatch placeDis = new PlaceDispatch(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name FROM place_dispatch WHERE idplace_dispatch = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            placeDis.setName(rs.getString("name"));

        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return placeDis;
    }

    @Override
    public void update(Object ob) {
        PlaceDispatch placeDis = (PlaceDispatch) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE place_dispatch SET name = ?  WHERE idplace_dispatch = ?");
            ps.setString(1, placeDis.getName());
            ps.setInt(2, placeDis.getIdplaceDispatch());
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
            ps = connection.prepareStatement("DELETE FROM place_dispatch WHERE idplace_dispatch = ?");
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
