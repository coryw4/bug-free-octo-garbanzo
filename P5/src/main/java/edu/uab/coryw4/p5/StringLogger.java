/*
 * File: StringLogger.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * A ECardLogger which logs the strings to a string array that can be retrieved.
 *
 * @author coryw4
 */
public class StringLogger implements ECardLogger {

    private ArrayList<String> entries;

    /**
     * Create a StringLogger.
     */
    public StringLogger() {
        entries = new ArrayList<>();
    }

    /**
     * Create a log entry.
     *
     * @param blazerID user code
     * @param message text
     */
    @Override
    public void log(String blazerID, String message) {
        entries.add(blazerID + ": " + message);
    }

    /**
     * Return an array matching content and reset log.
     *
     * @return an array containing the strings written.
     */
    public Object[] extractLog() {
        Object[] log = entries.toArray();
        entries.clear();
        return log;
    }

}
