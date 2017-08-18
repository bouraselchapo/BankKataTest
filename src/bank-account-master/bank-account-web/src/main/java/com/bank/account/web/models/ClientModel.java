////////////////////////////////////////////////////////////////////
//
// File: ClientModel.java
// Created: 18 août 2017 09:02:08
// Author: PHBS06171
//
////////////////////////////////////////////////////////////////////

package com.bank.account.web.models;

/**
 * Data Transfer Object for json client object. 
 * client : { name : '', uid: '' }
 * 
 * @author PHBS06171 
 *
 */
public class ClientModel {
  private String name;
  private String uid;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the uid
   */
  public String getUid() {
    return uid;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param uid the uid to set
   */
  public void setUid(String uid) {
    this.uid = uid;
  }
}
