package by.bsuir.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Admin on 14.04.2015.
 */
public class ConnectionPool {

    private static String driverClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/bd?useUnicode=true&amp;characterEncoding=UTF-8";
    private static String username = "root";
    private static String password = "pass";
    private static int maxConnection = 10;
    private static ArrayBlockingQueue<Connection> freeConnections;
    private static ArrayBlockingQueue<Connection> allConnections;
    private static ConnectionPool connectionPool;

    private static Logger logger =
            Logger.getLogger(ConnectionPool.class.getName());

    private ConnectionPool() {
        freeConnections = new ArrayBlockingQueue<Connection>(maxConnection);
        allConnections = new ArrayBlockingQueue<Connection>(maxConnection);
    }

    public static ConnectionPool initConnection(){
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            try {
                Class.forName(driverClassName);
                int i = 0;
                while (i != maxConnection) {
                    freeConnections.add(newConnection());
                    i++;
                }
                allConnections.addAll(freeConnections);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        Connection con;
        if (!freeConnections.isEmpty()) {
            con = (Connection) freeConnections.poll();
            if (con.isClosed()) {
                allConnections.remove();
                con = newConnection();
                allConnections.add(con);
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private static Connection newConnection() throws SQLException {
        Connection con;
        con = DriverManager.getConnection(url,username,password);
        return con;
    }

    public synchronized void freeConnection(Connection con) {
        if ((con != null) && (freeConnections.size() <= maxConnection)) {
            freeConnections.add(con);
        }
    }

    public void closeConnection(){
        for (Connection con : allConnections) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        freeConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeConnection();
    }
}
