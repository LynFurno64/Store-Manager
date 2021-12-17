package com.example.storemanager.ui.statistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager.database.SQLDatabase.StatisticsBaseHelper;
import com.example.storemanager.database.SQLDatabase.StatisticsDbSchema;
import com.example.storemanager.database.Statistics;

import java.util.UUID;

public class Statisticlab {
    private static Statisticlab statistic;


    private Context context;
    private SQLiteDatabase database;

    public static Statisticlab get(Context context){
    }


    private Statisticlab(Context context){
        context = context.getApplicationContext();
        database = new StatisticsBaseHelper(context).getWritableDatabase();

    }
    public void AddStat(Statistics s){
        ContentValues values = getContentValues(s);
        database.insert(StatisticsDbSchema.StatisticsTable.Name,null,values);
    }


    public Statisticlab getStatistic(UUID id){
        return null;
    }
    public void updateStat(Statistics statistics){
        String uuidString = statistics.getId().toString();
        ContentValues values = getContentValues(statistics);

        database.update(StatisticsDbSchema.StatisticsTable.Name,values, StatisticsDbSchema.StatisticsTable.Cols.UUID+"= ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Statistics statistics){
        ContentValues values = new ContentValues();
        values.put(StatisticsDbSchema.StatisticsTable.Cols.UUID,statistics.getId().toString());
        values.put(StatisticsDbSchema.StatisticsTable.Cols.TotalEarn, statistics.getEarn());
        values.put(StatisticsDbSchema.StatisticsTable.Cols.TotalSpent, statistics.getSpent());
        values.put(StatisticsDbSchema.StatisticsTable.Cols.Profits, statistics.getProfits());

        return values;
    }
}
