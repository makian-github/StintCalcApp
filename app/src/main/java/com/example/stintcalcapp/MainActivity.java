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
    private TextView startTime0;
    private TextView endTime0;
    private TextView startTime1;
    private TextView endTime1;
    int driverNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stintData = (StintData) this.getApplication();
        //stintData.setDriverName("def");

        startTime0 = findViewById(R.id.startTime0);
        endTime0 = findViewById(R.id.endTime0);
        startTime1 = findViewById(R.id.startTime1);
        endTime1 = findViewById(R.id.endTime1);

        Button setButton1 = findViewById(R.id.setButton0);
        setButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 0);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime0.setText(stintData.getRaceData()[0][1]);
        endTime0.setText(stintData.getRaceData()[0][2]);
    }
}