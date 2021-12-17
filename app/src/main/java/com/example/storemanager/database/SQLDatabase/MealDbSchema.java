package com.example.storemanager.database.SQLDatabase;

import android.content.ContentValues;

import com.example.storemanager.database.Meal;

public class MealDbSchema {
    public static final class MealTable{
        public static final String NAME = "meals";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String MealName ="name";
            public static final String MealCost = "price";
            public static final String TimesOrder = "timesOrder";
        }
        public static ContentValues getContentValues(Meal meal){
            ContentValues values = new ContentValues();

            values.put(Cols.UUID, meal.getId().toString());
            values.put(Cols.MealName,meal.getName());
            values.put(Cols.MealCost,meal.getPrice());
            values.put(Cols.TimesOrder,meal.timesOrder);

            return values;
        }

    }
}
