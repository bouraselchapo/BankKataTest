////////////////////////////////////////////////////////////////////
//
// File: AccountModel.java
// Created: 18 ao�t 2017 09:08:44
// Author: PHBS06171
//
////////////////////////////////////////////////////////////////////

package com.bank.account.web.models;

/**
 * Date Transfer Model For the account. 
 * model = { 
 *           client : { name : '', uid: '' }, 
 *           deposeWithdrawAmount : 0.0, 
 *           transferAmount : 0.0
 *         }
 * 
 * @author PHBS06171
 *
 */
public class AccountModel {

  private ClientModel client;
  private Double deposeWithdrawAmount;
  private String selectedAccount;
  private Double transferAmount;

  /**
   * @return the client
   */
  public ClientModel getClient() {
    return client;
  }

  /**
   * @return the deposeWithdrawAmount
   */
  public Double getDeposeWithdrawAmount() {
    return deposeWithdrawAmount;
  }

  /**
   * @return
   */
  public String getSelectedAccount() {
    return selectedAccount;
  }

  /**
   * @return the transferAmount
   */
  public Double getTransferAmount() {
    return transferAmount;
  }

  /**
   * @param client the client to set
   */
  public void setClient(ClientModel client) {
    this.client = client;
  }

  /**
   * @param deposeWithdrawAmount the deposeWithdrawAmount to set
   */
  public void setDeposeWithdrawAmount(Double deposeWithdrawAmount) {
    this.deposeWithdrawAmount = deposeWithdrawAmount;
  }

  /**
   * @param selectedAccount the selectedAccount to set
   */
  public void setSelectedAccount(String selectedAccount) {
    this.selectedAccount = selectedAccount;
  }

  /**
   * @param transferAmount the transferAmount to set
   */
  public void setTransferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
  }

}
