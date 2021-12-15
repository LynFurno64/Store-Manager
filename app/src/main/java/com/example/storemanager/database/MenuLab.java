package com.example.storemanager.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuLab {
    private static MenuLab sMenuLab;
    private List<Meal> mMenu;

    public static MenuLab get(Context context) {
        if (sMenuLab == null) {
            sMenuLab = new MenuLab(context);
        }
        return sMenuLab;
    }

    private MenuLab(Context context) {
        mMenu = new ArrayList<>();


        for (int i = 0; i < 100; i++) {
            Meal dish = new Meal();
            dish.setMeal("Meal #"+i);
            dish.setPrice(1.75 + i);
            mMenu.add(dish);
        }
    }

    public List<Meal> getMenu() {
        return mMenu;
    }

    public Meal getMeals(UUID id) {
        for (Meal food : mMenu) {
            if (food.getmId().equals(id)) {
                return food;
            }
        }
        return null;
    }

}
