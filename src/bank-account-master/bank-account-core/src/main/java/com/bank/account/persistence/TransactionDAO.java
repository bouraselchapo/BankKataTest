////////////////////////////////////////////////////////////////////
//
// File: TransactionDAO.java
// Created: 13 août 2017 21:14:37
// Author:HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.TransactionType;

/**
 * Data access object interface for all transaction objects.
 * 
 * @author HICHAM BOURAS
 *
 */
public interface TransactionDAO {

  /**
   * Add transaction to the repository.
   * 
   * @param account
   *            Transaction account.
   * @param amount
   *            of transaction.
   * @param date
   *            of transaction.
   * @param transactionType
   *            type of transaction {@link TrnsactionType}.
   */
  void addTransaction(LocalDateTime date, Account account, TransactionType transactionType,
      BigDecimal amount);

}
