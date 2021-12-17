package com.example.storemanager.database;

/**
 *
 * @author chesh
 */
public class FinanceForMonths {
    public int month;
    public String moneyMade;
    public String moneySpent;
    
    public FinanceForMonths(int month, String moneyMade, String moneySpent) {
        this.month =  month;
        this.moneyMade = moneyMade;
        this.moneySpent = moneySpent;
    }

    public int getMonth() {
        return month;
    }

    public String getMoneyMade() {
        return moneyMade;
    }

    public String getMoneySpent() {
        return moneySpent;
    }
}
