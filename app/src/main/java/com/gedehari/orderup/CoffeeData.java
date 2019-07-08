package com.gedehari.orderup;

import androidx.annotation.NonNull;

public class CoffeeData {

    private String coffee, description;
    private int coffeeDrawables;
    private double price;

    public CoffeeData(String coffee, int coffeeDrawables, String description, double price) {
        this.coffee = coffee;
        this.description = description;
        this.coffeeDrawables = coffeeDrawables;
        this.price = price;
    }

    public String getCoffee() {
        return coffee;
    }

    public String getDescription() {
        return description;
    }

    public int getCoffeeDrawables() {
        return coffeeDrawables;
    }

    public double getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return coffee;
    }
}
