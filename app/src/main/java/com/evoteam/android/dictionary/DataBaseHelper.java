package com.evoteam.android.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 5/15/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    final static int VERSION = 1;
    final static String DATABASE_NAME = "dic.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DataBaseSchema.Favorite.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DataBaseSchema.Favorite.culs.F_NAME + ", " +
                DataBaseSchema.Favorite.culs.F_TRANSLATE + ", " +
                DataBaseSchema.Favorite.culs.F_IMAGE +
                ")"
        );

        db.execSQL("create table " + DataBaseSchema.History.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DataBaseSchema.History.culs.H_NAME + ", " +
                DataBaseSchema.History.culs.H_TRANSLATE + ", " +
                DataBaseSchema.History.culs.H_IMAGE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do here yet
    }
}
