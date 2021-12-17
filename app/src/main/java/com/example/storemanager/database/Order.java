package com.example.storemanager.database;

public class Order implements calculate{
    public double totalMealPrice = 0.0;

    @Override
    public double calculateOverallTotal(double num) {
        totalMealPrice += num;
        return totalMealPrice;
    }// calculateTotalMealPrice
    
    public double substractMealTotal(double num) {
        totalMealPrice -= num;
        return totalMealPrice;
    }// substractMealTotal
    
    public double calculateChange(double pay) {
        double change = pay - totalMealPrice;
        return change;
    }// calculateChange
    
}
