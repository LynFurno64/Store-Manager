package com.example.storemanager.database;

import java.util.UUID;

public class Statistics {
    private UUID id;
    public double earn;
    public double spent;
    public double profits;

    public Statistics(){
        this(UUID.randomUUID());
    }
    public Statistics(UUID code){
        id = code;
        profits =0;
    }

    public UUID getId() {
        return id;
    }

    public double getEarn() {
        return earn;
    }

    public double getProfits() {
        return profits;
    }

    public double getSpent() {
        return spent;
    }
    public void setEarn(double earn) {
        this.earn = earn;
    }
    public void setProfits(double profits) {
        this.profits = profits;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }
}
