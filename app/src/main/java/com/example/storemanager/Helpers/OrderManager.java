package com.example.storemanager.Helpers;

import android.content.Context;

import com.example.storemanager.database.CartFood;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager sOrder;
    public double totalMealPrice = 0.0;
    public ArrayList<CartFood> mCart;

    public static OrderManager get(Context context) {
        if (sOrder == null) {
            sOrder = new OrderManager(context);
        }
        return sOrder;
    }

    private OrderManager(Context context) {
        mCart = new ArrayList<>();
    }

    public List<CartFood> getCartItems() {
        return mCart;
    }

    public double calculateOverallTotal(double num) {
        totalMealPrice += num;
        return totalMealPrice;
    }// calculateTotalMealPrice


    public void insertFood(CartFood item) {
        mCart.add(item);
    }
}
