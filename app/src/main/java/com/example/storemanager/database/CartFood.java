package com.example.storemanager.database;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFood {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public String cart_name;
    public double cart_price;
    public int cart_amount;
    public double total;

    public CartFood(String cart_ame, double cart_price, int cart_amount) {
        this.cart_name = cart_ame;
        this.cart_price = cart_price;
        this.cart_amount = cart_amount;
        total = Double.parseDouble(df.format(cart_price * cart_amount));
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCart_name() {
        return cart_name;
    }

    public void setCart_name(String cart_name) {
        this.cart_name = cart_name;
    }

    public double getCart_price() {
        return cart_price;
    }

    public void setCart_price(double cart_price) {
        this.cart_price = cart_price;
    }

    public int getCart_amount() {
        return cart_amount;
    }

    public void setCart_amount(int cart_amount) {
        this.cart_amount = cart_amount;
    }
}
