/*
 * File: ProductIdentity.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

/**
 * A product identity.
 *
 * @author coryw4
 */
public class ProductIdentity {

    private int upc;
    private String description;
    private double price;

    public ProductIdentity(int upc, String description, double price) {
        this.upc = upc;
        this.description = description;
        this.price = price;
    }

    public long getUpc() {
        return upc;
    }

    public String getDesciption() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
