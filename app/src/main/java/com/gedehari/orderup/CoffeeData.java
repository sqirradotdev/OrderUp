package com.gedehari.orderup;

public class CoffeeData {

    private String coffeeData;
    private int coffeeDrawables;

    public CoffeeData(String coffeeData, int coffeeDrawables) {
        this.coffeeData = coffeeData;
        this.coffeeDrawables = coffeeDrawables;
    }

    public String getCoffeeData() {
        return coffeeData;
    }

    public void setCoffeeData(String coffeeData) {
        this.coffeeData = coffeeData;
    }

    public int getCoffeeDrawables() {
        return coffeeDrawables;
    }

    public void setCoffeeDrawables(int coffeeDrawables) {
        this.coffeeDrawables = coffeeDrawables;
    }

    @Override
    public String toString() {
        return coffeeData;
    }
}
