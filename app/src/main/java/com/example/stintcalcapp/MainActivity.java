package com.example.stintcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StintData stintData;
    private int allStintCount;
    private MyApp myApp;
    private TextView[] startTime;
    private TextView[] endTime;
//    private TextView startTime1;
//    private TextView endTime1;
    int driverNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stintData = (StintData) this.getApplication();

        //全スティント数
        allStintCount = 2;

        //スティントの開始/終了時間のTextViewの定義
        startTime =  new TextView[allStintCount];
        endTime = new TextView[allStintCount];

        startTime[0] = findViewById(R.id.startTime0);
        startTime[1] = findViewById(R.id.startTime1);
        endTime[0] = findViewById(R.id.endTime0);
        endTime[1] = findViewById(R.id.endTime1);

        Button setButton0 = findViewById(R.id.setButton0);
        Button setButton1 = findViewById(R.id.setButton1);

        setButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 0);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 1);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (int i = 0; i < stintData.getRaceData().length; i++) {
            startTime[i].setText(stintData.getRaceData()[i][1]);
            endTime[i].setText(stintData.getRaceData()[i][2]);
        }
    }
}