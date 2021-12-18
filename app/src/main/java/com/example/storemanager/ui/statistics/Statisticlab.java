package com.example.storemanager.ui.statistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager.database.SQLDatabase.StatisticCursorWrapper;
import com.example.storemanager.database.SQLDatabase.StatisticsBaseHelper;
import com.example.storemanager.database.SQLDatabase.StatisticsDbSchema;
import com.example.storemanager.database.Statistics;

import java.util.ArrayList;
import java.util.List;
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
    public List<Statistics> getStat(){
        List<Statistics> statistics = new ArrayList<>();

        StatisticCursorWrapper cursorWrapper = queryStat(null,null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                statistics.add(cursorWrapper.getStat());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return statistics;
    }
    public void AddStat(Statistics s){
        ContentValues values = getContentValues(s);
        database.insert(StatisticsDbSchema.StatisticsTable.Name,null,values);
    }


    public Statisticlab getStatistic(UUID id){
        StatisticCursorWrapper cursorWrapper = queryStat(StatisticsDbSchema.StatisticsTable.Cols.UUID+" =?",
                new String[]{id.toString()});
        try {
            if (cursorWrapper.getCount() == 0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getStat();
        }finally {
            cursorWrapper.close();
        }
    }
    public void updateStat(Statistics statistics){
        String uuidString = statistics.getId().toString();
        ContentValues values = getContentValues(statistics);

        database.update(StatisticsDbSchema.StatisticsTable.Name,values, StatisticsDbSchema.StatisticsTable.Cols.UUID+"= ?",
                new String[]{uuidString});
    }
    private StatisticCursorWrapper queryStat(String whereClause, String[] whereArgs){
        Cursor cursor = database.query(StatisticsDbSchema.StatisticsTable.Name,null
        ,//columns - null selects all columes
                whereClause,whereArgs,null,//groupBy null,//having null //orderBy);
        );
        return new StatisticCursorWrapper(cursor);
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
