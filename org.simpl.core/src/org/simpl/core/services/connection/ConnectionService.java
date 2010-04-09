/*
 * 
 */
package org.simpl.core.services.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ConnectionService {
  private Vector<JDCConnection> connections;
  private String url, user, password;
  final private long timeout = 60000;
  private ConnectionReaper reaper;
  final private int poolsize = 10;

  public ConnectionService(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    connections = new Vector<JDCConnection>(poolsize);
    reaper = new ConnectionReaper(this);
    reaper.start();
  }

  public synchronized void reapConnections() {
    long stale = System.currentTimeMillis() - timeout;
    Enumeration<JDCConnection> connlist = connections.elements();

    while ((connlist != null) && (connlist.hasMoreElements())) {
      JDCConnection conn = (JDCConnection) connlist.nextElement();

      if ((conn.inUse()) && (stale > conn.getLastUse()) && (!conn.validate())) {
        removeConnection(conn);
      }
    }
  }

  public synchronized void closeConnections() {
    Enumeration<JDCConnection> connlist = connections.elements();

    while ((connlist != null) && (connlist.hasMoreElements())) {
      JDCConnection conn = (JDCConnection) connlist.nextElement();
      removeConnection(conn);
    }
  }

  private synchronized void removeConnection(JDCConnection conn) {
    connections.removeElement(conn);
  }

  public synchronized Connection getConnection() throws SQLException {
    JDCConnection c;

    for (int i = 0; i < connections.size(); i++) {
      c = (JDCConnection) connections.elementAt(i);
      if (c.lease()) {
        return c;
      }
    }

    Connection conn = DriverManager.getConnection(url, user, password);
    c = new JDCConnection(conn, this);
    c.lease();
    connections.addElement(c);

    return c.getConnection();
  }

  public synchronized void returnConnection(JDCConnection conn) {
    conn.expireLease();
  }
}

class ConnectionReaper extends Thread {
  private ConnectionService pool;
  private final long delay = 300000;

  ConnectionReaper(ConnectionService pool) {
    this.pool = pool;
  }

  public void run() {
    while (true) {
      try {
        sleep(delay);
      } catch (InterruptedException e) {
      }
      pool.reapConnections();
    }
  }
}