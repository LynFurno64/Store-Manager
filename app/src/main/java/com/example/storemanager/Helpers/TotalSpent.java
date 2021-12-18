package com.example.storemanager.Helpers;

public class TotalSpent{
    public double spentToday;
    public double spendSoFar;

    public double getSpentToday() {
        return spentToday;
    }

    public void setSpentToday(double spentToday) {
        this.spentToday = spentToday;
    }

    public double getSpendSoFar() {
        return spendSoFar;
    }

    public void setSpendSoFar(double spendSoFar) {
        this.spendSoFar = spendSoFar;
    }

    public double calculateOverallTotal(double num) {
        spendSoFar += num;
        return spendSoFar;
    }// calculateOverallTotal

}
