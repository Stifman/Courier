/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Courier;
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
public class CourierDAO implements DAO {

    public CourierDAO() {
    }

    public List getCourier() {
        Courier courier = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List couriers = new ArrayList();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM courier");
            rs = ps.executeQuery();
            while (rs.next()) {
                courier = new Courier(rs.getInt("idcourier"));
                courier.setName(rs.getString("name"));
                courier.setNumberTelephon(rs.getLong("number_telephon"));
                courier.setNumberOrders(rs.getInt("number_orders"));
                couriers.add(courier);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return couriers;
    }

    @Override
    public int create(Object ob) {   //возвращает 0 если запись добавлена иначе номер существовавшей
        Courier courier = (Courier) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idcourier FROM courier WHERE name = ? and number_telephon = ?");
            ps.setString(1, courier.getName());
            ps.setLong(2, courier.getNumberTelephon());
            if ((rs = ps.executeQuery()) != null) {
                rs.next();
                return rs.getInt("idcourier");
            }
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
            ex.printStackTrace();
            System.out.println("Courier add true");
        }
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO courier (name, number_telephon) VALUES (?,?)");
            ps.setString(1, courier.getName());
            ps.setLong(2, courier.getNumberTelephon());
            ps.execute();
        } catch (SQLException ex) {

        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return 0;

    }

    public int searchCourier(String name) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idcourier FROM courier WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("idcourier");
        } catch (SQLException ex) {
            //ps.executeQuery()) = null !!!
        }
        return 0;
    }

    public void addOrder(String name) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idcourier, number_orders FROM courier WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            Courier courier = new Courier(rs.getInt(1));
            courier.setNumberOrders(rs.getInt(2));
            finallyBlock(ps, rs);
            ps = connection.prepareStatement("UPDATE courier SET number_orders = ? WHERE idcourier = ?");
            ps.setInt(1, courier.getNumberOrders() + 1);
            ps.setInt(2, courier.getIdcourier());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delOrder(String name) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idcourier, number_orders FROM courier WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            Courier courier = new Courier(rs.getInt(1));
            courier.setNumberOrders(rs.getInt(2));
            finallyBlock(ps, rs);
            ps = connection.prepareStatement("UPDATE courier SET number_orders = ? WHERE idcourier = ?");
            ps.setInt(1, courier.getNumberOrders() - 1);
            ps.setInt(2, courier.getIdcourier());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updOrder(String name) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idcourier, number_orders FROM courier WHERE name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            Courier courier = new Courier(rs.getInt(1));
            courier.setNumberOrders(rs.getInt(2));
            finallyBlock(ps, rs);
            ps = connection.prepareStatement("UPDATE courier SET number_orders = ? WHERE idcourier = ?");
            ps.setInt(1, courier.getNumberOrders() - 1);
            ps.setInt(2, courier.getIdcourier());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Courier read(int id) {
        Courier courier = new Courier(id);
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name, number_telephon, number_orders FROM courier WHERE idcourier = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            courier.setName(rs.getString("name"));
            courier.setNumberTelephon(rs.getLong("number_telephon"));
            courier.setNumberOrders(rs.getInt("number_orders"));

        } catch (SQLException ex) {
ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, rs);
        }
        return courier;
    }

    @Override
    public void update(Object ob) {
        Courier courier = (Courier) ob;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE courier SET name = ?, number_telephon = ? WHERE idcourier = ?");
            ps.setString(1, courier.getName());
            ps.setLong(2, courier.getNumberTelephon());
            //ps.setInt(3, courier.getNumberOrders());
            ps.setInt(3, courier.getIdcourier());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    @Override
    public void delete(int id)  throws SQLException{
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("DELETE FROM courier WHERE idcourier = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex){
            throw ex;
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    public String getNumberOrdersMax() {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String str = null;
        int n = -1;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name, number_orders FROM courier");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (n < rs.getInt("number_orders")) {
                    n = rs.getInt("number_orders");
                    str = rs.getString("name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return str + '(' + n + ')';
    }

    public String getNumberOrdersMin() {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String str = null;
        int n = 9999;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT name, number_orders FROM courier");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (n > rs.getInt("number_orders")) {
                    n = rs.getInt("number_orders");
                    str = rs.getString("name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return str + '(' + n + ')';
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
