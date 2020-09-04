/*
 * File: ECardRecord.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.Random;

/**
 * Model an EBankAccount record associated with an EBank.
 *
 * @author coryw4
 */
public class EBankRecord {

    private final long uid;           // unique code matching account
    private EBankAccount account;     // EBank account of the customer
    private final String blazerID;    // userid
    private final int type;           // type code

    /**
     * Create an EBankRecord.
     *
     * @param account the EBank account
     * @param blazerID the userid of the account holder
     * @param type type of user based on adding values: 1 - student, 2 -
     * faculty, 4 - employee, and where a visitor will have type = 0
     */
    public EBankRecord(EBankAccount account, String blazerID, int type) {
        Random rand = new Random(1000000000L);
        uid = rand.nextLong();
        this.account = account;
        this.blazerID = blazerID;
        this.type = type;
    }

    /**
     * Get the account that corresponds to the EBank Account.
     *
     * @return account or null
     */
    public EBankAccount getEBankAccount() {
        return account;
    }

    /**
     * Get the blazerID of that corresponds to the EBank Account.
     *
     * @return blazerID
     */
    public String getBlazerID() {
        return blazerID;
    }

    /**
     * Is this EBank Account holder a student?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isStudent() {
        return (type & 1) == 1;
    }

    /**
     * Is this EBank Account holder a faculty member?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isFaculty() {
        return (type & 2) == 2;
    }

    /**
     * Is this EBank Account holder an employee?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isEmployee() {
        return (type & 4) == 4;
    }

    /**
     * Is this EBank Account holder a visitor?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isVisitor() {
        return type == 0;
    }
}
