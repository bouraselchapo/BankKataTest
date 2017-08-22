////////////////////////////////////////////////////////////////////
//
// File: AccountServiceImpl.java
// Created: 12 août 2017 10:40:06
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.domaine.Transaction;
import com.bank.account.domaine.TransactionType;
import com.bank.account.persistence.AccountDAO;
import com.bank.account.persistence.TransactionDAO;
import com.bank.account.service.AccountService;

/**
 * Service implementation for AccountService {@link AccountService} interface.
 * 
 * @author HICHAM BOURAS
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

  /**
   * 
   */
  private static final String ERROR_BALANCE_EXCEDED = "The withdrawal is beyond the balance!";

  /**
   * Text for a negative amount drop.
   */
  private static final String ERROR_NEGATIVE_AMOUNT_TXT = "You can not drop a negative Amount!";

  /**
   * Text for a null value drop.
   */
  private static final String ERROR_NULL_AMOUNT_TXT = "You can not drop an null Amount!";

  @Autowired
  private AccountDAO accountDAO;

  @Autowired
  private TransactionDAO transactionDAO;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.bank.account.service.AccountService#createAccount(com.bank.account.domaine.AccountHolder)
   */
  @Override
  public void createAccount(BankClient client) {
    // Account creation by constructor.
    final Account account = new Account(client);
    // Persist the created account in an in memory MAP for now.
    accountDAO.saveAccount(account);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.bank.account.service.AccountService#depositAmount(java.time.LocalDateTime,
   * com.bank.account.domaine.Account, java.math.BigDecimal)
   */
  @Override
  public void depositAmount(LocalDateTime date, Account account, BigDecimal amount) {
    // We check the if the Amount has valid values.
    if (amount == null) {
      throw new IllegalArgumentException(ERROR_NULL_AMOUNT_TXT);
    } else if (amount.signum() == -1) {
      throw new IllegalArgumentException(ERROR_NEGATIVE_AMOUNT_TXT);
    }
    // update the account balance.
    account.setBalance(account.getBalance().add(amount));
    // trace the transaction.
    transactionDAO.addTransaction(date, account, TransactionType.DEPOSIT, amount);
  }

  /* (non-Javadoc)
   * @see com.bank.account.service.AccountService#getAccountByHolder(com.bank.account.domaine.AccountHolder)
   */
  @Override
  public Account getAccountByClient(BankClient client) {
    return accountDAO.getAccountByClient(client);
  }

  /* (non-Javadoc)
   * @see com.bank.account.serviceTest.AccountService#getAccountByRib(java.lang.String)
   */
  @Override
  public Account getAccountByRib(String rib) {
    return accountDAO.getAccountByRIB(rib);
  }

  /* (non-Javadoc)
   * @see com.bank.account.service.AccountService#getAllAccounts()
   */
  @Override
  public Set<Account> getAllAccounts() {
    return accountDAO.getAllAccounts();
  }

  /* (non-Javadoc)
   * @see com.bank.account.service.AccountService#getTransactionsHistory(com.bank.account.domaine.Account)
   */
  @Override
  public Set<Transaction> getTransactionsHistory(Account account) {
    return account.getTransactionSet();
  }

  /* (non-Javadoc)
   * @see com.bank.account.service.AccountService#getTransferTransactionsHistory(com.bank.account.domaine.Account)
   */
  @Override
  public Set<Transaction> getTransferTransactionsHistory(Account account) {
    final Set<Transaction> transferTransactions = account.getTransactionSet().stream()
        .filter(t -> (t.getTransactionType().equals(TransactionType.TRANSFER_FROM)
            || t.getTransactionType().equals(TransactionType.TRANSFER_TO)))
        .collect(Collectors.toSet());
    return transferTransactions;
  }

  /**
   * Method for transferring money from an account. 
   * 
   * @param account
   *            account to transfer money from.
   * @param amount
   *            amount of the transfer.
   * @param transactionDate
   *            date of the transfer.
   */
  private void transferFrom(LocalDateTime transactionDate, Account account, BigDecimal amount) {
    // update the account balance.
    account.setBalance(account.getBalance().add(amount.negate()));
    // trace the transaction.
    transactionDAO.addTransaction(transactionDate, account, TransactionType.TRANSFER_FROM, amount);

  }

  /* (non-Javadoc)
   * @see com.bank.account.service.AccountService#transferMoney(com.bank.account.domaine.Account, com.bank.account.domaine.Account, java.math.BigDecimal, java.time.LocalDate)
   */
  @Override
  public void transferMoney(LocalDateTime transactionDate, Account sourceAccount,
      Account destinationAccount, BigDecimal transferAmount) {
    transferFrom(transactionDate, sourceAccount, transferAmount);
    transferTo(transactionDate, destinationAccount, transferAmount);

  }

  /**
   * Method for transferring money to an account.
   *  
   * @param account
   *            account to transfer money to.
   * @param amount
   *            amount of the transfer.
   * @param transactionDate
   *            date of the transfer.
   */
  private void transferTo(LocalDateTime transactionDate, Account account, BigDecimal amount) {
    // update the account balance.
    account.setBalance(account.getBalance().add(amount));
    // trace the transaction.
    transactionDAO.addTransaction(transactionDate, account, TransactionType.TRANSFER_TO, amount);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.bank.account.service.AccountService#withdrawAmount(java.time.LocalDateTime,
   * com.bank.account.domaine.Account, java.math.BigDecimal)
   */
  @Override
  public void withdrawAmount(LocalDateTime transactionDate, Account account, BigDecimal amount) {
    // We check if the amount has a valid value.
    if (amount == null) {
      throw new IllegalArgumentException(ERROR_NULL_AMOUNT_TXT);
    } else if (amount.signum() == -1) {
      throw new IllegalArgumentException(ERROR_NEGATIVE_AMOUNT_TXT);
    } else if (amount.compareTo(account.getBalance()) > 0) {
      throw new IllegalArgumentException(ERROR_BALANCE_EXCEDED);
    }
    // update the account balance.
    account.setBalance(account.getBalance().add(amount.negate()));
    // trace the transaction.
    transactionDAO.addTransaction(transactionDate, account, TransactionType.WITHDRAW, amount);

  }

}
