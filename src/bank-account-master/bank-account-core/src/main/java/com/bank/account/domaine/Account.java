////////////////////////////////////////////////////////////////////
//
// File: Account.java
// Created: 12 août 2017 11:00:41
// Author:HICHAM BOURAS1
//
////////////////////////////////////////////////////////////////////

package com.bank.account.domaine;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

/**
 * Pojo for Bank accounts objects.
 * 
 * @author HICHAM BOURAS
 *
 */
public class Account {

  /**
   * Unique bank account ID.
   */
  private String accountRIB;

  /**
   * Account current balance.
   */
  private BigDecimal balance;

  /**
   * The Bank Customer/account Holder.
   */
  private BankClient client;

  /**
   * Set of all current account transaction entries
   */
  private Set<Transaction> transactionSet;

  /**
   * Account constructor from client object.
   * 
   * @param client account holder {@link BankClient}
   */
  public Account(BankClient client) {
    this.client = client;
    // To simplify the systeme we set the account RIB with
    // client UID
    this.accountRIB = client.getClientUID();
    this.balance = new BigDecimal(0.0);
    this.transactionSet = new TreeSet<Transaction>();
  }

  /**
   * Client getter.
   * 
   * @return the bank account client.
   */
  public BankClient getAccountHolder() {
    return client;
  }

  /**
   * Account RIB getter.
   * 
   * @return the account RIB.
   */
  public String getAccountRIB() {
    return accountRIB;
  }

  /**
   * Account Balance getter.
   * 
   * @return the account balance
   */
  public BigDecimal getBalance() {
    return balance;
  }

  /**
   * @return the transactionSet
   */
  public Set<Transaction> getTransactionSet() {
    return transactionSet;
  }

  /**
   * @param accountHolder the accountHolder to set
   */
  public void setAccountHolder(BankClient accountHolder) {
    this.client = accountHolder;
  }

  /**
   * @param accountRIB the accountRIB to set
   */
  public void setAccountRIB(String accountRIB) {
    this.accountRIB = accountRIB;
  }

  /**
   * @param balance the balance to set
   */
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  /**
   * @param transactionSet the transactionSet to set
   */
  public void setTransactionSet(Set<Transaction> transactionSet) {
    this.transactionSet = transactionSet;
  }

}
