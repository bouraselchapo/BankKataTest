////////////////////////////////////////////////////////////////////
//
// File: TransactionDAO.java
// Created: 13 août 2017 21:14:37
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.persistence.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.Transaction;
import com.bank.account.domaine.TransactionType;
import com.bank.account.persistence.TransactionDAO;

/**
 * This class is the implementation of TansactionDAO {@link TansactionDAO}.
 * 
 * @author HICHAM BOURAS
 *
 */
@Repository
public class TransactionDAOImpl implements TransactionDAO {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.bank.account.persistence.TransactionDAO#addTransaction(com.bank.account.domaine.Account,
   * com.bank.account.domaine.TransactionType, java.math.BigDecimal, java.time.LocalDateTime)
   */
  public void addTransaction(LocalDateTime date, Account account, TransactionType transactionType,
      BigDecimal amount) {
    account.getTransactionSet()
    .add(new Transaction(account, transactionType, amount, date, account.getBalance()));
  }
}
