/*
 * File: ECard.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * Model a ECard for identity.
 *
 * @author coryw4
 */
public class ECard {

    private static long counter = 1000000000L;
    private final long uid;

    /**
     * Create a card assigning it a unique 10 digit code. Cards with leading 0's
     * are not used.
     */
    public ECard() {
        uid = counter++;
    }

    /**
     * Return the code for this card.
     *
     * @return the card's code
     */
    public long getCode() {
        return uid;
    }

}
