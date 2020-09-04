/*
 * File: TestP1.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * Test P1 for Spring 2020.
 *
 * @author coryw4
 */
public class TestP1 {

    /**
     * Exercise program P1's objects.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        println("TestP1");

        // Make a ECardDB to use
        ECardDB db = ECardDB.getInstance();

        ECard ec1 = db.issueCard("First Student", "stu1", 1);
        ECard ec2 = db.issueCard("Second Faculty/Employee", "fac2", 6);
        ECard ec3 = db.issueCard("Third visitor", "vis1", 0);

        printTestResult(ec1.getCode() >= 1000000000L
                && ec1.getCode() < 10000000000L,
                "ec1 10 digit");
        printTestResult(ec2.getCode() >= 1000000000L
                && ec2.getCode() < 10000000000L,
                "ec2 10 digit");
        printTestResult(ec3.getCode() >= 1000000000L
                && ec3.getCode() < 10000000000L,
                "ec3 10 digit");

        printTestResult(ec1.getCode() != ec2.getCode()
                && ec1.getCode() != ec3.getCode()
                && ec2.getCode() != ec3.getCode(),
                "code uniqueness test");

        printTestResult(db.validate(ec1) != null, "ec1 in database");
        printTestResult(db.validate(ec2) != null, "ec2 in database");
        printTestResult(db.validate(ec3) != null, "ec3 in database");
        printTestResult(db.validate(new ECard()) == null, "fake card test");

        printTestResult(db.validate(ec1).getBlazerID().equals("stu1"),
                "ec1 blazerid");
        printTestResult(db.validate(ec2).getDisplayName().equals("Second Faculty/Employee"),
                "ec2 displayName");
        printTestResult(db.validate(ec3).isVisitor(), "ec3 visitor");
        printTestResult(db.validate(ec3).isCancelled() == false, "ec3 valid");
        db.cancel("vis1");      // cancel ec3
        printTestResult(db.validate(ec3) == null, "cancelled ec3 invalid");
        printTestResult(db.validate(ec2).isFaculty(), "ec2 faculty");
        printTestResult(db.validate(ec2).isEmployee(), "ec2 employee");
        printTestResult(db.validate(ec2).isVisitor() == false, "ec2 visitor");
        printTestResult(db.validate(ec2).isStudent() == false, "ec2 student");
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
