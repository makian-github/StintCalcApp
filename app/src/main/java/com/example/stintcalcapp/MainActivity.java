package com.example.stintcalcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StintData stintData;
    private int maxStintCount;
    private TextView[] startTimeTextView;
    private TextView[] endTimeTextView;
    private TextView[] runTimeTextView;
    private TextView[] driverTimeTextView;
    private TextView perStintTimeTextView;
    private EditText allStintTextEditText;
    private EditText raceTimeEditText;
    private CheckBox[] flagCheckBox;
    private int raceTime;
    private int allStint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    protected void onResume() {
        super.onResume();

        stintData = (StintData) this.getApplication();

        //Maxスティント数
        maxStintCount = stintData.getMaxStintCount();

        //スティントの開始/終了時間のTextViewの定義
        startTimeTextView =  new TextView[maxStintCount];
        endTimeTextView = new TextView[maxStintCount];
        runTimeTextView = new TextView[maxStintCount];
        driverTimeTextView = new TextView[maxStintCount];
        flagCheckBox = new CheckBox[maxStintCount];

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

        driverTimeTextView[0] = findViewById(R.id.driver0);
        driverTimeTextView[1] = findViewById(R.id.driver1);
        driverTimeTextView[2] = findViewById(R.id.driver2);
        driverTimeTextView[3] = findViewById(R.id.driver3);
        driverTimeTextView[4] = findViewById(R.id.driver4);
        driverTimeTextView[5] = findViewById(R.id.driver5);
        driverTimeTextView[6] = findViewById(R.id.driver6);
        driverTimeTextView[7] = findViewById(R.id.driver7);
        driverTimeTextView[8] = findViewById(R.id.driver8);
        driverTimeTextView[9] = findViewById(R.id.driver9);
        driverTimeTextView[10] = findViewById(R.id.driver10);
        driverTimeTextView[11] = findViewById(R.id.driver11);
        driverTimeTextView[12] = findViewById(R.id.driver12);
        driverTimeTextView[13] = findViewById(R.id.driver13);
        driverTimeTextView[14] = findViewById(R.id.driver14);

        flagCheckBox[0] = findViewById(R.id.checkbox0);
        flagCheckBox[1] = findViewById(R.id.checkbox1);
        flagCheckBox[2] = findViewById(R.id.checkbox2);
        flagCheckBox[3] = findViewById(R.id.checkbox3);
        flagCheckBox[4] = findViewById(R.id.checkbox4);
        flagCheckBox[5] = findViewById(R.id.checkbox5);
        flagCheckBox[6] = findViewById(R.id.checkbox6);
        flagCheckBox[7] = findViewById(R.id.checkbox7);
        flagCheckBox[8] = findViewById(R.id.checkbox8);
        flagCheckBox[9] = findViewById(R.id.checkbox9);
        flagCheckBox[10] = findViewById(R.id.checkbox10);
        flagCheckBox[11] = findViewById(R.id.checkbox11);
        flagCheckBox[12] = findViewById(R.id.checkbox12);
        flagCheckBox[13] = findViewById(R.id.checkbox13);
        flagCheckBox[14] = findViewById(R.id.checkbox14);

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
        Button perStintCalcBtn = findViewById(R.id.perStintCalcBtn);
        Button perStintSetBtn = findViewById(R.id.perStintSetBtn);
        Button refreshBtn = findViewById(R.id.refreshBtn);
        Button uniformityBtn = findViewById(R.id.uniformity);

        perStintTimeTextView = findViewById(R.id.perStintText);
        allStintTextEditText = findViewById(R.id.allStintEditText);
        raceTimeEditText = findViewById(R.id.raceTimeEditText);

        raceTime = Integer.parseInt(raceTimeEditText.getText().toString());
        allStint = Integer.parseInt(allStintTextEditText.getText().toString());

        perStintCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perStintTimeCalc();
            }
        });

        perStintSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("123", "onClick: stintData.getStintCnt()" + stintData.getStintCnt());
                Log.d("123", "onClick: maxStintCount" +  maxStintCount);
                if (allStint > 0 && allStint <= maxStintCount) {
                    perStintTimeCalc();
                    if (stintData.getPerStintTime() != 0) {
                        for (int i = 0; i < stintData.getStintCnt(); i++) {
                            stintData.setEndTime(i, calcPlusTime(stintData.getRaceData()[i][1], stintData.getPerStintTime()));
                        }
                    }

                    stintData.getRaceData()[stintData.getStintCnt() - 1][2] = calcPlusTime(stintData.getRaceData()[0][1], raceTime);
                    Log.d("@@", "onClick: stintData.getRaceData()[0][1]" + stintData.getRaceData()[0][1]);
                    Log.d("@@", "onClick: raceTime" + raceTime);
                    Log.d("@@", "onClick: stintData.getRaceData()[stintData.getStintCnt()-1][2]" + stintData.getRaceData()[stintData.getStintCnt() - 1][2]);
                    displayUpdate();
                }
            }
        });

        uniformityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBoxにチェックが入っている最後のStintを検出
                for (int i = maxStintCount-1; i >= 0; i--) {
                    Log.d("TAG", "onClick: flagCheckBox[" + i + "].isChecked() = " + flagCheckBox[i].isChecked());
                    if (flagCheckBox[i].isChecked()){
                        String uniformityStartTime = stintData.getRaceData()[i][1];
                        String uniformityEndTime = stintData.getRaceData()[allStint-1][2];
                        Log.d("TAG", "onClick: uniformityStartTime = " + uniformityStartTime);
                        Log.d("TAG", "onClick: uniformityEndTime = " + uniformityEndTime);
                        uniformitySet(uniformityStartTime,uniformityEndTime,i);
                        break;
                    }
                }
                displayUpdate();
            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUpdate();
            }
        });

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


        displayUpdate();
    }

    /**
     * 引数で渡された時刻の時刻差を計算
     * @param startTime
     * @param endTime
     * @return startTimeとendTimeの時間差(書式は00:00)
     */
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

    /**
     * 00:00(time)から時(Hour)を抽出
     * @param time 00:00
     * @return hour
     */
    private int hourExtraction(String time) {
        int hour;

        String[] times = time.split(":");
        hour = Integer.parseInt(times[0]);

        return hour;
    }

    /**
     * 00:00(time)から分(minutes)を抽出
     * @param time 00:00
     * @return minutes
     */
    private int minutesExtraction(String time) {
        int minutes;

        String[] times = time.split(":");
        minutes = Integer.parseInt(times[1]);

        return minutes;
    }

    /**
     * 引数で渡された時刻(time)に時間(plusTime)を足した時刻を返す
     * @param time 時刻(00:00)
     * @param plusTime 足したい時間
     * @return time + plusTimeの時刻
     */
    private String calcPlusTime(String time , int plusTime) {

        int timeHour = hourExtraction(time);
        int timeMin = minutesExtraction(time);

        //引数で渡された値に1Stint当たりの走行時間を足す
        int endTime = timeHour * 60 + timeMin + plusTime;

        //00:00の書式でreturn
        String returnTime = String.format("%d:%02d", endTime / 60, endTime % 60);
        Log.d("TAG", "calcPlusTime: returnTime = " + returnTime);

        return returnTime;
    }

    /**
     * レース時間(raceTimeEditText)とスティント数(allStintTextEditText)から
     * 1スティントあたりの走行時間を算出
     * 算出結果を"PerStintTimeTextViewへ表示"と"StintDataのperStintTimeへ値をSetする"
     */
    private void perStintTimeCalc(){
        stintData.setStintCnt(allStint);
        stintData.clearRaceData();
        try {
            raceTime = Integer.parseInt(raceTimeEditText.getText().toString());
            allStint = Integer.parseInt(allStintTextEditText.getText().toString());
            int perStint = Math.round(raceTime/allStint);

            perStintTimeTextView.setText(String.valueOf(perStint));
            stintData.setPerStintTime(perStint);
        }catch (Exception e){
            Log.d("Exception", "onClick: " + e);
        }
    }

    private void uniformitySet(String startTime,String endTime,int stint){
        try {
            String time = runTimeCalc(startTime,endTime);
            int time_min = convertTimeToMin(time);
            int remainingStint = allStint - stint;
            Log.d("TAG", "onClick: remainingStint = " + remainingStint);
            int perStint = Math.round(time_min/remainingStint);

            Log.d("TAG", "uniformitySet: perStint" + perStint);

            for (int i = stint; i < allStint; i++) {
                stintData.setEndTime(i, calcPlusTime(stintData.getRaceData()[i][1], perStint));
            }

        }catch (Exception e){
            Log.d("Exception", "onClick: " + e);
        }
    }

    /**
     * 00:00の書式から分に変換
     * @return
     */
    private int convertTimeToMin(String time){
        int minTime = hourExtraction(time)*60 + minutesExtraction(time);
        return minTime;
    }

    /**
     * 表示を更新
     * StintDataから値を取得
     */
    private void displayUpdate(){
        for (int i = 0; i < stintData.getRaceData().length; i++) {
            startTimeTextView[i].setText(stintData.getRaceData()[i][1]);
            endTimeTextView[i].setText(stintData.getRaceData()[i][2]);
            runTimeTextView[i].setText(runTimeCalc(stintData.getRaceData()[i][1],stintData.getRaceData()[i][2]));
            driverTimeTextView[i].setText(stintData.getRaceData()[i][3]);
        }
    }
}