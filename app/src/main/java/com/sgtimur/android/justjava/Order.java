package com.sgtimur.android.justjava;

import java.io.Serializable;

/**
 * Representation of the customer's order
 */
public class Order implements Serializable {
    private String customerName;
    private boolean hasWhippedCream;
    private boolean hasChocolate;
    private int cupsNumber;

    public Order() {}

    public Order(String customerName, boolean hasWhippedCream, boolean hasChocolate, int cupsNumber) {
        this.hasWhippedCream = hasWhippedCream;
        this.hasChocolate = hasChocolate;
        this.cupsNumber = cupsNumber;
        setCustomerName(customerName);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (!"".equals(customerName))
            this.customerName = customerName;
        else this.customerName = "Anonymous";
    }

    public boolean isHasWhippedCream() {
        return hasWhippedCream;
    }

    public void setHasWhippedCream(boolean hasWhippedCream) {
        this.hasWhippedCream = hasWhippedCream;
    }

    public boolean isHasChocolate() {
        return hasChocolate;
    }

    public void setHasChocolate(boolean hasChocolate) {
        this.hasChocolate = hasChocolate;
    }

    public int getCupsNumber() {
        return cupsNumber;
    }

    public void setCupsNumber(int cupsNumber) {
        this.cupsNumber = cupsNumber;
    }
}
