package com.example.storemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager.database.SQLDatabase.MealBaseHelper;
import com.example.storemanager.database.SQLDatabase.MealDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuLab {
    private static MenuLab sMenuLab;
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

    // Add Meals in Database
    public void addMeal(Meal m) {
        ContentValues values = getContentValues(m);
        mDatabase.insert(MealDbSchema.MealTable.NAME, null, values);
    }

    // Update Meals, Meanly for updating Times ordered
    public void updateCrime(Meal meal) {
        String uuidString = meal.getId().toString();
        ContentValues values = getContentValues(meal);
        mDatabase.update(MealDbSchema.MealTable.NAME, values,
                MealDbSchema.MealTable.Cols.UUID + " = ?",
                new String[] { uuidString }); // String is not placed directly in the clause to prevent SQL Injection
    }

    public List<Meal> getMenu() {
        return new ArrayList<>();
    }

    public Meal getMeals(UUID id) {
        /**
        for (Meal food : mMenu) {
            if (food.getId().equals(id)) {
                return food;
            }
        }**/
        return null;
    }

    private static ContentValues getContentValues(Meal meal) {
        ContentValues values = new ContentValues();
        values.put(MealDbSchema.MealTable.Cols.UUID, meal.getId().toString());
        values.put(MealDbSchema.MealTable.Cols.MealName, meal.getName());
        values.put(MealDbSchema.MealTable.Cols.MealCost, meal.getPrice());
        values.put(MealDbSchema.MealTable.Cols.TimesOrder, meal.getTimesOrder());
        return values;
    }// getContentValues

}
