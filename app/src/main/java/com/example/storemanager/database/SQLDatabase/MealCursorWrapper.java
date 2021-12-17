package com.example.storemanager.database.SQLDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.storemanager.database.Meal;

import java.util.UUID;

public class MealCursorWrapper extends CursorWrapper {
    public MealCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Meal getMeal() {
        String uuidString = getString(getColumnIndex(MealDbSchema.MealTable.Cols.UUID));
        String name = getString(getColumnIndex(MealDbSchema.MealTable.Cols.MealName));
        double price = getDouble(getColumnIndex(MealDbSchema.MealTable.Cols.MealCost));
        int timesOrdered = getInt(getColumnIndex(MealDbSchema.MealTable.Cols.TimesOrder));

        Meal meal = new Meal(UUID.fromString(uuidString));
        meal.setName(name);
        meal.setPrice(price);
        meal.setTimesOrder(timesOrdered);
        return meal;
    }
}
