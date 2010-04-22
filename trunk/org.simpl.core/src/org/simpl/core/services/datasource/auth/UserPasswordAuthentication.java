package org.simpl.core.services.datasource.auth;

/**
 * <b>Purpose:</b>Holds information for the authentication with user name and
 * password.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class UserPasswordAuthentication extends Authentication {
  private String user;
  private String password;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}