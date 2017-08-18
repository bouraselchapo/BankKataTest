////////////////////////////////////////////////////////////////////
//
// File: AccountHolder.java
// Created: 12 août 2017 10:32:50
// Author:HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.domaine;

/**
 * Pojo Class representing the Bank account holder.
 * 
 * @author HICHAM BOURAS
 *
 */
public class BankClient {

  /**
   * A client unique ID.
   */
  private String clientUID;

  /**
   * Client name.
   */
  private String name;

  /**
   * Client object constructor.
   * 
   * @param string
   */
  public BankClient(String clientUID, String name) {
    super();
    this.clientUID = clientUID;
    this.name = name;
  }

  /**
   * Client UID getter.
   * 
   * @return the uID
   */
  public String getClientUID() {
    return clientUID;
  }

  /**
   * Client Name getter
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }


  /**
   * @param uID the uID to set
   */
  public void setClientUID(String uID) {
    clientUID = uID;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
