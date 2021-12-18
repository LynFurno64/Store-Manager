package com.example.storemanager.Helpers;

import static java.lang.StrictMath.round;

import android.content.Context;

import com.example.storemanager.database.CartFood;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static OrderManager sOrder;
    public double totalMealPrice = 0.00;
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

    public void destroy(){
        mCart.clear();
    }

    public double getTotalCartPrice(List<CartFood> c) {
        double fee = 0.00;
        for (CartFood food : c) {
            fee += food.getTotal();
        }
        return Double.parseDouble(df.format(fee));
    }// calculateTotalMealPrice


    public void insertFood(CartFood item) {
        mCart.add(item);
    }

    public void setTotalCartPrice(double i) {
        totalMealPrice = i;
    }
}
