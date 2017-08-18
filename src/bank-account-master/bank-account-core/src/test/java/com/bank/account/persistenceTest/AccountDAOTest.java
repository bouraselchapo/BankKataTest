////////////////////////////////////////////////////////////////////
//
// File: AccountDAO.java
// Created: 13 août 2017 21:48:34
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.persistenceTest;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.persistence.AccountDAO;
import com.bank.account.test.builders.AccountBuilder;
import com.bank.account.test.conf.AppTestConfig;


/**
 * Data access object interface for accounts repository class Test.
 * 
 * @author HICHAM BOURAS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public class AccountDAOTest {

  @Autowired
  private AccountDAO accountDAO;

  @Test
  public void shouldGetAccountByClient() {
    // We start by creating a Bank client that will be the account holder.
    final BankClient client1 =
        AccountBuilder.getBuilder().clientUID("AQZ445376").clientName("John").buildClient();

    final BankClient client2 =
        AccountBuilder.getBuilder().clientUID("LLLLOOOOOO").clientName("Bilal").buildClient();

    final Account account1 = new Account(client1);
    final Account account2 = new Account(client2);
    // Create the account
    accountDAO.saveAccount(account1);
    accountDAO.saveAccount(account2);

    // Get account
    assertThat(accountDAO.getAccountByClient(client1)).isEqualTo(account1);
  }

  @Test
  public void shouldGetAccountByRIB() {
    // We start by creating a Bank client that will be the account holder.
    final BankClient client1 =
        AccountBuilder.getBuilder().clientUID("AQZ445376").clientName("John").buildClient();

    final BankClient client2 =
        AccountBuilder.getBuilder().clientUID("LLLLOOOOOO").clientName("Bilal").buildClient();

    final Account account1 = new Account(client1);
    final Account account2 = new Account(client2);
    // Create the account
    accountDAO.saveAccount(account1);
    accountDAO.saveAccount(account2);

    // Get account
    assertThat(accountDAO.getAccountByRIB(account2.getAccountRIB())).isEqualTo(account2);
  }

  @Test
  public void shouldGetAllAccounts() {
    // We start by creating a Bank client that will be the account holder.
    final BankClient client1 =
        AccountBuilder.getBuilder().clientUID("AQZ445376").clientName("John").buildClient();

    final BankClient client2 =
        AccountBuilder.getBuilder().clientUID("LLLLOOOOOO").clientName("Bilal").buildClient();

    final Account account1 = new Account(client1);
    final Account account2 = new Account(client2);
    // Create the account
    accountDAO.saveAccount(account1);
    accountDAO.saveAccount(account2);

    // Get account
    assertThat(accountDAO.getAllAccounts().size()).isEqualTo(2);
  }

}
