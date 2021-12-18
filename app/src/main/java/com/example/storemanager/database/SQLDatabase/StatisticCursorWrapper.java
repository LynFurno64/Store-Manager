package com.example.storemanager.database.SQLDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.storemanager.database.Statistics;

import java.util.UUID;


public class StatisticCursorWrapper extends CursorWrapper{
    public StatisticCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Statistics getStat(){
        String uuidString = getString(getColumnIndex(StatisticsDbSchema.StatisticsTable.Cols.UUID));
        double earn = getDouble(getColumnIndex(StatisticsDbSchema.StatisticsTable.Cols.TotalEarn));
        double spent = getDouble(getColumnIndex(StatisticsDbSchema.StatisticsTable.Cols.TotalSpent));
        double profits = getDouble(getColumnIndex(StatisticsDbSchema.StatisticsTable.Cols.Profits));

        Statistics statistics = new Statistics(UUID.fromString(uuidString));
        statistics.setEarn(earn);
        statistics.setSpent(spent);
        statistics.setProfits(profits);

        return statistics;
    }
}
