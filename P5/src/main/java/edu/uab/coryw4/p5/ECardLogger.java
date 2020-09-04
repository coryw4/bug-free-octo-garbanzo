/*
 * File: ECardLogger.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * An interface describing a logging function for ECard system components to use
 *
 * @author coryw4
 */
public interface ECardLogger {

    /**
     * Log message with blazerid and message.
     *
     * @param blazerID user associated with action being logged
     * @param message action being logged
     */
    public void log(String blazerID, String message);

    /**
     * Close logger - does nothing unless overridden.
     */
    public default void close() {

    }
}
