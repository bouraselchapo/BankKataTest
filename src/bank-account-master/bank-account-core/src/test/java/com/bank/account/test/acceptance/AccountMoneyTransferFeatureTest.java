////////////////////////////////////////////////////////////////////
//
// File: AccountMoneyTransferFeatureTest.java
// Created: 15 août 2017 09:37:15
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
import com.bank.account.test.builders.AccountBuilder;
import com.bank.account.test.conf.AppTestConfig;

/**
 * Acceptance test for transfer part business requirement :
 * 
 * ● Write some code to transfer a specified amount of money from one bank account (the payer) to
 * another (the payee)
 *
 * @author HICHAM BOURAS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppTestConfig.class})
public class AccountMoneyTransferFeatureTest {

  @Autowired
  private com.bank.account.service.AccountService accountService;

  @Autowired
  private BankClient client1;

  @Test
  public void userCanTransferAnAmountOfMoneyFromAnAccountToAnOther() {
    // Creation of the needed accounts.
    final Account sourceAccount = new Account(client1);

    final Account destinationAccount =
        AccountBuilder.getBuilder().clientUID("ZXF2111116").clientName("John").build();

    // Depose some money to the source account.
    accountService.depositAmount(LocalDateTime.now(), sourceAccount, new BigDecimal(5000));

    // transfer the money from the source account to destination.
    accountService.transferMoney(LocalDateTime.now(), sourceAccount, destinationAccount,
        new BigDecimal(1000));

    assertThat(sourceAccount.getBalance()).isEqualTo(new BigDecimal(4000));

    assertThat(destinationAccount.getBalance()).isEqualTo(new BigDecimal(1000));
  }

}
