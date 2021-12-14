package com.example.storemanager.database;

import java.util.UUID;

public class Meal {
    private UUID mId;
    public String meal;
    public double price;
    public int timesOrder;

    public Meal() {
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }


    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimesOrder() {
        return timesOrder;
    }

    public void setTimesOrder(int timesOrder) {
        this.timesOrder = timesOrder;
    }
}
