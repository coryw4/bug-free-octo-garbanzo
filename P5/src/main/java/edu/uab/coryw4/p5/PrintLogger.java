/*
 * File: PrintLogger.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * A ECard logger which outputs to stdout.
 *
 * @author coryw4
 */
public class PrintLogger implements ECardLogger {

    /**
     * Log message with blazerid and message to stdout.
     *
     * @param blazerID user associated with action being logged
     * @param message action being logged
     */
    public void log(String blazerID, String message) {
        System.out.println(blazerID + ": " + message);
    }
}
