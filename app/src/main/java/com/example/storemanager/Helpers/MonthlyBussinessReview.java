package com.example.storemanager.Helpers;

import com.example.storemanager.Helpers.TotalSpent;
import com.example.storemanager.database.Dish;

public class MonthlyBussinessReview  {
    public Dish earned;
    public TotalSpent spent;
    public double totalEarned;
    public double totalLoss;


    public MonthlyBussinessReview(Dish earned,TotalSpent spent, double totalEarned,double totalLoss){
        this.earned = earned;
        this.spent = spent;
        this.totalEarned =  totalEarned;
        this.totalLoss = totalLoss;

    }

    public MonthlyBussinessReview() {

    }

    public double calculateProfits(double earn, double spent){
        double gain = 0;
        if (earn> spent) {
            double ans = earn - spent;
            double result = ans / spent;
            gain = result * 100;
        }
        return gain;

    }
    public double calculateLoss(double earn, double spent){
        double loss = 0;
        if (spent>earn) {
            double ans = spent - earn;
            double result = ans / earn;
            loss = result * 100;
        }
        return loss;
    }
    public String findBestDish()
    {

        return null;
    }
    public Dish getEarned() {
        return earned;
    }

    public double Totalearned(double earn, double spent) {
        double totalearned = 0;
        if (earn> spent) {
            totalearned = earn - spent;
        }
        return totalearned;
    }
    public double Totalloss(double earn, double spent) {
        double totalloss = 0;
        if (spent>earn) {
            totalloss = spent - earn;
        }
        return totalloss;
    }

    public void setTotalEarned(double totalEarned) {
        this.totalEarned = totalEarned;
    }

    public double getTotalEarned() {
        return totalEarned;
    }
}