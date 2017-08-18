////////////////////////////////////////////////////////////////////
//
// File: AppTestConfig.java
// Created: 17 août 2017 11:54:07
// Author: HICHAM BOURAS
//
////////////////////////////////////////////////////////////////////

package com.bank.account.test.conf;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author HICHAM BOURAS
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.bank.account"})
public class AppTestConfig {
}
