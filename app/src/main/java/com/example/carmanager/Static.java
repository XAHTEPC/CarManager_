package com.example.carmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.carmanager.data.Contract;

import com.example.carmanager.data.DBHelper;

import java.util.Calendar;

public class Static extends AppCompatActivity {

    TextView res;
    DBHelper dbHelper;
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        res = findViewById(R.id.stat);
        res.setText("hi");
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] info = {
                Contract.DataEntry.COLUMN_PRICE,
                Contract.DataEntry.COLUMN_DATE
        };
        Cursor cursor = db.query(
                Contract.DataEntry.TABLE_NAME,   // таблица
                info,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки
        int sum = 0;
        get_date();
        try {

            int priceColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_PRICE);
            int dateColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_DATE);
            while (cursor.moveToNext()) {
                int currentprice = cursor.getInt(priceColumnIndex);
                String currentdate = cursor.getString(dateColumnIndex);
                if(check(currentdate))
                {
                    sum += currentprice;
                }
                //Log.e("Sum", String.valueOf(sum));
                res.setText(String.valueOf(sum));
            }


        } finally {
            cursor.close();
        }



    }
    public void get_date()
    {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String d = String.valueOf(month+1)+String.valueOf(year);
        Log.e("m",d);
        String e="";
        if(d.length()==5)
            d="0"+d;
        char[] d_ = d.toCharArray();
        time += String.valueOf(d_[0])+String.valueOf(d_[1])+'.'+String.valueOf(d_[4])+String.valueOf(d_[5]);
        char[] D = e.toCharArray();
        //Log.e("Time", d + " "+ e);
    }
    boolean check(String str)
    {
        char[] str_ = str.toCharArray();
        String STR ="";
        STR += String.valueOf(str_[3])+String.valueOf(str_[4])+String.valueOf(str_[5])+String.valueOf(str_[6])+String.valueOf(str_[7]);
        Log.e("Debag", STR +"///"+time);
        return (STR.equals(time));////
    }
    public void look (View v)
    {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                Contract.DataEntry._ID,
                Contract.DataEntry.COLUMN_TYPE,
                Contract.DataEntry.COLUMN_TYPE_,
                Contract.DataEntry.COLUMN_CONTENT,
                Contract.DataEntry.COLUMN_PRICE,
                Contract.DataEntry.COLUMN_DATE
        };

        // Делаем запрос
        Cursor cursor = db.query(
                Contract.DataEntry.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        TextView displayTextView = findViewById(R.id.text_view_info);
        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " позиций.\n\n");
            displayTextView.append("№ - Категория - Тип - Подтип - Цена - Дата\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(Contract.DataEntry._ID);
            int typeColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_TYPE);
            int type_ColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_TYPE_);
            int contentColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_CONTENT);
            int priceColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_PRICE);
            int dateColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_DATE);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currenttype = cursor.getString(typeColumnIndex);
                String currenttype_ = cursor.getString(type_ColumnIndex);
                String currentcontent = cursor.getString(contentColumnIndex);
                int currentprice = cursor.getInt(priceColumnIndex);
                String currentdate = cursor.getString(dateColumnIndex);
                displayTextView.append(("\n" + currentID + " - " +
                        currenttype + " - " +
                        currenttype_ + " - " +
                        currentcontent + " - " +
                        currentprice  + " - " +
                        currentdate ));
            }
        } finally {
            cursor.close();
        }
    }
}

