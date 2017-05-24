package com.evoteam.android.dictionary;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by user on 5/15/2017.
 */

public class FavoriteCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public FavoriteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dictionary getFavorite(){
        String name = getString(getColumnIndex(DataBaseSchema.Favorite.culs.F_NAME));
        String translate = getString(getColumnIndex(DataBaseSchema.Favorite.culs.F_TRANSLATE));
        String image = getString(getColumnIndex(DataBaseSchema.Favorite.culs.F_IMAGE));

        Dictionary currentWord = new Dictionary(name, translate, image);
        return currentWord;
    }
}
