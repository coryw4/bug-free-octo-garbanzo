/*
 * File: ECardRecord.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * Model a record associated with an ECard.
 *
 * @author coryw4
 */
public class ECardRecord {

    private final long uid;           // unique code matching card
    private final String displayName; // showable name of user
    private final String blazerID;    // userid
    private final int type;           // type code (see below for encoding)
    private boolean cancelled;        // code if card is cancelled

    /**
     * Create an ECardRecord.
     *
     * @param card the matching ECard
     * @param displayName the ready to show name of the card holder
     * @param blazerID the userid of the card holder
     * @param type type of user based on adding values: 1 - student, 2 -
     * faculty, 4 - employee, and where a visitor will have type = 0
     */
    public ECardRecord(ECard card, String displayName, String blazerID, int type) {
        uid = card.getCode();
        this.displayName = displayName;
        this.blazerID = blazerID;
        this.type = type;
        this.cancelled = false;
    }

    // queries
    /**
     * Determine whether the given eCard matches this record.
     *
     * @param ecard user's ECard
     * @return true if match, false otherwise
     */
    public boolean isMatch(final ECard ecard) {
        return ecard.getCode() == uid;
    }

    /**
     * Determine whether the given eCard matches this record.
     *
     * @param code user's code
     * @return true if match, false otherwise
     */
    public boolean isMatch(final long code) {
        return code == uid;
    }

    /**
     * Get the display name that corresponds to the eCard.
     *
     * @return display name or null
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get the blazerID of that corresponds to the eCard.
     *
     * @return blazerID
     */
    public String getBlazerID() {
        return blazerID;
    }

    /**
     * Is this ECard holder a student?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isStudent() {
        return (type & 1) == 1;
    }

    /**
     * Is this ECard holder a faculty member?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isFaculty() {
        return (type & 2) == 2;
    }

    /**
     * Is this ECard holder an employee?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isEmployee() {
        return (type & 4) == 4;
    }

    /**
     * Is this ECard holder a visitor?
     *
     * @return true if yes, false if no (or token invalid)
     */
    public boolean isVisitor() {
        return type == 0;
    }

    /**
     * Is this card cancelled?
     *
     * @return true if cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancel card.
     */
    public void cancel() {
        cancelled = true;
    }
}
