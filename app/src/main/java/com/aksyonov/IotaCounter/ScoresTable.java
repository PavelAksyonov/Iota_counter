package com.aksyonov.IotaCounter;

import android.provider.BaseColumns;

public class ScoresTable {

    ScoresTable (){};

    public static final class ScoresEntry implements BaseColumns {
        public static final String KEY_ID = "_id";
        public static final String KEY_PLAYER_NAME = "Name";
        public static final String KEY_RESULT = "Result";
        public static final String KEY_DATE = "Date";
        public static final String KEY_QUANTITY_PLAYERS = "Quantity";
    }

}

