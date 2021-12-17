package com.example.storemanager.database.SQLDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.storemanager.database.SQLDatabase.MealDbSchema.MealTable;

public class MealBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME ="mealBase.db";


    public MealBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MealTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                MealTable.Cols.UUID + ", " +
                MealTable.Cols.MealName + ", " +
                MealTable.Cols.MealCost + ", " +
                MealTable.Cols.TimesOrder +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
