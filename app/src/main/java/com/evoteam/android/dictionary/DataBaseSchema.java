package com.evoteam.android.dictionary;

/**
 * Created by user on 5/15/2017.
 */

public class DataBaseSchema {

    //first table
    public static final class Favorite{
        //table's name
        public static final String NAME = "favorite_table";

        //defining each column
        public static final class culs{
            public static final String F_NAME       = "name"       ;
            public static final String F_TRANSLATE  = "translate"  ;
            public static final String F_IMAGE      = "image"      ;
        }
    }

    //second table
    public static final class History{
        //table's name
        public static final String NAME = "history_table";

        //defining each column
        public static final class culs{
            public static final String H_NAME       = "name"       ;
            public static final String H_TRANSLATE  = "translate"  ;
            public static final String H_IMAGE      = "image"      ;
        }
    }
}
