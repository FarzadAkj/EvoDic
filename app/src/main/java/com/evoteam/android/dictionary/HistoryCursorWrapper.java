package com.evoteam.android.dictionary;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by user on 5/15/2017.
 */

public class HistoryCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public HistoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dictionary getHistory(){
        String name = getString(getColumnIndex(DataBaseSchema.History.culs.H_NAME));
        String translate = getString(getColumnIndex(DataBaseSchema.History.culs.H_TRANSLATE));
        String image = getString(getColumnIndex(DataBaseSchema.History.culs.H_IMAGE));

        Dictionary currentWord = new Dictionary(name, translate, image);
        return currentWord;
    }
}
