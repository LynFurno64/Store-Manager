package com.example.storemanager.Helpers;

import android.content.Context;

import com.example.storemanager.database.CartFood;
import com.example.storemanager.database.Meal;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;

    public void insertFood(Meal item) {
        ArrayList<CartFood> listFood = getListCart();
    }

    public ArrayList<CartFood> getListCart(){
        return null;
    }
}
