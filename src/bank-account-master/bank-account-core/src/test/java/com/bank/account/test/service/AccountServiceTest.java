package com.bank.account.test.service;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.account.domaine.Account;
import com.bank.account.domaine.BankClient;
import com.bank.account.persistence.AccountDAO;
import com.bank.account.persistence.TransactionDAO;
import com.bank.account.service.AccountService;
import com.bank.account.service.impl.AccountServiceImpl;
import com.bank.account.test.builders.AccountBuilder;


/**
 * Account test Class.
 * 
 * @author hicham.
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Mock
  private AccountDAO accountDao;

  @InjectMocks
  private final AccountService accountService = new AccountServiceImpl();

  @Mock
  private TransactionDAO transactionDAO;

  @Test
  public void shouldCreateAccountTest() {
    // We start by creating a Bank client that will be the account holder.
    final BankClient client =
        AccountBuilder.getBuilder().clientUID("AQZ445376").clientName("John").buildClient();

    // Create the account
    accountService.createAccount(client);

    // Get account
    when(accountDao.getAccountByClient(any(BankClient.class))).thenReturn(new Account(client));
    final Account account = accountService.getAccountByClient(client);

    // Test that the account exists.
    assertThat(account).isNotNull();
  }

  @Test
  public void shouldDdepositAnAmount() {
    //
    final Account account =
        AccountBuilder.getBuilder().clientName("John").clientUID("AQZ445376").build();

    // Deposit value
    accountService.depositAmount(LocalDateTime.now(), account, new BigDecimal(1000));

    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal(1000));
  }

  @Test
  public void shouldWithdrawAnAmount() {
    //
    final Account account =
        AccountBuilder.getBuilder().clientName("John").clientUID("AQZ445376").build();

    // Deposit value
    accountService.depositAmount(LocalDateTime.now(), account, new BigDecimal(1000));

    // Withdraw value
    accountService.withdrawAmount(LocalDateTime.now(), account, new BigDecimal(600));

    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal(400));
  }
}
