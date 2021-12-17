package com.example.storemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager.database.SQLDatabase.MealBaseHelper;
import com.example.storemanager.database.SQLDatabase.MealCursorWrapper;
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

    public void deleteMeal(Meal m) {
        String uuidString = m.getId().toString();
        mDatabase.delete(MealDbSchema.MealTable.NAME,
                MealDbSchema.MealTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    // Update Meals, Meanly for updating Times ordered
    public void updateCrime(Meal meal) {
        String uuidString = meal.getId().toString();
        ContentValues values = getContentValues(meal);
        mDatabase.update(MealDbSchema.MealTable.NAME, values,
                MealDbSchema.MealTable.Cols.UUID + " = ?",
                new String[] { uuidString }); // String is not placed directly in the clause to prevent SQL Injection
    }

    private MealCursorWrapper queryMeals(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MealDbSchema.MealTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new MealCursorWrapper(cursor);
    }

    public List<Meal> getMenu() {
        List<Meal> meals = new ArrayList<>();
        MealCursorWrapper cursor = queryMeals(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                meals.add(cursor.getMeal());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return meals;
    }

    //getMeal(UUID) will look similar to getMenu(), except it will only need to pull the first
    //item, if it is there.
    public Meal getMeal(UUID id) {
        MealCursorWrapper cursor = queryMeals(
                MealDbSchema.MealTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMeal();
        } finally {
            cursor.close();
        }
    }// getMeals

    private static ContentValues getContentValues(Meal meal) {
        ContentValues values = new ContentValues();
        values.put(MealDbSchema.MealTable.Cols.UUID, meal.getId().toString());
        values.put(MealDbSchema.MealTable.Cols.MealName, meal.getName());
        values.put(MealDbSchema.MealTable.Cols.MealCost, meal.getPrice());
        values.put(MealDbSchema.MealTable.Cols.TimesOrder, meal.getTimesOrder());
        return values;
    }// getContentValues

}
