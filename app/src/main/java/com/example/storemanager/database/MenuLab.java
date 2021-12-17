package com.example.storemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager.database.SQLDatabase.MealBaseHelper;

import java.util.List;
import java.util.UUID;

public class MenuLab {
    private static MenuLab sMenuLab;
    private List<Meal> mMenu;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MenuLab get(Context context) {
        if (sMenuLab == null) {
            sMenuLab = new MenuLab(context);
        }
        return sMenuLab;
    }

    private MenuLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MealBaseHelper(mContext)
                .getWritableDatabase();
    }

    public List<Meal> getMenu() {
        return mMenu;
    }

    public Meal getMeals(UUID id) {
        for (Meal food : mMenu) {
            if (food.getId().equals(id)) {
                return food;
            }
        }
        return null;
    }

}
