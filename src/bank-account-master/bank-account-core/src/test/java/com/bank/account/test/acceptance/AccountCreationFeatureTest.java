////////////////////////////////////////////////////////////////////
//
// File: AccountCreationFeatureTest.java
// Created: 12 août 2017 10:15:31
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.test.acceptance;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.test.conf.AppTestConfig;

/**
 *
 * Acceptance test for first business requirement :
 * 
 * Write some code to create a bank application and to withdraw/deposit a valid amount of money
 * in/from the account
 *
 * @author HICHAM BOURAS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public class AccountCreationFeatureTest {

  @Autowired
  private com.bank.account.service.AccountService accountService;

  @Autowired
  private BankClient client;

  @Test
  public void clientCanDepositAnAmount() {
    //
    final Account account = new Account(client);

    // Deposit value
    accountService.depositAmount(LocalDateTime.now(), account, new BigDecimal(1000));

    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal(1000));
  }

  @Test(expected = IllegalArgumentException.class)
  public void clientCanNotDepositAnNonValidAmountOfMony(){
    final Account account = new Account(client);

    // Deposit value
    accountService.depositAmount(LocalDateTime.now(), account, new BigDecimal(-1000));
  }

  @Test(expected = IllegalArgumentException.class)
  public void clientCanNotWithdrawAnNotValideAmountOfMony(){
    final Account account = new Account(client);

    // Withdraw value
    accountService.withdrawAmount(LocalDateTime.now(), account, new BigDecimal(600));
  }

  public void clientCanWithdrawAnAmountOfMoney() {

    final Account account = new Account(client);

    // Deposit value
    accountService.depositAmount(LocalDateTime.now(), account, new BigDecimal(1000));

    // Withdraw value
    accountService.withdrawAmount(LocalDateTime.now(), account, new BigDecimal(600));

    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal(400));
  }

  @Test
  public void newAccountCanBeCreatedByClient() {
    // We start by creating a Bank client that will be the account holder.
    final BankClient client = new BankClient("AQZ445376", "John");

    // Create the account
    accountService.createAccount(client);

    // Get account
    final Account account = accountService.getAccountByClient(client);

    // Test that the account exists.
    assertThat(account).isNotNull();
  }
}
