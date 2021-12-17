package com.example.storemanager.database;

import java.util.UUID;

public class Meal {
    private UUID id;
    public String name;
    public double price;
    public int timesOrder;

    public Meal() {
        id = UUID.randomUUID();
        timesOrder = 0;
    }

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
