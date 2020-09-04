/*
 * File: EBankAccount.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * An ebank account.
 *
 * @author coryw4
 */
public class EBankAccount {

    private String customer;
    private double amountInAccount;

    public EBankAccount(String customer, double amountInAccount) {
        this.customer = customer;
        this.amountInAccount = amountInAccount;
    }

    public void deposit(double ebucks) {
        amountInAccount += ebucks;
    }

    public void withdraw(double ebucks) {
        amountInAccount -= ebucks;
    }

    public Double getBalance() {
        return amountInAccount;
    }

    public String getCustomer() {
        return customer;
    }
}
