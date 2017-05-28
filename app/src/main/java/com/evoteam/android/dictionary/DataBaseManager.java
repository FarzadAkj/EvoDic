package com.evoteam.android.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 5/15/2017.
 */

public class DataBaseManager {

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public static ArrayList<Dictionary> historyList;
    public static ArrayList<Dictionary> favoriteList;

    public DataBaseManager(Context context){
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new DataBaseHelper(mContext).getWritableDatabase();
    }

    public void addFavorite(Dictionary word){
        ContentValues value = getFavoriteWordValues(word);
        FavoriteCursorWrapper cursor = queryFavorite();

        cursor.moveToFirst();
        boolean isInDB = false ;
        while(! cursor.isAfterLast()){
            if (cursor.getFavorite().getWord().equalsIgnoreCase(word.getWord()))
                isInDB = true ;
            cursor.moveToNext();
        }
        if (isInDB == false)
            mSQLiteDatabase.insert(DataBaseSchema.Favorite.NAME, null, value);
    }

    private static ContentValues getFavoriteWordValues(Dictionary word){
        ContentValues values = new ContentValues();

        values.put(DataBaseSchema.Favorite.culs.F_NAME, word.getWord());
        values.put(DataBaseSchema.Favorite.culs.F_TRANSLATE, word.getTranslate());
        values.put(DataBaseSchema.Favorite.culs.F_IMAGE, word.getPicture());

        return values;
    }

    public void addHistory(Dictionary word){
        ContentValues value = getHistoryWordValues(word);
        HistoryCursorWrapper cursor = queryHistory();

        cursor.moveToFirst();
        boolean isInDB = false ;
        while(! cursor.isAfterLast()){
            if ( cursor.getHistory().getWord().equalsIgnoreCase(word.getWord()))
                isInDB = true ;
            cursor.moveToNext();
        }
        if ( isInDB == false)
            mSQLiteDatabase.insert(DataBaseSchema.History.NAME, null, value);
    }
    private static ContentValues getHistoryWordValues(Dictionary word){
        ContentValues values = new ContentValues();

        values.put(DataBaseSchema.History.culs.H_NAME, word.getWord());
        values.put(DataBaseSchema.History.culs.H_TRANSLATE, word.getTranslate());
        values.put(DataBaseSchema.History.culs.H_IMAGE, word.getPicture());

        return values;
    }

    public ArrayList getFavorite(){
        favoriteList = new ArrayList<>();
        FavoriteCursorWrapper cursor = queryFavorite();

        cursor.moveToFirst();

        while(! cursor.isAfterLast()){
            Dictionary temp = cursor.getFavorite();
            if(favoriteList.contains(temp) == false){
                favoriteList.add(temp);
            }
            cursor.moveToNext();
        }

        return favoriteList;
    }

    private FavoriteCursorWrapper queryFavorite() {
        Cursor cursor = mSQLiteDatabase.query(DataBaseSchema.Favorite.NAME
        , null, null, null, null, null, null);

        return new FavoriteCursorWrapper(cursor);
    }

    public ArrayList getHistory(){
        historyList = new ArrayList<>();
        HistoryCursorWrapper cursor = queryHistory();

        cursor.moveToFirst();

        while(! cursor.isAfterLast()){
            Dictionary temp = cursor.getHistory();
            if(historyList.contains(temp) == false){
                historyList.add(temp);
            }
            cursor.moveToNext();
        }

        return historyList;
    }

    private HistoryCursorWrapper queryHistory() {
        Cursor cursor = mSQLiteDatabase.query(DataBaseSchema.History.NAME
                , null, null, null, null, null, null);

        return new HistoryCursorWrapper(cursor);
    }

    public void deleteFavorite(Dictionary word){
        mSQLiteDatabase.delete(DataBaseSchema.Favorite.NAME, DataBaseSchema.Favorite.culs.F_NAME + "=" + "'" + word.getWord() + "'"
                + "and " + DataBaseSchema.Favorite.culs.F_TRANSLATE + "=" + "'" + word.getTranslate() + "'", null);
    }

    public void deleteHistory(Dictionary word){
        mSQLiteDatabase.delete(DataBaseSchema.History.NAME, DataBaseSchema.History.culs.H_NAME + "=" + "'" + word.getWord() + "'"
                + "and " + DataBaseSchema.History.culs.H_TRANSLATE + "=" + "'" + word.getTranslate() + "'", null);
    }
}
