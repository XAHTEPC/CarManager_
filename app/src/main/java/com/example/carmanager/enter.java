package com.example.carmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class enter extends AppCompatActivity {

    EditText avto_label;
    EditText avto_data;
    EditText avto_engine;
    EditText avto_number;
    EditText avto_vin;
    EditText avto_trans;
    private final static String FILE = "avto.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        avto_label = findViewById(R.id.label);
        avto_data = findViewById(R.id.year);
        avto_engine = findViewById(R.id.engine);
        avto_number = findViewById(R.id.number);
        avto_vin = findViewById(R.id.vin);
        avto_trans = findViewById(R.id.transmission);
    }

    public void save (View view)
    {
            String label = avto_label.getText().toString();
            String data = avto_data.getText().toString();
            String engine = avto_engine.getText().toString();
            String number = avto_number.getText().toString();
            String vin = avto_vin.getText().toString();
            String trans = avto_trans.getText().toString();
            FileOutputStream fos = null;
        String AVTO="";
            if(!label.isEmpty()&&!data.isEmpty()&&!engine.isEmpty()&&!number.isEmpty()&&!vin.isEmpty()&&!trans.isEmpty())
             AVTO = label + "\n" +
                    data + "\n" +
                    number + "\n" +
                    vin + "\n" +
                    engine + "\n" +
                    trans + "\n";
        try {
            fos = openFileOutput(FILE, MODE_PRIVATE);
            fos.write(AVTO.getBytes());
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        Intent r = new Intent(this, MainActivity.class);
        startActivity(r);
    }
}
