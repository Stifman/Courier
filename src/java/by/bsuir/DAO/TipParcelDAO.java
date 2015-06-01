/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.TipParcel;
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
public class TipParcelDAO implements DAO{
    public TipParcelDAO(){}
    public List getTipParcel() {
        TipParcel tip_parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM tip_parcel");
            rs = ps.executeQuery();
            while (rs.next()) {
                tip_parcel = new TipParcel(rs.getInt("idtip_parcel"));
                tip_parcel.setName(rs.getString("name"));
                list.add(tip_parcel);
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
        TipParcel tip_parcel = (TipParcel) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO tip_parcel (name) VALUES (?)");
            ps.setString(1, tip_parcel.getName());
            ps.execute();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return 0;

    }

    @Override
    public TipParcel read(int id) {
        TipParcel tip_parcel = new TipParcel(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name FROM tip_parcel WHERE idtip_parcel= ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            tip_parcel.setName(rs.getString("name"));

        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return tip_parcel;
    }

    @Override
    public void update(Object ob) {
       TipParcel tip_parcel = (TipParcel) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE tip_parcel SET name = ? WHERE idtip_parcel = ?");
            ps.setString(1, tip_parcel.getName());
            ps.setInt(2, tip_parcel.getIdtipParcel());
            ps.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }
    
    public int searchTipParcel(String name) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idtip_parcel FROM tip_parcel WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("idtip_parcel");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public void delete(int id) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("DELETE FROM tip_parcel WHERE idtip_parcel = ?");
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
