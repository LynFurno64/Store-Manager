package com.example.storemanager.database.SQLDatabase;

public class StatisticsDbSchema {
    public static final class StatisticsTable{
        public static final String Name = "statistics";

        public static final class Cols{
            public static final String UUID = "month";
            public static final String TotalEarn = "earn";
            public static final String TotalSpent = "spent";
            public static final String Profits = "profits";
        }
    }
}
