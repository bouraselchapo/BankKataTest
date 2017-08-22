////////////////////////////////////////////////////////////////////
//
// File: AccountTransferHistoryFeatureTest.java
// Created: 15 août 2017 11:37:15
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.test.acceptance;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.domaine.Transaction;
import com.bank.account.domaine.TransactionType;
import com.bank.account.test.builders.AccountBuilder;
import com.bank.account.test.conf.AppTestConfig;

/**
 * Acceptance test for transfer part business requirement :
 * 
 * ● Write some code to query a bank account's transaction history for any
 * bank transfers to or from a specific account
 *
 * @author HICHAM BOURAS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppTestConfig.class})
public class AccountTransferHistoryFeatureTest {

  @Autowired
  private com.bank.account.service.AccountService accountService;

  @Autowired
  private BankClient client1;

  @Test
  public void clientCanQueryMoneytransferHostroryFromOrToAnyAccount() {
    // Creation of the needed accounts.
    final Account sourceAccount = new Account(client1);

    final Account destinationAccount =
        AccountBuilder.getBuilder().clientUID("ZXF211TR1116").clientName("John").build();

    // Depose some money to the source account.
    accountService.depositAmount(LocalDateTime.now(), sourceAccount, new BigDecimal(5000));

    // transfer the money from the source account to destination.
    accountService.transferMoney(LocalDateTime.now(), sourceAccount, destinationAccount,
        new BigDecimal(1000));

    // Test the transactions trace for the source Account.
    final Set<Transaction> sourceTransferTransactionHistorySet =
        accountService.getTransferTransactionsHistory(sourceAccount);

    assertThat(sourceTransferTransactionHistorySet.size()).isEqualTo(1);

    final Transaction transferFromTransaction =
        sourceTransferTransactionHistorySet.iterator().next();
    assertThat(transferFromTransaction.getAccount()).isEqualTo(sourceAccount);
    assertThat(transferFromTransaction.getAmount()).isEqualTo(new BigDecimal(1000));
    assertThat(transferFromTransaction.getTransactionType())
    .isEqualTo(TransactionType.TRANSFER_FROM);

    // Test the transactions trace for the destination Account.
    final Set<Transaction> destinationTransferTransactionHistory =
        accountService.getTransferTransactionsHistory(destinationAccount);

    assertThat(destinationTransferTransactionHistory.size()).isEqualTo(1);

    final Transaction transferToTransaction =
        destinationTransferTransactionHistory.iterator().next();
    assertThat(transferToTransaction.getAccount()).isEqualTo(destinationAccount);
    assertThat(transferToTransaction.getAmount()).isEqualTo(new BigDecimal(1000));
    assertThat(transferToTransaction.getTransactionType()).isEqualTo(TransactionType.TRANSFER_TO);
  }

}
