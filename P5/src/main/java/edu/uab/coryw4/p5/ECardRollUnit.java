/*
 * File: ECardRollUnit.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * A unit to roll in class.
 *
 * @author coryw4
 */
public class ECardRollUnit extends AuthPoint {

    private String day = "unknown day";
    private String course = "unknown course";
    private ArrayList<RollRecord> present = new ArrayList<>();
    private boolean lastCardFac = false;

    /**
     * Set day.
     *
     * @param day string to log date (used till changed)
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Set course (if last card validated was faculty card).
     *
     * @param course string to log course (used till changed).
     *
     */
    public void setCourse(String course) {
        if (lastCardFac) {
            this.course = course;
        }
    }

    /**
     * Get the current course.
     *
     * @returns String course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Called by the above validate method when a card is successfully
     * validated. To be valid it must be a valid card, be either a student or
     * faculty card.
     *
     * @return true if valid (for roll, or faculty) otherwise false
     */
    protected boolean valid(ECardRecord ecr) {
        String blazerID = ecr.getBlazerID();
        boolean valid = false;

        if (ecr.isStudent()) {
            present.add(new RollRecord(course, day, blazerID));
            log(blazerID, "Green Light Flash");
            valid = true;
        } else if (!ecr.isFaculty()) {
            invalid(blazerID);
            valid = false;
        }

        if (ecr.isFaculty()) {
            log(blazerID, "Green Light On");
            log(blazerID, "long delay");
            log(blazerID, "Green Light Off");
            lastCardFac = true;
            valid = true;
        } else {
            lastCardFac = false;
        }

        return valid;
    }

    /**
     * Called by the above validate method when a card is not validated when
     * presented.
     *
     * @param cardCode - card code not being granted access
     */
    protected void invalid(long cardCode) {
        log("" + cardCode, "Red Light Flash");
    }

    /**
     * Called by the above validate method when a card is not validated when
     * presented.
     *
     * @param blazerID - user not being listed as present
     */
    protected void invalid(String blazerID) {
        log(blazerID, "Red Light Flash");
    }

    /**
     * Print a attendance list. The output will be blazerID, count per line The
     * logger is not closed by this routine to facilitate continued use if
     * desired.
     *
     * @param course string name of course to be reported on
     * @param logger an object that implements the eCardLogger interface
     */
    public void courseRoll(String course, ECardLogger logger) {

        String blazerID;
        ArrayList<String> blazerIDs = new ArrayList<>();

        // determine the list of blazerIDs for this class
        for (RollRecord rollRecord : present) {
            if (rollRecord.getCourse().equals(course)) {
                blazerID = rollRecord.getBlazerID();
                if (!blazerIDs.contains(blazerID)) {
                    blazerIDs.add(blazerID);
                }
            }
        }

        // sort the list of blazerIDs
        blazerIDs.sort(String.CASE_INSENSITIVE_ORDER);

        // for each blazerid, count number of times they are listed
        for (String bid : blazerIDs) {
            int count = 0;
            for (RollRecord rollRecord : present) {
                if (rollRecord.getBlazerID().equalsIgnoreCase(bid)
                        && rollRecord.getCourse().equalsIgnoreCase(course)) {
                    count++;
                }
            }
            logger.log(bid, "" + count);
        }
    }
}
