////////////////////////////////////////////////////////////////////
//
// File: Transaction.java
// Created: 15 août 2017 16:15:46
// Author:HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.domaine;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Pojo class for a Transaction object.
 * 
 * @author HICHAM BOURAS
 *
 */
public class Transaction implements Comparable<Transaction> {

  /**
   * The account object
   */
  private final Account account;
  /**
   * Money amount of the transaction.
   */
  private final BigDecimal amount;
  /**
   * The account balance after the transaction.
   */
  private final BigDecimal balance;
  /**
   * Transaction Date.
   */
  private final LocalDateTime date;
  /**
   * Transaction nature {@link TransactionType}
   */
  private final TransactionType transactionType;

  /**
   * All fields Transaction constructor.
   * 
   * @param account
   * @param transacctionType
   * @param amount
   * @param transactionDate
   * @param balance
   */
  public Transaction(Account account, TransactionType transactionType, BigDecimal amount,
      LocalDateTime transactionDate, BigDecimal balance) {
    this.account = account;
    this.transactionType = transactionType;
    this.amount = amount;
    this.date = transactionDate;
    this.balance = balance;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Transaction other) {
    return this.date.compareTo(other.date);
  }

  /**
   * Account getter.
   * 
   * @return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * Amount getter.
   * 
   * @return the amount
   */
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * After Transaction Account balance getter.
   * 
   * @return the balance
   */
  public BigDecimal getBalance() {
    return balance;
  }

  /**
   * Transaction date getter.
   * 
   * @return the date
   */
  public LocalDateTime getDate() {
    return date;
  }

  /**
   * TransactionType object getter.
   * 
   * @return the transactionType
   */
  public TransactionType getTransactionType() {
    return transactionType;
  }
}
