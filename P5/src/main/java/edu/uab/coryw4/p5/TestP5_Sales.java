/*
 * File: TestP5_Sales.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * Test for Sales System
 *
 * @author coryw4
 */
public class TestP5_Sales {

    public static void main(String[] args) {
        println("TestSalesSystem");

        //Creating bank accounts
        ArrayList<EBankRecord> bankAccounts = new ArrayList<>();

        EBankRecord bankAccount1 = new EBankRecord(new EBankAccount("First Student", 100.0), "stu1", 1);
        bankAccounts.add(bankAccount1);
        println("New EBank account " + bankAccount1.getBlazerID() + " created with balance "
                + bankAccount1.getEBankAccount().getBalance());
        EBankRecord bankAccount2 = new EBankRecord(new EBankAccount("Second Student", 80.0), "stu2", 1);
        bankAccounts.add(bankAccount2);
        println("New EBank account " + bankAccount2.getBlazerID() + " created with balance "
                + bankAccount2.getEBankAccount().getBalance());
        EBankRecord bankAccount3 = new EBankRecord(new EBankAccount("Third Faculty/Employee", 150.0), "fac1", 4);
        bankAccounts.add(bankAccount3);
        println("New EBank account " + bankAccount3.getBlazerID() + " created with balance "
                + bankAccount3.getEBankAccount().getBalance());

        // Make a ECardDB to use
        ECardDB db = ECardDB.getInstance();

        ECard ec1 = db.issueCard("First Student", "stu1", 1);
        ECard ec2 = db.issueCard("Second Student", "stu2", 1);
        ECard ec3 = db.issueCard("Third Faculty/Employee", "fac1", 4);

        //Creating EBank with conversion rate 10 dollars
        EBank ebank = new EBank(bankAccounts, 10);

        //Creating products
        ArrayList<ProductIdentity> products = new ArrayList<>();
        ProductIdentity prod1 = new ProductIdentity(101, "Product1", 17.5);
        products.add(prod1);
        ProductIdentity prod2 = new ProductIdentity(102, "Product2", 10.7);
        products.add(prod2);
        ProductIdentity prod3 = new ProductIdentity(103, "Product3", 14.0);
        products.add(prod3);

        ECardLogger logger = new PrintLogger();

        //Creating a sale system instance
        SalesSystem salesSystem = new SalesSystem(products);
        salesSystem.initialize("sale123loc");
        salesSystem.add(logger);
        println("Opening balance (EBucks) in the " + bankAccount1.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount1.getBlazerID()));
        //Price of the product with tax
        double price = salesSystem.selectProduct(prod1);
        println("Product price:  " + prod1.getPrice() + " | Product price with tax: " + price);
        salesSystem.validate(ec1);
        logger.log(bankAccount1.getBlazerID(), "ECard validated.");
        printTestResult(ebank.withdraw(bankAccount1.getBlazerID(), price, "Agent123", ec1.getCode(),
                "Purchased product " + prod1.getDesciption()), "Product purchasing successful.");
        logger.log(bankAccount1.getBlazerID(), ebank.transactions.get(0).getEbucks() + " EBucks withdrawn from the account.");
        println("Ending balance (EBucks) in the " + bankAccount1.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount1.getBlazerID()));
        println("Opening balance (EBucks) in the " + bankAccount2.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount2.getBlazerID()));
        printTestResult(ebank.deposit(bankAccount2.getBlazerID(), 50.0, "Agent456", ec2.getCode(), "Deposited money in the "
                + bankAccount2.getEBankAccount().getCustomer() + " account"), "Money deposit successful.");
        logger.log(bankAccount2.getBlazerID(), ebank.transactions.get(1).getDollars() + " dollars deposited successfully.");
        println("Ending balance (EBucks) in the " + bankAccount2.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount2.getBlazerID()));
        println("Opening balance (EBucks) in the " + bankAccount3.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount3.getBlazerID()));
        ebank.withdraw(bankAccount3.getBlazerID(), 131, "Agent123", ec3.getCode(), "Withdrawn money from "
                + bankAccount3.getEBankAccount().getCustomer() + " account");
        logger.log(bankAccount3.getBlazerID(), ebank.transactions.get(2).getEbucks() + " EBucks withdrawn successfully");
        println("Ending balance (EBucks) in the " + bankAccount3.getBlazerID() + " account is "
                + ebank.getBalance(bankAccount3.getBlazerID()));
        //Generating daily report of transactions
        println("Daily Report");
        for (int i = 0; i < ebank.transactions.size(); i++) {
            EBankTransaction transaction = ebank.transactions.get(i);
            println("Transaction " + (i + 1) + " on " + transaction.getDate() + " | Description: "
                    + transaction.getDescription() + " | Source/Destination: " + transaction.getSource() + " "
                    + transaction.getDestination() + " | EBucks: " + transaction.getEbucks() + " | Dollars: "
                    + transaction.getDollars());
        }
    }

    // convenience println
    private static void println(String s) {
        System.out.println(s);
    }

    // print s after noting whether or not test passed
    // b will be true if test passed
    private static void printTestResult(boolean b, String s) {
        if (b) {
            println("Test passed: " + s);
        } else {
            println("Test failed: " + s);
        }
    }
}
