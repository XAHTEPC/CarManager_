package com.example.carmanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carmanager.data.Contract;
import com.example.carmanager.data.DBHelper;

public class Fine extends AppCompatActivity {

    EditText price;
    EditText date;
    Spinner type;
    String type_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        price = findViewById(R.id.price);
        date = findViewById(R.id.date);
        type = findViewById(R.id.spinner_fine);
        setupSpinner();
    }
    boolean check(String s)
    {
        char[] a = s.toCharArray();
        int k=0,r=0;
        for(int i=0;i<s.length();i++)
        {
            if(a[i]=='.'||a[i]==',')
                k++;
            if((a[i]>='0'&&a[i]<='9'))
                r++;

        }
        int d=0,m=0,y=0;
        if(s.length()==8) {
            d = Integer.parseInt(String.valueOf(a[0])) * 10 + Integer.parseInt(String.valueOf(a[1]));
            m = Integer.parseInt(String.valueOf(a[3])) * 10 + Integer.parseInt(String.valueOf(a[4]));
            y = Integer.parseInt(String.valueOf(a[6])) * 10 + Integer.parseInt(String.valueOf(a[7]));
            return (a[2]=='.'||a[2]==',')&&a[2]==a[5]&&s.length()==8&&d>0&&d<32&&m>0&&m<13&&y>=0&&y<100&&k+r==s.length()&&k==2;
        }
        else
            return false;

    }
    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_fine_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        type.setAdapter(genderSpinnerAdapter);
        type.setSelection(2);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.t1))) {
                        type_ = "Проезд на запрещающий сигнал светофора";
                    } else if (selection.equals(getString(R.string.t2))) {
                        type_ = "Нарушение разметки";
                    } else if (selection.equals(getString(R.string.t4))) {
                        type_ = "Превышение скорости";
                    } else
                        type_ = "Другое";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type_ = "Другое";
            }
        });
    }
    public void save(View v)
    {
        Toast end = Toast.makeText(this,"Данные сохранены", Toast.LENGTH_LONG);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String price_ = price.getText().toString();
        String date_ = date.getText().toString();
        if(type_.isEmpty()||price_.isEmpty()||date_.isEmpty())
        {
            Toast er = Toast.makeText(this,"Заполните все строки", Toast.LENGTH_LONG);
            er.show();
        }
        else if(!check(date_))
        {
            Toast er = Toast.makeText(this,"Проверьте правильность даты", Toast.LENGTH_SHORT);
            er.show();
        }
        else
        {
            int sum = Integer.parseInt(price_);
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract.DataEntry.COLUMN_TYPE, "Штраф");
            contentValues.put(Contract.DataEntry.COLUMN_TYPE_, "");
            contentValues.put(Contract.DataEntry.COLUMN_CONTENT, type_);
            contentValues.put(Contract.DataEntry.COLUMN_PRICE, sum);
            contentValues.put(Contract.DataEntry.COLUMN_DATE, date_);
            database.insert(Contract.DataEntry.TABLE_NAME, null, contentValues);
            end.show();
        }
    }
}
