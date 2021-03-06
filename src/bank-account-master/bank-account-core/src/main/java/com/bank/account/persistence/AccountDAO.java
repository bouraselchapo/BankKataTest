////////////////////////////////////////////////////////////////////
//
// File: AccountDAO.java
// Created: 13 août 2017 21:48:34
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.persistence;

import java.util.Set;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;


/**
 * Data access object interface for accounts repository.
 * 
 * @author HICHAM BOURAS
 *
 */
public interface AccountDAO {

  /**
   * Account getter by client object.
   * 
   * @param client 
   *            the account client
   * @return the account.
   */
  public Account getAccountByClient(BankClient client);

  /**
   * Account getter by RIB.
   * 
   * @param RIB
   *        account RIB.
   * @return Account
   *        for the given RIB.
   */
  Account getAccountByRIB(String rib);

  /**
   * All account Objects getter.
   * 
   * @return All accounts in the repository.
   */
  Set<Account> getAllAccounts();

  /**
   * Save account to the repository.
   * 
   * @param account.
   */
  void saveAccount(Account account);

}
