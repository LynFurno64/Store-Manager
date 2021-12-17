package com.example.storemanager.database;

public class MonthS {
    public int num;
    public String monthS;
    public String moneySpent;

    public MonthS(int num, String monthS, String moneySpent) {
        this.num =  num;
        this.monthS = monthS;
        this.moneySpent = moneySpent;
    }

    public String getMonthS() {
        return monthS;
    }

    public String getMoneySpent() {
        return moneySpent;
    }
}
