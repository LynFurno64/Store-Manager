package com.example.storemanager.database;


/**
 *
 * @author mesha and Ché
 */
public class Dish {
    public String dish;
    public double price;
    public int timesOrder;

    public Dish(String dish, double price, int timesOrder) {
        this.dish = dish;
        this.price = price;
        this.timesOrder = timesOrder;
    }

    // Get existing Dishes
    public String getDish() {
        return dish;
    }

    public double getPrice() {
        return price;
    }

    public int getTimesOrder() {
        return timesOrder;
    }

    // Add new Dishes
    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTimesOrder(int timesOrder) {
        this.timesOrder = timesOrder;
    }
}
