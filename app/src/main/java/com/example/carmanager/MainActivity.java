package com.example.carmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView avto_label;
    TextView avto_data;
    TextView avto_engine;
    TextView avto_number;
    TextView avto_vin;
    TextView avto_trans;
    String AVTO="";
    boolean flag=false;
    private final static String FILE = "avto.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avto_label = findViewById(R.id.label);
        avto_data = findViewById(R.id.year);
        avto_engine = findViewById(R.id.engine);
        avto_number = findViewById(R.id.number);
        avto_vin = findViewById(R.id.vin);
        avto_trans = findViewById(R.id.transmission);
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            AVTO=text;

        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        char[] avto = AVTO.toCharArray();
        String info="";
        int m=0;
        for(int i=0;i<AVTO.length();i++)
        {
            if(avto[i]!='\n')
                info+=avto[i];
            else
            {
                if(m==0)
                    avto_label.setText(info);
                if(m==1)
                    avto_data.setText(info);
                if(m==2)
                    avto_number.setText(info);
                if(m==3)
                    avto_vin.setText(info);
                if(m==4)
                    avto_engine.setText(info);
                if(m==5)
                    avto_trans.setText(info);
                info="";
                m++;
            }
        }
        flag = AVTO.isEmpty();
        Toast first = Toast.makeText
                (this,
                        "Привет, введи данные своей машины и затем перезагрузи пиложение",
                        Toast.LENGTH_LONG);
        if(flag)
        {
            Intent intent = new Intent(this, enter.class);
            startActivity(intent);
            first.show();
        }
    }

    public void fuel (View view)
    {
        Intent intent = new Intent(this, Fuel.class);
        startActivity(intent);
        Toast hint = Toast.makeText
                (this, "Заполните информацию о последней заправке",Toast.LENGTH_LONG);
        hint.show();

    }
    public void fine (View view)
    {
        Intent intent = new Intent(this, Fine.class);
        startActivity(intent);
        Toast hint = Toast.makeText
                (this, "Заполните информацию о штрафах",Toast.LENGTH_LONG);
        hint.show();
    }
    public void repair (View view)
    {
        Intent intent = new Intent(this, Service.class);
        startActivity(intent);
        Toast hint = Toast.makeText
                (this, "Заполните информацию о последнем ремонте",Toast.LENGTH_LONG);
        hint.show();
    }
    public void stat (View view)
    {
        Intent intent = new Intent(this, Static.class);
        startActivity(intent);
    }
}
