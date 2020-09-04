/*
 * File: SalesSystem.java
 * Author: Cory Wilson coryw4@uab.edu
 * Assignment:  P5 - EE333 Spring 2020
 * Vers: 1.0.1 08/15/2020 caw - initial coding
 */
package edu.uab.coryw4.p5;

import java.util.List;

/**
 * Perform the sales transaction (AuthPoint).
 *
 * @author coryw4
 */
public class SalesSystem extends AuthPoint {

    private List<ProductIdentity> products;

    public SalesSystem(List<ProductIdentity> products) {
        this.products = products;
    }

    public double selectProduct(ProductIdentity product) {
        double price = 0;
        for (ProductIdentity prod : products) {
            if (product.getUpc() == prod.getUpc()) {
                //Adding 2% tax to the product price 
                price = prod.getPrice() + (prod.getPrice() * 0.2);
            }
        }
        return price;
    }

    @Override
    protected boolean valid(ECardRecord ecr) {
        String blazerID = ecr.getBlazerID();
        boolean valid = false;
        if (ecr.isStudent() || ecr.isEmployee() || ecr.isFaculty()) {
            log(blazerID, "Green Light Flash");
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
