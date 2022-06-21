package com.example.carmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DATA.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Contract.DataEntry.TABLE_NAME + " ("
                + Contract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.DataEntry.COLUMN_TYPE + " TEXT NOT NULL, "
                + Contract.DataEntry.COLUMN_TYPE_ + " TEXT NOT NULL, "
                + Contract.DataEntry.COLUMN_CONTENT + " TEXT NOT NULL, "
                + Contract.DataEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + Contract.DataEntry.COLUMN_DATE + " TEXT NOT NULL " + ")" );
        Log.w("SQLite", "Создана БД");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS  EquipmentEntry.TABLE_NAME");
        onCreate(db);

    }
}
