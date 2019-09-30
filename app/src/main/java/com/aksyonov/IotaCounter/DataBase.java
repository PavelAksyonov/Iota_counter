package com.aksyonov.IotaCounter;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "ScoresDb";
   public static final String TABLE_SCORES = "Scores";







    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 3 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_SCORES + "(" +
                ScoresTable.ScoresEntry.KEY_ID + " integer primary key autoincrement, " +
                ScoresTable.ScoresEntry.KEY_PLAYER_NAME + " text, " +
                ScoresTable.ScoresEntry.KEY_RESULT  + " integer, " +
                ScoresTable.ScoresEntry.KEY_DATE + " text, " +
                ScoresTable.ScoresEntry.KEY_QUANTITY_PLAYERS + " integer " + " )"  );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" drop table if exists " + TABLE_SCORES);

        onCreate( db);

    }
}
