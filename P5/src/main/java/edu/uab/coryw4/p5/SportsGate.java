/*
 * File: EBankAccount.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * Allow access to up to n students at sports event (Subclass of AuthPoint).
 *
 * @author coryw4
 */
public class SportsGate extends AuthPoint {

    private int maxStudents;
    protected static ArrayList<RollRecord> admittedStudents;

    public SportsGate(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    @Override
    protected boolean valid(ECardRecord ecr) {
        String blazerID = ecr.getBlazerID();
        boolean valid = false;
        if (ecr.isStudent()) {
            if (admittedStudents.size() < maxStudents) {
                admittedStudents.add(new RollRecord("unknown_course", "unknown_day", blazerID));
                log(blazerID, "Green Light Flash");
            } else {
                log(blazerID, "Red Light Flash");
            }
            valid = true;
        } else {
            invalid(blazerID);
            valid = false;
        }

        return valid;
    }

    @Override
    protected void invalid(long cardCode) {
        log("" + cardCode, "Red Light Flash");
    }

    @Override
    protected void invalid(String blazerID) {
        log(blazerID, "Red Light Flash");
    }

}
