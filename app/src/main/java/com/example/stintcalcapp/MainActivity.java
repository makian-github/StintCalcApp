package com.example.stintcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StintData stintData;
    private int allStintCount;
    private TextView[] startTimeTextView;
    private TextView[] endTimeTextView;
    private TextView[] runTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stintData = (StintData) this.getApplication();

        //全スティント数
        allStintCount = 15;

        //スティントの開始/終了時間のTextViewの定義
        startTimeTextView =  new TextView[allStintCount];
        endTimeTextView = new TextView[allStintCount];
        runTimeTextView = new TextView[allStintCount];

        //idの紐づけ
        startTimeTextView[0] = findViewById(R.id.startTime0);
        startTimeTextView[1] = findViewById(R.id.startTime1);
        startTimeTextView[2] = findViewById(R.id.startTime2);
        startTimeTextView[3] = findViewById(R.id.startTime3);
        startTimeTextView[4] = findViewById(R.id.startTime4);
        startTimeTextView[5] = findViewById(R.id.startTime5);
        startTimeTextView[6] = findViewById(R.id.startTime6);
        startTimeTextView[7] = findViewById(R.id.startTime7);
        startTimeTextView[8] = findViewById(R.id.startTime8);
        startTimeTextView[9] = findViewById(R.id.startTime9);
        startTimeTextView[10] = findViewById(R.id.startTime10);
        startTimeTextView[11] = findViewById(R.id.startTime11);
        startTimeTextView[12] = findViewById(R.id.startTime12);
        startTimeTextView[13] = findViewById(R.id.startTime13);
        startTimeTextView[14] = findViewById(R.id.startTime14);

        endTimeTextView[0] = findViewById(R.id.endTime0);
        endTimeTextView[1] = findViewById(R.id.endTime1);
        endTimeTextView[2] = findViewById(R.id.endTime2);
        endTimeTextView[3] = findViewById(R.id.endTime3);
        endTimeTextView[4] = findViewById(R.id.endTime4);
        endTimeTextView[5] = findViewById(R.id.endTime5);
        endTimeTextView[6] = findViewById(R.id.endTime6);
        endTimeTextView[7] = findViewById(R.id.endTime7);
        endTimeTextView[8] = findViewById(R.id.endTime8);
        endTimeTextView[9] = findViewById(R.id.endTime9);
        endTimeTextView[10] = findViewById(R.id.endTime10);
        endTimeTextView[11] = findViewById(R.id.endTime11);
        endTimeTextView[12] = findViewById(R.id.endTime12);
        endTimeTextView[13] = findViewById(R.id.endTime13);
        endTimeTextView[14] = findViewById(R.id.endTime14);

        runTimeTextView[0] = findViewById(R.id.runTime0);
        runTimeTextView[1] = findViewById(R.id.runTime1);
        runTimeTextView[2] = findViewById(R.id.runTime2);
        runTimeTextView[3] = findViewById(R.id.runTime3);
        runTimeTextView[4] = findViewById(R.id.runTime4);
        runTimeTextView[5] = findViewById(R.id.runTime5);
        runTimeTextView[6] = findViewById(R.id.runTime6);
        runTimeTextView[7] = findViewById(R.id.runTime7);
        runTimeTextView[8] = findViewById(R.id.runTime8);
        runTimeTextView[9] = findViewById(R.id.runTime9);
        runTimeTextView[10] = findViewById(R.id.runTime10);
        runTimeTextView[11] = findViewById(R.id.runTime11);
        runTimeTextView[12] = findViewById(R.id.runTime12);
        runTimeTextView[13] = findViewById(R.id.runTime13);
        runTimeTextView[14] = findViewById(R.id.runTime14);

        Button setButton0 = findViewById(R.id.setButton0);
        Button setButton1 = findViewById(R.id.setButton1);
        Button setButton2 = findViewById(R.id.setButton2);
        Button setButton3 = findViewById(R.id.setButton3);
        Button setButton4 = findViewById(R.id.setButton4);
        Button setButton5 = findViewById(R.id.setButton5);
        Button setButton6 = findViewById(R.id.setButton6);
        Button setButton7 = findViewById(R.id.setButton7);
        Button setButton8 = findViewById(R.id.setButton8);
        Button setButton9 = findViewById(R.id.setButton9);
        Button setButton10 = findViewById(R.id.setButton10);
        Button setButton11 = findViewById(R.id.setButton11);
        Button setButton12 = findViewById(R.id.setButton12);
        Button setButton13 = findViewById(R.id.setButton13);
        Button setButton14 = findViewById(R.id.setButton14);


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

        setButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 2);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 3);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 4);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 5);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 6);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 7);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 8);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 9);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 10);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 11);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 12);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 13);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        setButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 14);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        for (int i = 0; i < stintData.getRaceData().length; i++) {
            startTimeTextView[i].setText(stintData.getRaceData()[i][1]);
            endTimeTextView[i].setText(stintData.getRaceData()[i][2]);
            runTimeTextView[i].setText(runTimeCalc(stintData.getRaceData()[i][1],stintData.getRaceData()[i][2]));
        }
    }

    //引数で渡された時刻の時刻差を計算
    private String runTimeCalc(String startTime,String endTime){
        String runtime;

        Log.d("TAG", "runTimeCalc: start=" + startTime);
        Log.d("TAG", "runTimeCalc: start=" + endTime);

        int startHour = hourExtraction(startTime);
        int startMin = minutesExtraction(startTime);
        int endHour = hourExtraction(endTime);
        int endMin = minutesExtraction(endTime);

        int start = startHour*60 + startMin;
        int end = endHour*60 + endMin;
        int runtimeInt;

        Log.d("TAG", "runTimeCalc: start=" + start);
        Log.d("TAG", "runTimeCalc: end=" + end);

        if (start <= end){
            runtimeInt = end - start;
        }else{
            runtimeInt = end + 24*60 -start;
        }

        //00:00の書式でreturn
        runtime = String.format("%d:%02d",runtimeInt/60,runtimeInt%60);

        return runtime;
    }

    //00:00から時(Hour)を抽出
    private int hourExtraction(String time) {
        int hour;

        String[] times = time.split(":");
        hour = Integer.parseInt(times[0]);

        return hour;
    }

    //00:00から分(minutes)を抽出
    private int minutesExtraction(String time) {
        int minutes;

        String[] times = time.split(":");
        minutes = Integer.parseInt(times[1]);

        return minutes;
    }

}