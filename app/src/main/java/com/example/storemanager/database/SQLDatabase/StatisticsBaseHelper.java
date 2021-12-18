package com.example.storemanager.database.SQLDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.storemanager.database.SQLDatabase.StatisticsDbSchema.StatisticsTable;

public class StatisticsBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "WebandMobileProject.db";

    public StatisticsBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table"+ StatisticsTable.Name +"(" + "_id integer primary key" +
                "autoincrement,"+ StatisticsTable.Cols.UUID+","+
                StatisticsTable.Cols.TotalEarn+","+
                StatisticsTable.Cols.TotalSpent+","+
                StatisticsTable.Cols.Profits+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
