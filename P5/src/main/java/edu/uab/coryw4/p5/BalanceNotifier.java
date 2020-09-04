/*
 * File: BalanceNotifier.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * Notify user of low balance
 *
 * @author coryw4
 */
public class BalanceNotifier {

    private ArrayList<EBankRecord> bankAccounts;

    public BalanceNotifier(ArrayList<EBankRecord> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void send(String blazerId, double balance, String note) {
        for (EBankRecord bankAccount : bankAccounts) {
            if (bankAccount.getBlazerID().equals(blazerId)) {
                System.out.println("Sending email to customer " + bankAccount.getBlazerID() + "...\n{" + note
                        + "\nYour current account balance is " + balance + "}");
            }
        }
    }
}
