/*
 * File: EBank.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Serves as the bank for EBuck users.
 *
 * @author coryw4
 */
public class EBank {

    private ArrayList<EBankRecord> bankAccounts;
    protected ArrayList<EBankTransaction> transactions;
    private final double conversionRate;
    private BalanceNotifier notifier;

    public EBank(ArrayList<EBankRecord> bankAccounts, double conversionRate) {
        this.bankAccounts = bankAccounts;
        transactions = new ArrayList<>();
        this.conversionRate = conversionRate;
        notifier = new BalanceNotifier(bankAccounts);
    }

    public boolean deposit(String blazerId, double money, String agent, long code, String description) {
        boolean check = false;
        for (int i = 0; i < bankAccounts.size(); i++) {
            EBankRecord bankAccount = bankAccounts.get(i);
            if (bankAccount.getBlazerID().equals(blazerId)) {
                EBankAccount account = bankAccount.getEBankAccount();
                double ebucks = money / conversionRate;
                account.deposit(ebucks);
                bankAccounts.set(i, new EBankRecord(account, blazerId, i));
                EBankTransaction transaction = new EBankTransaction("", account.getCustomer(), agent, code, description,
                        new SimpleDateFormat("dd-MMM-yyyy").format(new Date()), ebucks, money);
                transaction.involves("", bankAccount.getBlazerID());
                transactions.add(transaction);
                check = true;
            }
        }
        return check;
    }

    public boolean withdraw(String blazerId, double ebucks, String agent, long code, String description) {
        boolean check = false;
        for (int i = 0; i < bankAccounts.size(); i++) {
            EBankRecord bankAccount = bankAccounts.get(i);
            if (bankAccount.getBlazerID().equals(blazerId)) {
                EBankAccount account = bankAccount.getEBankAccount();
                if (account.getBalance() > ebucks) {
                    account.withdraw(ebucks);
                    bankAccounts.set(i, new EBankRecord(account, blazerId, i));
                    EBankTransaction transaction = new EBankTransaction("", account.getCustomer(), agent, code, description,
                            new SimpleDateFormat("dd-MMM-yyyy").format(new Date()), ebucks, ebucks * conversionRate);
                    transaction.involves(bankAccount.getBlazerID(), "");
                    transactions.add(transaction);
                    check = true;
                }
            }
        }
        return check;
    }

    public double getBalance(String blazerId) {
        double balance = 0;
        for (EBankRecord bankAccount : bankAccounts) {
            if (bankAccount.getBlazerID().equals(blazerId)) {
                balance = bankAccount.getEBankAccount().getBalance();
                if (balance < 20) {
                    notifier.send(blazerId, balance, "Dear customer your account balance is less than 20 ebucks.");
                }
            }
        }
        return balance;
    }
}
