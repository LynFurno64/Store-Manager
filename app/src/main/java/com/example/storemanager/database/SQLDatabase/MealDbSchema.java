package com.example.storemanager.database.SQLDatabase;

public class MealDbSchema {
    public static final class MealTable{
        public static final String NAME = "Meals";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String MealName ="name";
            public static final String MealCost = "price";
            public static final String TimesOrder = "timesOrder";
        }
    }
}
