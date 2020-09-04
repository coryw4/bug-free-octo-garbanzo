/*
 * File: TestP5.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.ArrayList;

/**
 * Test for Sports Gate
 *
 * @author coryw4
 */
public class TestP5_Sports {

    public static void main(String[] args) {
        println("TestSportsGate");

        // Make a ECardDB to use
        ECardDB db = ECardDB.getInstance();

        println("Creating ECards");
        ECard ec1 = db.issueCard("First Student", "stu1", 1);
        ECard ec2 = db.issueCard("Second Student", "stu2", 1);
        ECard ec3 = db.issueCard("Third Student", "stu3", 1);
        ECard ec4 = db.issueCard("Fourth Student", "stu4", 1);
        ECard ec5 = db.issueCard("Fifth Faculty/Employee", "fac1", 2);
        println("Creating ECardLogger.");
        ECardLogger logger = new PrintLogger();

        println("Creating SportsGate.");
        //Creating a Sports Gate with 3 students limit
        SportsGate sportsGate = new SportsGate(3);
        sportsGate.initialize("sports123loc");
        sportsGate.add(logger);
        println("Creating list of admitted students.");
        SportsGate.admittedStudents = new ArrayList<>();
        printTestResult(sportsGate.validate(ec1), "stu1 entry successful.");
        logger.log("stu1", "First Student has entered the sports event");
        printTestResult(sportsGate.validate(ec2), "stu2 entry successful.");
        logger.log("stu2", "Second Student has entered the sports event");
        printTestResult(sportsGate.validate(ec5), "fac1 is invalid for event.");
        logger.log("fac1", "Fifth Faculty/Employee invalid and not entered the sports event");
        printTestResult(sportsGate.validate(ec3), "stu3 entry successful.");
        logger.log("stu3", "Third Student has entered the sports event");
        printTestResult(sportsGate.validate(ec4), "stu4 entry unssuccessful.");
        logger.log("stu4", "Fourth Student has not entered, space not available.");
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
