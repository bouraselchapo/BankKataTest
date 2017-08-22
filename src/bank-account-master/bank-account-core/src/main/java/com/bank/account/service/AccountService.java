////////////////////////////////////////////////////////////////////
//
// File: IaccountService.java
// Created: Aug 10, 2017 8:43:10 PM
// Author: hicham
////////////////////////////////////////////////////////////////////
package com.bank.account.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.domaine.Transaction;

/**
 * Interface for all account operations.
 * 
 * @author Hicham.
 *
 */
public interface AccountService {

  /**
   * Create an Account object from a given BankClient object.
   * 
   * @param accountHolder for Bank client.
   * 
   */
  void createAccount(BankClient accountHolder);

  /**
   * Depose a some amount of money to the given account.
   * 
   * @param account 
   *            the account to depose into.
   * @param localDateTime 
   *            Date and time of the operation.
   * @param amount 
   *            the money amount of the operation.
   * 
   */
  void depositAmount(LocalDateTime localDateTime, Account account, BigDecimal amount);

  /**
   * Account getter by bank client object.
   * 
   * @param client
   *            the client object of account holder.
   * @return account
   *            the account object of the given client.
   */       
  Account getAccountByClient(BankClient client);

  /**
   * Account by BAN (RIB) getter.
   * 
   * @param rib 
   *            the account BAN.
   * @return Account
   *            the bank account for the given BAN (RIB).
   */
  Account getAccountByRib(String rib);

  /**
   * All account Objects getter.
   * 
   * @return
   *      All accounts in the repository.
   */
  Set<Account> getAllAccounts();

  /**
   * All account transactions getter.
   * 
   * @param account
   *            {@link Account} object.
   * @return Set of transactions
   *            representing all transaction for the given account.
   */
  Set<Transaction> getTransactionsHistory(Account account);

  /**
   * All transfer transactions getter.
   * 
   * @param account
   *            {@link Account} object.
   * @return Set of transfer transactions
   *            representing all transfer transactions for the given account.
   */
  Set<Transaction> getTransferTransactionsHistory(Account sourceAccount);

  /**
   * Method for transferring money from an account to an other.
   * 
   * @param sourceAccount
   *            account to transfer money from.
   * @param destinationAccount
   *            account to transfer money to.
   * @param transferAmount
   *            amount of the transfer.
   * @param transactionDate
   *            date of the transfer.
   */
  void transferMoney(LocalDateTime transactionDate, Account sourceAccount,
      Account destinationAccount, BigDecimal transferAmount);

  /**
   * Withdraw the given amount of money to the given account.
   * 
   * @param account 
   *            the account to depose into.
   * @param localDateTime 
   *            Date and time of the operation.
   * @param amount 
   *            the money amount of the operation.
   */
  void withdrawAmount(LocalDateTime localDate, Account account, BigDecimal accountNumber);

}
