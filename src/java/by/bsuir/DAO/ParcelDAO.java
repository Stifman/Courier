/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.DAO;

import by.bsuir.connection.ConnectionPool;
import by.bsuir.entity.Courier;
import by.bsuir.entity.Parcel;
import by.bsuir.entity.PlaceDelivery;
import by.bsuir.entity.PlaceDispatch;
import by.bsuir.entity.Recipient;
import by.bsuir.entity.Sender;
import by.bsuir.entity.Status;
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
public class ParcelDAO implements DAO {

    final public static String MARK_PARCEL = "Доставлено";

    public ParcelDAO() {
    }

    public List getParcel() {
        Parcel parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        ResultSet rs = null;
        ResultSet rsNew = null;
        PreparedStatement ps = null;
        PreparedStatement psNew = null;
        List parcels = new ArrayList<Parcel>();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM parcel");
            rs = ps.executeQuery();
            while (rs.next()) {
                parcel = new Parcel(rs.getInt("idparcel"));
                parcel.setName(rs.getString("name"));
                parcel.setDeliveryDate(rs.getString("delivery_date"));

                Connection cn = ConnectionPool.initConnection().getConnection();

                psNew = connection.prepareStatement("SELECT * FROM courier WHERE idcourier = ?");
                psNew.setInt(1, rs.getInt("fk_courier"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkCourier(new Courier(rsNew.getInt("idcourier"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM place_delivery WHERE idplace_delivery = ?");
                psNew.setInt(1, rs.getInt("fk_place_delivery"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkPlaceDelivery(new PlaceDelivery(rsNew.getInt("idplace_delivery"), rsNew.getString("name")));

                psNew = connection.prepareStatement("SELECT * FROM place_dispatch WHERE idplace_dispatch = ?");
                psNew.setInt(1, rs.getInt("fk_place_dispatch"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkPlaceDispatch(new PlaceDispatch(rsNew.getInt("idplace_dispatch"), rsNew.getString("name")));

                psNew = connection.prepareStatement("SELECT * FROM recipient WHERE idrecipient = ?");
                psNew.setInt(1, rs.getInt("fk_recipient"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkRecipient(new Recipient(rsNew.getInt("idrecipient"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM sender WHERE idsender = ?");
                psNew.setInt(1, rs.getInt("fk_sender"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkSender(new Sender(rsNew.getInt("idsender"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM status WHERE idstatus = ?");
                psNew.setInt(1, rs.getInt("fk_status"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkStatus(new Status(rsNew.getInt("idstatus"), rsNew.getString("name")));

                psNew = connection.prepareStatement("SELECT * FROM tip_parcel WHERE idtip_parcel = ?");
                psNew.setInt(1, rs.getInt("fk_tip_parcel"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkTipParcel(new TipParcel(rsNew.getInt("idtip_parcel"), rsNew.getString("name")));

                parcel.setCost(rs.getString("cost"));

                parcels.add(parcel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.closeConnection();
            finallyBlock(ps, rs);
            finallyBlock(psNew, rsNew);
            finallyBlock(ps, rsNew);
        }
        return parcels;
    }

    @Override
    public int create(Object object) {
        Parcel parcel = (Parcel) object;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("INSERT INTO parcel (name, delivery_date, cost, fk_courier, fk_place_delivery, fk_place_dispatch, fk_tip_parcel, fk_recipient, fk_status, fk_sender) VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, parcel.getName());
            ps.setString(2, parcel.getDeliveryDate());
            ps.setString(3, parcel.getCost());
            int i;
            i = new CourierDAO().searchCourier(parcel.getFkCourier().getName());
            ps.setInt(4, i);
            i = new PlaceDeliveryDAO().create(parcel.getFkPlaceDelivery());
            ps.setInt(5, i);
            i = new PlaceDispatchDAO().create(parcel.getFkPlaceDispatch());
            ps.setInt(6, i);
            i = new TipParcelDAO().searchTipParcel(parcel.getFkTipParcel().getName());
            ps.setInt(7, i);
            i = new RecipientDAO().create(parcel.getFkRecipient());
            ps.setInt(8, i);
            i = new StatusDAO().searchStatus(parcel.getFkStatus().getName());
            ps.setInt(9, i);
            i = new SenderDAO().create(parcel.getFkSender());
            ps.setInt(10, i);
//            ps.setInt(4, new CourierDAO().searchCourier(parcel.getFkCourier().getName()));
//            ps.setInt(5, new PlaceDeliveryDAO().create(parcel.getFkPlaceDelivery()));
//            ps.setInt(6, new PlaceDispatchDAO().create(parcel.getFkPlaceDispatch()));
//            ps.setInt(7, new TipParcelDAO().searchTipParcel(parcel.getFkTipParcel().getName()));
//            ps.setInt(8, new RecipientDAO().create(parcel.getFkRecipient()));
//            ps.setInt(9, new StatusDAO().searchStatus(parcel.getFkStatus().getName()));
//            ps.setInt(10, new SenderDAO().create(parcel.getFkSender()));

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
        return 1;

    }

    @Override
    public Object read(int id) {
        Parcel parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        ResultSet rs = null;
        ResultSet rsNew = null;
        PreparedStatement psNew = null;
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM parcel WHERE idparcel = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            parcel = new Parcel(rs.getInt("idparcel"));
            parcel.setName(rs.getString("name"));
            parcel.setDeliveryDate(rs.getString("delivery_date"));

            Connection cn = ConnectionPool.initConnection().getConnection();

            psNew = connection.prepareStatement("SELECT * FROM courier WHERE idcourier = ?");
            psNew.setInt(1, rs.getInt("fk_courier"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkCourier(new Courier(rsNew.getInt("idcourier"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

            psNew = connection.prepareStatement("SELECT * FROM place_delivery WHERE idplace_delivery = ?");
            psNew.setInt(1, rs.getInt("fk_place_delivery"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkPlaceDelivery(new PlaceDelivery(rsNew.getInt("idplace_delivery"), rsNew.getString("name")));

            psNew = connection.prepareStatement("SELECT * FROM place_dispatch WHERE idplace_dispatch = ?");
            psNew.setInt(1, rs.getInt("fk_place_dispatch"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkPlaceDispatch(new PlaceDispatch(rsNew.getInt("idplace_dispatch"), rsNew.getString("name")));

            psNew = connection.prepareStatement("SELECT * FROM recipient WHERE idrecipient = ?");
            psNew.setInt(1, rs.getInt("fk_recipient"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkRecipient(new Recipient(rsNew.getInt("idrecipient"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

            psNew = connection.prepareStatement("SELECT * FROM sender WHERE idsender = ?");
            psNew.setInt(1, rs.getInt("fk_sender"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkSender(new Sender(rsNew.getInt("idsender"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

            psNew = connection.prepareStatement("SELECT * FROM status WHERE idstatus = ?");
            psNew.setInt(1, rs.getInt("fk_status"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkStatus(new Status(rsNew.getInt("idstatus"), rsNew.getString("name")));

            psNew = connection.prepareStatement("SELECT * FROM tip_parcel WHERE idtip_parcel = ?");
            psNew.setInt(1, rs.getInt("fk_tip_parcel"));
            rsNew = psNew.executeQuery();
            rsNew.next();
            parcel.setFkTipParcel(new TipParcel(rsNew.getInt("idtip_parcel"), rsNew.getString("name")));

            parcel.setCost(rs.getString("cost"));

        } catch (SQLException ex) {
        } finally {
            cp.closeConnection();
            finallyBlock(ps, rs);
            finallyBlock(ps, rsNew);
        }
        return parcel;
    }

    @Override
    public void update(Object newObject) { // courier id update
        Parcel parcel = (Parcel) newObject;
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE parcel SET name = ?, delivery_date = ?, cost = ?  WHERE idparcel = ?");
            ps.setString(1, parcel.getName());
            ps.setString(2, parcel.getDeliveryDate());
            parcel.setCost(null);
            ps.setString(3, parcel.getCost());
            ps.setString(4, parcel.getIdparcel().toString());

            ps.executeUpdate();

            new PlaceDeliveryDAO().update(parcel.getFkPlaceDelivery());
            new PlaceDispatchDAO().update(parcel.getFkPlaceDispatch());
            new RecipientDAO().update(parcel.getFkRecipient());
            new SenderDAO().update(parcel.getFkSender());

            ps = connection.prepareStatement("UPDATE parcel SET fk_courier = ?, fk_tip_parcel = ? WHERE idparcel = ?");
            ps.setInt(1, new CourierDAO().searchCourier(parcel.getFkCourier().getName()));
            ps.setInt(2, new TipParcelDAO().searchTipParcel(parcel.getFkTipParcel().getName()));
            //ps.setInt(3, new StatusDAO().searchStatus(parcel.getFkStatus().getName()));
            ps.setInt(3, parcel.getIdparcel());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
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
            Parcel parcel = new Parcel();
            parcel = (Parcel) this.read(id);
            CourierDAO object = new CourierDAO();
            object.delOrder(parcel.getFkCourier().getName());

            connection = cp.getConnection();
            ps = connection.prepareStatement("DELETE FROM parcel WHERE idparcel = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace(); // повторное удаление(Назад)
        }finally {
            cp.freeConnection(connection);
            finallyBlock(ps, null);
        }
    }

    public List getParcelForDelete() {
        Parcel parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List parcels = new ArrayList<Parcel>();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT idparcel,parcel.`name`,delivery_date, fk_courier, fk_tip_parcel, fk_recipient, fk_sender FROM parcel INNER JOIN status ON parcel.fk_status=status.idstatus WHERE status.name = 'Недоставлено'");
            rs = ps.executeQuery();
            while (rs.next()) {
                parcel = new Parcel(rs.getInt("idparcel"));
                parcel.setName(rs.getString("name"));
                parcel.setDeliveryDate(rs.getString("delivery_date"));

                CourierDAO cour = new CourierDAO();
                parcel.setFkCourier(cour.read(rs.getInt("fk_courier")));

                TipParcelDAO tip = new TipParcelDAO();
                parcel.setFkTipParcel(tip.read(rs.getInt("fk_tip_parcel")));

                SenderDAO send = new SenderDAO();
                parcel.setFkSender(send.read(rs.getInt("fk_sender")));

                RecipientDAO rec = new RecipientDAO();
                parcel.setFkRecipient(rec.read(rs.getInt("fk_recipient")));

                parcels.add(parcel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.closeConnection();
            finallyBlock(ps, rs);
        }
        return parcels;
    }

    public List getParcelNoMark() {
        Parcel parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        ResultSet rs = null;
        ResultSet rsNew = null;
        PreparedStatement ps = null;
        PreparedStatement psNew = null;
        List parcels = new ArrayList<Parcel>();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT * FROM parcel INNER JOIN status ON parcel.fk_status=status.idstatus WHERE status.name = 'Недоставлено'");
            rs = ps.executeQuery();
            while (rs.next()) {
                parcel = new Parcel(rs.getInt("idparcel"));
                parcel.setName(rs.getString("parcel.name"));
                parcel.setDeliveryDate(rs.getString("delivery_date"));

                Connection cn = ConnectionPool.initConnection().getConnection();

                psNew = connection.prepareStatement("SELECT * FROM courier WHERE idcourier = ?");
                psNew.setInt(1, rs.getInt("fk_courier"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkCourier(new Courier(rsNew.getInt("idcourier"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM place_delivery WHERE idplace_delivery = ?");
                psNew.setInt(1, rs.getInt("fk_place_delivery"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkPlaceDelivery(new PlaceDelivery(rsNew.getInt("idplace_delivery"), rsNew.getString("name")));

                psNew = connection.prepareStatement("SELECT * FROM place_dispatch WHERE idplace_dispatch = ?");
                psNew.setInt(1, rs.getInt("fk_place_dispatch"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkPlaceDispatch(new PlaceDispatch(rsNew.getInt("idplace_dispatch"), rsNew.getString("name")));

                psNew = connection.prepareStatement("SELECT * FROM recipient WHERE idrecipient = ?");
                psNew.setInt(1, rs.getInt("fk_recipient"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkRecipient(new Recipient(rsNew.getInt("idrecipient"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM sender WHERE idsender = ?");
                psNew.setInt(1, rs.getInt("fk_sender"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkSender(new Sender(rsNew.getInt("idsender"), rsNew.getString("name"), rsNew.getLong("number_telephon")));

                psNew = connection.prepareStatement("SELECT * FROM status WHERE idstatus = ?");
                psNew.setInt(1, rs.getInt("fk_status"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkStatus(new Status(rsNew.getInt("idstatus"), rsNew.getString("status.name")));
                //parcel.setFkStatus(new Status(rs.getInt("idstatus"), rs.getString("status.name")));

                psNew = connection.prepareStatement("SELECT * FROM tip_parcel WHERE idtip_parcel = ?");
                psNew.setInt(1, rs.getInt("fk_tip_parcel"));
                rsNew = psNew.executeQuery();
                rsNew.next();
                parcel.setFkTipParcel(new TipParcel(rsNew.getInt("idtip_parcel"), rsNew.getString("name")));

                parcel.setCost(rs.getString("cost"));

                parcels.add(parcel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.closeConnection();
            finallyBlock(ps, rs);
            finallyBlock(psNew, rsNew);
            finallyBlock(ps, rsNew);
        }
        return parcels;
    }

//    public List getParcelNoMark() {
//        Parcel parcel = null;
//        ConnectionPool cp = ConnectionPool.initConnection();
//        Connection connection = null;
//        ResultSet rs = null;
//        ResultSet rsNew = null;
//        PreparedStatement ps = null;
//        PreparedStatement psNew = null;
//        List parcels = new ArrayList<Parcel>();
//        try {
//            connection = cp.getConnection();
//            ps = connection.prepareStatement("SELECT idparcel,parcel.`name`,delivery_date, fk_courier, fk_recipient FROM parcel INNER JOIN status ON parcel.fk_status=status.idstatus WHERE status.name = 'Недоставлено'");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                parcel = new Parcel(rs.getInt("idparcel"));
//                parcel.setName(rs.getString("name"));
//                parcel.setDeliveryDate(rs.getString("delivery_date"));
//
//                psNew = connection.prepareStatement("SELECT * FROM courier WHERE idcourier = ?");
//                psNew.setInt(1, rs.getInt("fk_courier"));
//                rsNew = psNew.executeQuery();
//                rsNew.next();
//                parcel.setFkCourier(new Courier(rsNew.getInt("idcourier"), rsNew.getString("name"), rsNew.getLong("number_telephon")));
//
//                psNew = connection.prepareStatement("SELECT * FROM recipient WHERE idrecipient = ?");
//                psNew.setInt(1, rs.getInt("fk_recipient"));
//                rsNew = psNew.executeQuery();
//                rsNew.next();
//                parcel.setFkRecipient(new Recipient(rsNew.getInt("idrecipient"), rsNew.getString("name"), rsNew.getLong("number_telephon")));
//
//                parcels.add(parcel);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            cp.closeConnection();
//            finallyBlock(ps, rs);
//            finallyBlock(psNew, rsNew);
//            finallyBlock(ps, rsNew);
//        }
//        return parcels;
//    }
    public void markParcel(int id) {
        Connection connection = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        PreparedStatement ps = null;
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("UPDATE parcel SET fk_status = ? WHERE idparcel = ?");
            StatusDAO object = new StatusDAO();
            ps.setInt(1, object.searchStatus(MARK_PARCEL));
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public List getParcelForRecipient(String name) {
        Parcel parcel = null;
        ConnectionPool cp = ConnectionPool.initConnection();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List parcels = new ArrayList<Parcel>();
        try {
            connection = cp.getConnection();
            ps = connection.prepareStatement("SELECT DISTINCT idparcel,parcel.`name`,delivery_date,fk_sender,fk_status FROM parcel INNER JOIN status ON parcel.fk_status=status.idstatus INNER JOIN sender ON parcel.fk_sender = sender.idsender WHERE sender.name = ? AND status.name = 'Недоставлено'");
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                parcel = new Parcel(rs.getInt("idparcel"));
                parcel.setName(rs.getString("name"));
                parcel.setDeliveryDate(rs.getString("delivery_date"));
                parcels.add(parcel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cp.closeConnection();
            finallyBlock(ps, rs);
        }
        return parcels;
    }
}
