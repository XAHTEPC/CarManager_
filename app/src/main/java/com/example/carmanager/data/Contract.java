package com.example.carmanager.data;

import android.provider.BaseColumns;

public final class Contract {
    private  Contract()
    {
    };
    public static final class DataEntry implements BaseColumns {
        public final static String TABLE_NAME = "DATA";

        public final static String _ID = "_id";
        public final static String COLUMN_TYPE = "type";
        public final static String COLUMN_TYPE_ = "type_";
        public final static String COLUMN_CONTENT = "content";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_DATE = "date";
    }
}
