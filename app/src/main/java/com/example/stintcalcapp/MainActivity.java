package com.example.stintcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StintData stintData;
    private MyApp myApp;
    private TextView driverName1;
    int driverNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stintData = (StintData) this.getApplication();
        //stintData.setDriverName("def");

        driverName1 = findViewById(R.id.driverName1);

        Button setButton1 = findViewById(R.id.setButton);
        setButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
            driverName1.setText(stintData.getDriverName());
    }
}