package com.example.storemanager.database.SQLDatabase;

public class MealDbSchema {
    public static final class AddMeal{
        public static final String NAME = "Meals";

        public static final class Cols{
            public static final String MealName ="mealname";
            public static final String MealCost = "mealcost";
        }
    }
}
