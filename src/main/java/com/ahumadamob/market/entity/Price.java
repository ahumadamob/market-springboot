package com.ahumadamob.market.entity;

public class Price {
    private double basePrice;
    private double tax; // Representado como porcentaje, por ejemplo, 15.0 para un 15%
    private double discount; // Representado como porcentaje, por ejemplo, 10.0 para un 10%
    private double surcharge; // Representado como porcentaje, por ejemplo, 5.0 para un 5%
    //private Date effectiveDate;
    //private Date expirationDate;

    // Getters y setters

    public double calculateFinalPrice() {
        double priceWithTax = basePrice + (basePrice * tax / 100);
        double priceWithDiscount = priceWithTax - (priceWithTax * discount / 100);
        double finalPrice = priceWithDiscount + (priceWithDiscount * surcharge / 100);
        return finalPrice;
    }
}
