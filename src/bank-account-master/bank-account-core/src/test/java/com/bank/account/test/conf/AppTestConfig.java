////////////////////////////////////////////////////////////////////
//
// File: AppTestConfig.java
// Created: 17 août 2017 11:54:07
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.test.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bank.account.domaine.BankClient;
import com.bank.account.test.builders.AccountBuilder;

/**
 * @author HICHAM BOURAS
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.bank.account"})
public class AppTestConfig {
  @Bean
  public BankClient bankClient() {
    final BankClient client =
        AccountBuilder.getBuilder().clientUID("AQZ445376").clientName("John").buildClient();
    // set properties, etc.
    return client;
  }
}
