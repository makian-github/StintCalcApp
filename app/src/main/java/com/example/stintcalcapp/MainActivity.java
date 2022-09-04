package com.example.stintcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private StintData stintData;
    private int maxStintCount;
    private TextView[] startTimeTextView;
    private TextView[] endTimeTextView;
    private TextView[] runTimeTextView;
    private TextView[] driverTimeTextView;
    private TextView[] kartNoTextView;
    private TextView perStintTimeTextView;
    private EditText allStintTextEditText;
    private EditText raceTimeEditText;
    private EditText setMinEditText;
    private CheckBox[] flagCheckBox;
    private int raceTime;
    private int allStint;
    private int setRunMin;
    private boolean[] flagCheckBoxes;
    private int[] runTime;
    private TextView[] runSumTimeTextView;
    private TextView maxRunTimeTextView;
    private TextView[] stintCntTextView;
    private String[] driverItem;

    private int pauseCnt = 0;

    private TimeCalc timeCalc;

    private ToggleButton endTimeFixedToggle;

    //ファイル出力用
    private File file;
    private File startTimeFile;
    private File endTimeFile;
    private File runTimeFile;
    private File driverTimeFile;
    private TextView statusText;

    //ドライバーID
    private final int ID_AKIMA = 0;
    private final int ID_TOYOGUCHI = 1;
    private final int ID_YOSHIKAI = 2;
    private final int ID_LUKE = 3;
    private final int ID_YOKOTA = 4;
    private final int ID_TUBOI = 5;
    private final int ID_NITTA = 6;
    private final int ID_X = 7;
    private final int ID_PAUSE = 98;
    private final int ID_NULL = 99;
    private int driverID = 99;

    //120%ルール用の係数
    private final double COEF = 1.2;

    private SharedPreferences dataStore;
    private SharedPreferences.Editor editor;

    private String[] driverList;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();

        //ファイル出力用のファイルを新規作成
        Context context = getApplicationContext();

        String otherInfoFileName = "otherInfoFile.csv";
        String startTimeFileName = "startTimeFile.csv";
        String endTimeFileName = "endTimeFile.csv";
        String runTimeFileName = "runTimeFile.csv";
        String driverTimeFileName = "driverTimeFile.csv";


        file = new File(context.getFilesDir(), otherInfoFileName);
        startTimeFile = new File(context.getFilesDir(), startTimeFileName);
        endTimeFile = new File(context.getFilesDir(), endTimeFileName);
        runTimeFile = new File(context.getFilesDir(), runTimeFileName);
        driverTimeFile = new File(context.getFilesDir(), driverTimeFileName);

        driverList = res.getStringArray(R.array.driverList);

        // "DataStore"という名前でインスタンスを生成
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        editor = dataStore.edit();

        driverItem = new String[10];


    }

    @Override
    protected void onResume() {
        super.onResume();

        stintData = (StintData) this.getApplication();
        timeCalc = new TimeCalc();

        //Maxスティント数
        maxStintCount = stintData.getMaxStintCount();

        //スティントの開始/終了時間のTextViewの定義
        startTimeTextView =  new TextView[maxStintCount];
        endTimeTextView = new TextView[maxStintCount];
        runTimeTextView = new TextView[maxStintCount];
        driverTimeTextView = new TextView[maxStintCount];
        kartNoTextView =  new TextView[maxStintCount];
        flagCheckBox = new CheckBox[maxStintCount];

        //各Stintの走行時間の定義
        runTime = new int[maxStintCount];

        flagCheckBoxes = new boolean[maxStintCount];

        runSumTimeTextView = new TextView[stintData.getDriverCnt()+1];

        stintCntTextView = new TextView[10];

        driverItem = new String[10];
        driverItem[0] = "秋間";
        driverItem[1] = "豊口";
        driverItem[2] = "吉戒";
        driverItem[3] = "ルーク";
        driverItem[4] = "横田";
        driverItem[5] = "坪井";
        driverItem[6] = "新田";
        driverItem[7] = "X";
        driverItem[8] = "未設定";
        driverItem[9] = "中断";

        defineId();

        Button setButton0 = findViewById(R.id.creatButton);

        Button perStintCalcBtn = findViewById(R.id.perStintCalcBtn);
        Button perStintSetBtn = findViewById(R.id.perStintSetBtn);
        Button refreshBtn = findViewById(R.id.refreshBtn);
        Button uniformityBtn = findViewById(R.id.uniformity);
        Button checkItemSetBtn = findViewById(R.id.setMinuteBtn);

        Button allCheckBtn = findViewById(R.id.allCheckBtn);
        Button allUncheckBtn = findViewById(R.id.allUncheckBtn);
        Button reversCheckBtn = findViewById(R.id.reverseBtn);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button readBtn = findViewById(R.id.readBtn);

        Button driverSetBtn = findViewById(R.id.driverBtn);

        Button setRaceStartTimeBtn = findViewById(R.id.setRaceStartTimeBtn);

        //ドライバー一括設定ボタン
        Button akimaBtn = findViewById(R.id.setDriverAkima);
        Button toyoguchiBtn = findViewById(R.id.setDriverToyoguchi);
        Button yoshikaiBtn = findViewById(R.id.setDriverYoshikai);
        Button LukeBtn = findViewById(R.id.setDriverLuke);
        Button yokotaBtn = findViewById(R.id.setDriverYokota);
        Button tuboiBtn = findViewById(R.id.setDriverTuboi);
        Button nittaBtn = findViewById(R.id.setDriverNitta);
        Button xBtn = findViewById(R.id.setDriverX);
        Button nullBtn = findViewById(R.id.setDriverNull);
        Button pauseBtn = findViewById(R.id.setPause);

        Button qrBtn = findViewById(R.id.qrCreateBtn);

        perStintCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perStintTimeCalc();
            }
        });

        perStintCalcBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayUpdate();
                return false;
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
                            if (i==stintData.getStintCnt()-1 && endTimeFixedToggle.isChecked()){
                                stintData.setEndTime(i, timeCalc.calcPlusTime(stintData.getRaceData()[0][1], raceTime));
                            }else {
                                stintData.setEndTime(i, timeCalc.calcPlusTime(stintData.getRaceData()[i][1], stintData.getPerStintTime()));
                            }
                        }
                    }

                    stintData.getRaceData()[stintData.getStintCnt() - 1][2] = timeCalc.calcPlusTime(stintData.getRaceData()[0][1], raceTime);
                    Log.d("@@", "onClick: stintData.getRaceData()[0][1]" + stintData.getRaceData()[0][1]);
                    Log.d("@@", "onClick: raceTime" + raceTime);
                    Log.d("@@", "onClick: stintData.getRaceData()[stintData.getStintCnt()-1][2]" + stintData.getRaceData()[stintData.getStintCnt() - 1][2]);
                    displayUpdate();
                }
                //CSVに書き出し
                saveFile();
            }
        });

        uniformityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBoxにチェックが入っている最後のStintを検出
                for (int i = allStintCnt()-1; i >= 0; i--) {
                    Log.d("TAG", "onClick: flagCheckBox[" + i + "].isChecked() = " + flagCheckBox[i].isChecked());
                    if (flagCheckBox[i].isChecked()){
                        String uniformityStartTime = stintData.getRaceData()[i][1];
                        String uniformityEndTime = timeCalc.calcPlusTime(stintData.getRaceData()[0][1],raceTime);
                        Log.d("TAG", "onClick: uniformityStartTime = " + uniformityStartTime);
                        Log.d("TAG", "onClick: uniformityEndTime = " + uniformityEndTime);
                        uniformitySet(uniformityStartTime,uniformityEndTime,i);
                        break;
                    }
                }

                //CSVに書き出し
                saveFile();

                displayUpdate();
            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUpdate();
            }
        });

        /**
         * 選択項目にセットするボタン
         */
        checkItemSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setRunMin = Integer.parseInt(setMinEditText.getText().toString());
                }catch (Exception e){
                    Log.d("Exception", "onClick(checkItemSetBtn): " + e);
                }
                setCheckBoxes();
                setRunTimeArray();
                flagItemSetMin(setRunMin);

                //CSVに書き出し
                saveFile();

                displayUpdate();
            }
        });

        /**
         * AllCheckBtn
         */
        allCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < allStintCnt(); i++) {
                    flagCheckBox[i].setChecked(true);
                }
            }
        });

        allUncheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < maxStintCount; i++) {
                    flagCheckBox[i].setChecked(false);
                }
            }
        });

        reversCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < allStintCnt(); i++) {
                    flagCheckBox[i].setChecked(!flagCheckBox[i].isChecked());
                }
            }
        });

        driverSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverSet();
            }
        });

        setRaceStartTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), InputForm.class);
                intent.putExtra("Stint", 999);//第一引数key、第二引数渡したい値
                startActivity(intent);
            }
        });

        akimaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_AKIMA);
            }
        });

        toyoguchiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_TOYOGUCHI);
            }
        });

        yoshikaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_YOSHIKAI);
            }
        });

        LukeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_LUKE);
            }
        });

        yokotaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_YOKOTA);
            }
        });

        tuboiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_TUBOI);
            }
        });

        nittaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_NITTA);
            }
        });

        xBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_X);
            }
        });

        nullBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_NULL);
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDriver(ID_PAUSE);
            }
        });

        setButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedBoxesCnt = checkedBoxesCnt();
                if(checkedBoxesCnt==1){
                    Intent intent = new Intent(getApplication(), InputForm.class);
                    intent.putExtra("Stint", firstCheckBox());//第一引数key、第二引数渡したい値
                    startActivity(intent);
                }else if(checkedBoxesCnt == 0){
                    Context context = getApplicationContext();
                    Toast.makeText(context,"チェックボックスにチェックがついていますせん",Toast.LENGTH_LONG).show();
                }else{
                    Context context = getApplicationContext();
                    Toast.makeText(context,"複数のチェックボックスにチェックがついています",Toast.LENGTH_LONG).show();
                }

            }
        });



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
                displayUpdate();
            }
        });

        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";

                for (int i = 0; i < stintData.getMaxStintCount(); i++) {

                    data += String.valueOf(stintData.getRaceData()[i][1]) + ","
                            + String.valueOf(stintData.getRaceData()[i][2]) + ","
                            + getDriverNum(String.valueOf(stintData.getRaceData()[i][3])) + ","
                            + String.valueOf(stintData.getRaceData()[i][4]) + ",";
                }

                editor.putString("input", "startTime,endTime,driverName,KartNo," + data);
                editor.apply();

                Intent intent = new Intent(getApplication(), qrCreateActivity.class);
                startActivity(intent);
            }
        });

        //描画更新時にトグルスイッチが終了時間を固定にしている場合
        // 最終Stintの完了時間をセットする
        if (endTimeFixedToggle.isChecked() && allStintCnt()>0){
            stintData.setEndTime(allStintCnt()-1, timeCalc.calcPlusTime(stintData.getRaceData()[0][1], raceTime));
        }

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

        //Log.d("TAG", "runTimeCalc: start=" + startTime);
        //Log.d("TAG", "runTimeCalc: start=" + endTime);

        int startHour = timeCalc.hourExtraction(startTime);
        int startMin = timeCalc.minutesExtraction(startTime);
        int endHour = timeCalc.hourExtraction(endTime);
        int endMin = timeCalc.minutesExtraction(endTime);

        int start = startHour*60 + startMin;
        int end = endHour*60 + endMin;
        int runtimeInt;

        //Log.d("TAG", "runTimeCalc: start=" + start);
        //Log.d("TAG", "runTimeCalc: end=" + end);

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
            Log.d("TAG", "startTime = " + startTime);
            Log.d("TAG", "endTime = " + endTime);
            String time = runTimeCalc(startTime,endTime);
            Log.d("TAG", "time = " + time);
            int time_min = timeCalc.convertTimeToMin(time);
            int remainingStint = allStintCnt() - stint;
            Log.d("TAG", "onClick: remainingStint = " + remainingStint);
            Log.d("TAG", "onClick: time_min = " + time_min);
            int perStint = Math.round(time_min/remainingStint);

            Log.d("TAG", "uniformitySet: perStint" + perStint);

            for (int i = stint; i < allStintCnt(); i++) {
                Log.d("====TAG====", "i:" + i);
                stintData.setEndTime(i, timeCalc.calcPlusTime(stintData.getRaceData()[i][1], perStint));
            }

        }catch (Exception e){
            Log.d("Exception", "onClick: " + e);
        }
    }

    /**
     * checkBoxの状況を取得しセット
     */
    private void setCheckBoxes(){
        for (int i = 0; i < allStintCnt(); i++) {
            flagCheckBoxes[i] = flagCheckBox[i].isChecked();
            Log.d("TAG", "setCheckBoxes: flagCheckBoxes[" + i + "]=" + flagCheckBoxes[i]);
        }
    }

    /**
     * チェックボックスがついている最後のチェックボックスを返す
     * @return チェックのついている最後のチェックボックスの番号
     */
    private int lastCheckBox(){
        int lastChkBox = 0;
        for (int i = allStintCnt()-1; i >= 0; i--) {
            if (flagCheckBox[i].isChecked()){
                lastChkBox = i;
                break;
            }
        }
        return lastChkBox;
    }

    /**
     * チェックボックスがついている最初のチェックボックスを返す
     * @return チェックのついている最初のチェックボックスの番号
     */
    private int firstCheckBox(){
        int firstChkBox = 0;
        for (int i = 0; i <= allStintCnt(); i++) {
            if (flagCheckBox[i].isChecked()){
                firstChkBox = i;
                break;
            }
        }
        return firstChkBox;
    }

    /**
     * チェックがついているチェックボックスの数を返す
     * @return チェックがついているチェックボックスの数
     */
    private int checkedBoxesCnt(){
        int checkedBoxesCnt=0;
        for (int i = 0; i <= allStintCnt(); i++) {
            if (flagCheckBox[i].isChecked()){
                checkedBoxesCnt++;
            }
        }
        return checkedBoxesCnt;
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
            kartNoTextView[i].setText(stintData.getRaceData()[i][4]);

            if (stintData.getRaceData()[i][3].equals("中断")){
                driverTimeTextView[i].setTextColor(Color.RED);
            }else{
                driverTimeTextView[i].setTextColor(Color.BLACK);
            }
        }

        //各ドライバーの走行時間の計算・更新
        setRuntimeSum();
        stintCnt();
    }


    /**
     * FlagがTrueのStintに対してセットした分数に変更。
     * FlagがFalseの項目に対してはセットした値を考慮した開始時間・終了時間に再セット
     * @param runMin
     */
    private void flagItemSetMin(int runMin){
        for (int i = 0; i < allStintCnt(); i++) {
            if (flagCheckBoxes[i]){
                //走行終了時間に走行開始にセットしたい時間を足した時間をセットする
                stintData.setEndTime(i,timeCalc.calcPlusTime(stintData.getRaceData()[i][1],runMin));
            }else{
                if (i==allStintCnt()-1 && endTimeFixedToggle.isChecked()){
                    Log.d("TAG", "flagItemSetMin:@@@@@@@ " + raceTime);
                    //走行終了時間に走行開始にもともとセットされていた走行時間を足した時間をセットする
                    stintData.setEndTime(i,timeCalc.calcPlusTime(stintData.getRaceData()[0][1],raceTime));

                }else{
                    //走行終了時間に走行開始にもともとセットされていた走行時間を足した時間をセットする
                    Log.d("TAG", "flagItemSetMin:FALSE");
                    stintData.setEndTime(i,timeCalc.calcPlusTime(stintData.getRaceData()[i][1],runTime[i]));
                }
            }
        }
    }

    /**
     * 各Stintの走行時間をrunTime[]に格納
     */
    private void setRunTimeArray(){
        for (int i = 0; i < maxStintCount; i++) {
            runTime[i] = timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1],stintData.getRaceData()[i][2]));
            Log.d("TAG", "setRunTime: runTime[" + i + "]=" + runTime[i]);
        }
    }

    private void setRuntimeSum(){
        int[] runTime = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < maxStintCount; i++) {
            if (stintData.getDriverName(i).equals("秋間")) {
                runTime[0] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("豊口")) {
                runTime[1] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("吉戒")) {
                runTime[2] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("ルーク")) {
                runTime[3] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("横田")) {
                runTime[4] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("坪井")) {
                runTime[5] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("新田")) {
                runTime[6] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("X")) {
                runTime[7] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            } else if (stintData.getDriverName(i).equals("中断")){
                runTime[9] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            }else{
                runTime[8] += timeCalc.convertTimeToMin(runTimeCalc(stintData.getRaceData()[i][1], stintData.getRaceData()[i][2]));
            }
        }

        //走行時間が１分以上のドライバーの数を計算
        int driverCnt = 0;
        for (int i = 0; i < runTime.length-2; i++) {
            if (runTime[i]>0){
                driverCnt++;
            }
        }
        if (driverCnt == 0){
            driverCnt = runTime.length;
        }

        //規定走行時間以上の場合に赤文字で表示する
        for (int i = 0; i < runTime.length; i++) {
            //runSumTimeTextView[i].setText(timeCalc.timeFormatExtraction(runTime[i]));
            runSumTimeTextView[i].setText(runTime[i] + "min");
            if (runTime[i] >= maxRunTime(driverCnt)){
                runSumTimeTextView[i].setTextColor(Color.RED);
            }else{
                runSumTimeTextView[i].setTextColor(Color.BLACK);
            }
        }
        maxRunTimeTextView.setText(maxRunTime(driverCnt) + "min");
    }


    // ファイルを保存
    public void saveFile(String str,int data) {
        if (data == 0) {
            // try-with-resources
            try (FileWriter writer = new FileWriter(startTimeFile)) {
                writer.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(data == 1){
            // try-with-resources
            try (FileWriter writer = new FileWriter(endTimeFile)) {
                writer.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(data == 2){
            // try-with-resources
            try (FileWriter writer = new FileWriter(driverTimeFile)) {
                writer.write(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Log.d("TAG","ファイル選択エラー");
        }

    }

    // ファイルを読み出し
    public String readFile_() {
        String readText = null;

        // try-with-resources
        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ){
            readText = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return readText;
    }

    private void saveFile(){
        String startTimeText = "";
        String endTimeText = "";
        String driverTimeText = "";

        for (int i = 0; i < stintData.getMaxStintCount(); i++) {
            if (i==0){
                startTimeText = String.valueOf(stintData.getRaceData()[i][1]);
                endTimeText =String.valueOf(stintData.getRaceData()[i][2]);
                driverTimeText = String.valueOf(stintData.getRaceData()[i][3]);
            }else {
                startTimeText += "," + String.valueOf(stintData.getRaceData()[i][1]);
                endTimeText += "," + String.valueOf(stintData.getRaceData()[i][2]);
                driverTimeText += "," + String.valueOf(stintData.getRaceData()[i][3]);
            }
        }
        Log.d("saveBtn", "startTimeText" + startTimeText);
        Log.d("saveBtn", "endTimeText:" + endTimeText);
        Log.d("saveBtn", "driverTimeText" + driverTimeText);

        saveFile(startTimeText,0);
        saveFile(endTimeText,1);
        saveFile(driverTimeText,2);
        if(startTimeText.length() == 0 || endTimeText.length()== 0 || driverTimeText.length()== 0){
            //statusText.setText(R.string.no_text);
        }
        else{
            //statusText.setText(R.string.saved);
        }
    }

    public void readFile(){

        String startTimeReadText[] = new String[stintData.getMaxStintCount()];
        String endTimeReadText[] = new String[stintData.getMaxStintCount()];
        String driverTimeReadText[] = new String[stintData.getMaxStintCount()];

        // try-with-resources
        try(BufferedReader br = new BufferedReader(new FileReader(startTimeFile))){
            startTimeReadText =  br.readLine().split(",");
            for (int i = 0; i < stintData.getMaxStintCount(); i++) {
                stintData.getRaceData()[i][1] = startTimeReadText[i];
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(endTimeFile))){
            endTimeReadText =  br.readLine().split(",");
            for (int i = 0; i < stintData.getMaxStintCount(); i++) {
                stintData.getRaceData()[i][2] = endTimeReadText[i];
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(driverTimeFile))){
            driverTimeReadText =  br.readLine().split(",");
            for (int i = 0; i < stintData.getMaxStintCount(); i++) {
                stintData.getRaceData()[i][3] = driverTimeReadText[i];
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 15; i++) {
            Log.d("ReadFile",i + " : " + startTimeReadText[i] +" : "+endTimeReadText[i]+" : "+driverTimeReadText[i]);
        }

        //statusText.setText(R.string.read_comp);
    }

    private void setDriver(int driverID){
        setCheckBoxes();
        for (int i = 0; i < allStintCnt(); i++) {
            if(flagCheckBoxes[i]){
                switch (driverID){
                    case ID_AKIMA:
                        stintData.setDriver(i,"秋間");
                        break;
                    case ID_TOYOGUCHI:
                        stintData.setDriver(i,"豊口");
                        break;
                    case ID_YOSHIKAI:
                        stintData.setDriver(i,"吉戒");
                        break;
                    case ID_LUKE:
                        stintData.setDriver(i,"ルーク");
                        break;
                    case ID_YOKOTA:
                        stintData.setDriver(i,"横田");
                        break;
                    case ID_TUBOI:
                        stintData.setDriver(i,"坪井");
                        break;
                    case ID_NITTA:
                        stintData.setDriver(i,"新田");
                        break;
                    case ID_X:
                        stintData.setDriver(i,"X");
                        break;
                    case ID_NULL:
                        stintData.setDriver(i,"-");
                        break;
                    case ID_PAUSE:
                        if (!stintData.getDriverName(i).equals("中断")){
                            //ドライバーを繰り下げする
                            shiftList(i);
                            stintData.setDriver(i,"中断");
                            //中断後のStintを均等割りする
                            String uniformityStartTime = stintData.getRaceData()[i+1][1];
                            String uniformityEndTime = timeCalc.calcPlusTime(stintData.getRaceData()[0][1],raceTime);
                            uniformitySet(uniformityStartTime,uniformityEndTime,i+1);
                        }
                        break;
                }

            }
        }
        displayUpdate();
    }

    private int allStintCnt(){
        pauseCnt = 0;
        //中断の回数をallStintに追加することで帳尻を合わせる
        for (int i = 0; i < allStint; i++) {
            if (stintData.getDriverName(i).equals("中断")){
                //中断後は中断前のドライバーが乗るため、中断回数＊2を追加
                pauseCnt = pauseCnt + 2;
                Log.d("allStintCnt","pauseCnt = " + pauseCnt);
            }
        }
        stintData.setPauseCnt(pauseCnt);
        int allStintCnt = allStint + pauseCnt;
        Log.d("allStintCnt","allStintCnt = " + allStintCnt);
        return allStintCnt;
    }

    /**
     * 規則上最長の走行時間を計算して返す
     * @param driverCnt 参加ドライバー人数
     * @return 均等割り＊COEF(120%ルール)の値を返す
     */
    private int maxRunTime(int driverCnt){
        Log.d("maxRunTime","raceTime = " + raceTime + ",COEF = " + COEF + ",driverCnt = " + driverCnt);
        double maxTimeD = raceTime/driverCnt*COEF;
        Log.d("maxRunTime","maxTimeD(double) = " + maxTimeD);
        int maxTimeI = (int)maxTimeD;
        Log.d("maxRunTime","maxTimeI(int) = " + maxTimeI);
        return maxTimeI;
    }


    /**
     * 引数で取得した位置から先のドライバーをずらす
     * @param listNum ずらす開始位置
     */
    private void shiftList(int listNum){
        for (int i = allStintCnt()+1; i > listNum; i--) {
            stintData.setDriverName(i,stintData.getDriverName(i-2));
        }

    }

    /**
     * チェックがついているものを取得して、チェックボックスがついていない項目に繰り返しでドライバー名をセットする
     */
    private void driverSet(){
        Log.d("driverSet[IN]","driverSet[IN]");
        setCheckBoxes();
        String[] driver = new String[allStintCnt()];
        int repeatStint = 0;
        for (int i = 0; i < allStintCnt(); i++) {
            if (flagCheckBoxes[i]){
                driver[repeatStint]=stintData.getDriverName(i);
                Log.d("driverSet","driver[" + repeatStint + "] = " + stintData.getDriverName(i));
                repeatStint++;
            }
        }

        int repeatStintj = 0;
        //最後のチェックボックスから先を繰り返しでセットする
        //配列の関係でStint数から1引いた値で処理を行う
        int cnt = allStintCnt()-1;
        for (int i = lastCheckBox(); i < cnt; i++) {
            Log.d("driverSet[IN]","driverSet() int i = " + i);
            if (repeatStintj >= repeatStint) {
                repeatStintj = 0;
            }
            Log.d("driverSet[IN]","driver[" + repeatStintj + "]  = " + driver[repeatStintj]);
            stintData.setDriver(i + 1, driver[repeatStintj]);
            repeatStintj++;
        }
        for (int i = 0; i < cnt; i++) {
            Log.d("driverSet","stintData.getDriver(" + i + ") = " + stintData.getDriverName(i));
        }
        displayUpdate();
    }

    private String getDriverNum(String name){
        String driverNum = "0";
        for (int i = 0; i < driverList.length; i++) {
            if (name.equals(driverList[i])){
                driverNum = Integer.toString(i);
            }else{
                Log.v("MainActivity","driverNo. not found");
            }
        }

        return driverNum;
    }

    private void stintCnt(){
        for (int i = 0; i < driverItem.length; i++) {
            stintCntTextView[i].setText(stintData.getCntStintPerDriver(driverItem[i]) + "stint");
            Log.v("MainActivity","driverName:" + driverItem[i] + ",Stint:" + stintData.getCntStintPerDriver(driverItem[i]));
        }
    }

    private void defineId(){
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
        startTimeTextView[15] = findViewById(R.id.startTime15);
        startTimeTextView[16] = findViewById(R.id.startTime16);
        startTimeTextView[17] = findViewById(R.id.startTime17);
        startTimeTextView[18] = findViewById(R.id.startTime18);
        startTimeTextView[19] = findViewById(R.id.startTime19);
        startTimeTextView[20] = findViewById(R.id.startTime20);
        startTimeTextView[21] = findViewById(R.id.startTime21);
        startTimeTextView[22] = findViewById(R.id.startTime22);
        startTimeTextView[23] = findViewById(R.id.startTime23);
        startTimeTextView[24] = findViewById(R.id.startTime24);
        startTimeTextView[25] = findViewById(R.id.startTime25);
        startTimeTextView[26] = findViewById(R.id.startTime26);
        startTimeTextView[27] = findViewById(R.id.startTime27);
        startTimeTextView[28] = findViewById(R.id.startTime28);
        startTimeTextView[29] = findViewById(R.id.startTime29);
        startTimeTextView[30] = findViewById(R.id.startTime30);
        startTimeTextView[31] = findViewById(R.id.startTime31);
        startTimeTextView[32] = findViewById(R.id.startTime32);
        startTimeTextView[33] = findViewById(R.id.startTime33);
        startTimeTextView[34] = findViewById(R.id.startTime34);
        startTimeTextView[35] = findViewById(R.id.startTime35);
        startTimeTextView[36] = findViewById(R.id.startTime36);
        startTimeTextView[37] = findViewById(R.id.startTime37);
        startTimeTextView[38] = findViewById(R.id.startTime38);
        startTimeTextView[39] = findViewById(R.id.startTime39);
        startTimeTextView[40] = findViewById(R.id.startTime40);
        startTimeTextView[41] = findViewById(R.id.startTime41);
        startTimeTextView[42] = findViewById(R.id.startTime42);
        startTimeTextView[43] = findViewById(R.id.startTime43);
        startTimeTextView[44] = findViewById(R.id.startTime44);


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
        endTimeTextView[15] = findViewById(R.id.endTime15);
        endTimeTextView[16] = findViewById(R.id.endTime16);
        endTimeTextView[17] = findViewById(R.id.endTime17);
        endTimeTextView[18] = findViewById(R.id.endTime18);
        endTimeTextView[19] = findViewById(R.id.endTime19);
        endTimeTextView[20] = findViewById(R.id.endTime20);
        endTimeTextView[21] = findViewById(R.id.endTime21);
        endTimeTextView[22] = findViewById(R.id.endTime22);
        endTimeTextView[23] = findViewById(R.id.endTime23);
        endTimeTextView[24] = findViewById(R.id.endTime24);
        endTimeTextView[25] = findViewById(R.id.endTime25);
        endTimeTextView[26] = findViewById(R.id.endTime26);
        endTimeTextView[27] = findViewById(R.id.endTime27);
        endTimeTextView[28] = findViewById(R.id.endTime28);
        endTimeTextView[29] = findViewById(R.id.endTime29);
        endTimeTextView[30] = findViewById(R.id.endTime30);
        endTimeTextView[31] = findViewById(R.id.endTime31);
        endTimeTextView[32] = findViewById(R.id.endTime32);
        endTimeTextView[33] = findViewById(R.id.endTime33);
        endTimeTextView[34] = findViewById(R.id.endTime34);
        endTimeTextView[35] = findViewById(R.id.endTime35);
        endTimeTextView[36] = findViewById(R.id.endTime36);
        endTimeTextView[37] = findViewById(R.id.endTime37);
        endTimeTextView[38] = findViewById(R.id.endTime38);
        endTimeTextView[39] = findViewById(R.id.endTime39);
        endTimeTextView[40] = findViewById(R.id.endTime40);
        endTimeTextView[41] = findViewById(R.id.endTime41);
        endTimeTextView[42] = findViewById(R.id.endTime42);
        endTimeTextView[43] = findViewById(R.id.endTime43);
        endTimeTextView[44] = findViewById(R.id.endTime44);


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
        runTimeTextView[15] = findViewById(R.id.runTime15);
        runTimeTextView[16] = findViewById(R.id.runTime16);
        runTimeTextView[17] = findViewById(R.id.runTime17);
        runTimeTextView[18] = findViewById(R.id.runTime18);
        runTimeTextView[19] = findViewById(R.id.runTime19);
        runTimeTextView[20] = findViewById(R.id.runTime20);
        runTimeTextView[21] = findViewById(R.id.runTime21);
        runTimeTextView[22] = findViewById(R.id.runTime22);
        runTimeTextView[23] = findViewById(R.id.runTime23);
        runTimeTextView[24] = findViewById(R.id.runTime24);
        runTimeTextView[25] = findViewById(R.id.runTime25);
        runTimeTextView[26] = findViewById(R.id.runTime26);
        runTimeTextView[27] = findViewById(R.id.runTime27);
        runTimeTextView[28] = findViewById(R.id.runTime28);
        runTimeTextView[29] = findViewById(R.id.runTime29);
        runTimeTextView[30] = findViewById(R.id.runTime30);
        runTimeTextView[31] = findViewById(R.id.runTime31);
        runTimeTextView[32] = findViewById(R.id.runTime32);
        runTimeTextView[33] = findViewById(R.id.runTime33);
        runTimeTextView[34] = findViewById(R.id.runTime34);
        runTimeTextView[35] = findViewById(R.id.runTime35);
        runTimeTextView[36] = findViewById(R.id.runTime36);
        runTimeTextView[37] = findViewById(R.id.runTime37);
        runTimeTextView[38] = findViewById(R.id.runTime38);
        runTimeTextView[39] = findViewById(R.id.runTime39);
        runTimeTextView[40] = findViewById(R.id.runTime40);
        runTimeTextView[41] = findViewById(R.id.runTime41);
        runTimeTextView[42] = findViewById(R.id.runTime42);
        runTimeTextView[43] = findViewById(R.id.runTime43);
        runTimeTextView[44] = findViewById(R.id.runTime44);


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
        driverTimeTextView[15] = findViewById(R.id.driver15);
        driverTimeTextView[16] = findViewById(R.id.driver16);
        driverTimeTextView[17] = findViewById(R.id.driver17);
        driverTimeTextView[18] = findViewById(R.id.driver18);
        driverTimeTextView[19] = findViewById(R.id.driver19);
        driverTimeTextView[20] = findViewById(R.id.driver20);
        driverTimeTextView[21] = findViewById(R.id.driver21);
        driverTimeTextView[22] = findViewById(R.id.driver22);
        driverTimeTextView[23] = findViewById(R.id.driver23);
        driverTimeTextView[24] = findViewById(R.id.driver24);
        driverTimeTextView[25] = findViewById(R.id.driver25);
        driverTimeTextView[26] = findViewById(R.id.driver26);
        driverTimeTextView[27] = findViewById(R.id.driver27);
        driverTimeTextView[28] = findViewById(R.id.driver28);
        driverTimeTextView[29] = findViewById(R.id.driver29);
        driverTimeTextView[30] = findViewById(R.id.driver30);
        driverTimeTextView[31] = findViewById(R.id.driver31);
        driverTimeTextView[32] = findViewById(R.id.driver32);
        driverTimeTextView[33] = findViewById(R.id.driver33);
        driverTimeTextView[34] = findViewById(R.id.driver34);
        driverTimeTextView[35] = findViewById(R.id.driver35);
        driverTimeTextView[36] = findViewById(R.id.driver36);
        driverTimeTextView[37] = findViewById(R.id.driver37);
        driverTimeTextView[38] = findViewById(R.id.driver38);
        driverTimeTextView[39] = findViewById(R.id.driver39);
        driverTimeTextView[40] = findViewById(R.id.driver40);
        driverTimeTextView[41] = findViewById(R.id.driver41);
        driverTimeTextView[42] = findViewById(R.id.driver42);
        driverTimeTextView[43] = findViewById(R.id.driver43);
        driverTimeTextView[44] = findViewById(R.id.driver44);

        kartNoTextView[0] = findViewById(R.id.kartNo0);
        kartNoTextView[1] = findViewById(R.id.kartNo1);
        kartNoTextView[2] = findViewById(R.id.kartNo2);
        kartNoTextView[3] = findViewById(R.id.kartNo3);
        kartNoTextView[4] = findViewById(R.id.kartNo4);
        kartNoTextView[5] = findViewById(R.id.kartNo5);
        kartNoTextView[6] = findViewById(R.id.kartNo6);
        kartNoTextView[7] = findViewById(R.id.kartNo7);
        kartNoTextView[8] = findViewById(R.id.kartNo8);
        kartNoTextView[9] = findViewById(R.id.kartNo9);
        kartNoTextView[10] = findViewById(R.id.kartNo10);
        kartNoTextView[11] = findViewById(R.id.kartNo11);
        kartNoTextView[12] = findViewById(R.id.kartNo12);
        kartNoTextView[13] = findViewById(R.id.kartNo13);
        kartNoTextView[14] = findViewById(R.id.kartNo14);
        kartNoTextView[15] = findViewById(R.id.kartNo15);
        kartNoTextView[16] = findViewById(R.id.kartNo16);
        kartNoTextView[17] = findViewById(R.id.kartNo17);
        kartNoTextView[18] = findViewById(R.id.kartNo18);
        kartNoTextView[19] = findViewById(R.id.kartNo19);
        kartNoTextView[20] = findViewById(R.id.kartNo20);
        kartNoTextView[21] = findViewById(R.id.kartNo21);
        kartNoTextView[22] = findViewById(R.id.kartNo22);
        kartNoTextView[23] = findViewById(R.id.kartNo23);
        kartNoTextView[24] = findViewById(R.id.kartNo24);
        kartNoTextView[25] = findViewById(R.id.kartNo25);
        kartNoTextView[26] = findViewById(R.id.kartNo26);
        kartNoTextView[27] = findViewById(R.id.kartNo27);
        kartNoTextView[28] = findViewById(R.id.kartNo28);
        kartNoTextView[29] = findViewById(R.id.kartNo29);
        kartNoTextView[30] = findViewById(R.id.kartNo30);
        kartNoTextView[31] = findViewById(R.id.kartNo31);
        kartNoTextView[32] = findViewById(R.id.kartNo32);
        kartNoTextView[33] = findViewById(R.id.kartNo33);
        kartNoTextView[34] = findViewById(R.id.kartNo34);
        kartNoTextView[35] = findViewById(R.id.kartNo35);
        kartNoTextView[36] = findViewById(R.id.kartNo36);
        kartNoTextView[37] = findViewById(R.id.kartNo37);
        kartNoTextView[38] = findViewById(R.id.kartNo38);
        kartNoTextView[39] = findViewById(R.id.kartNo39);
        kartNoTextView[40] = findViewById(R.id.kartNo40);
        kartNoTextView[41] = findViewById(R.id.kartNo41);
        kartNoTextView[42] = findViewById(R.id.kartNo42);
        kartNoTextView[43] = findViewById(R.id.kartNo43);
        kartNoTextView[44] = findViewById(R.id.kartNo44);

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
        flagCheckBox[15] = findViewById(R.id.checkbox15);
        flagCheckBox[16] = findViewById(R.id.checkbox16);
        flagCheckBox[17] = findViewById(R.id.checkbox17);
        flagCheckBox[18] = findViewById(R.id.checkbox18);
        flagCheckBox[19] = findViewById(R.id.checkbox19);
        flagCheckBox[20] = findViewById(R.id.checkbox20);
        flagCheckBox[21] = findViewById(R.id.checkbox21);
        flagCheckBox[22] = findViewById(R.id.checkbox22);
        flagCheckBox[23] = findViewById(R.id.checkbox23);
        flagCheckBox[24] = findViewById(R.id.checkbox24);
        flagCheckBox[25] = findViewById(R.id.checkbox25);
        flagCheckBox[26] = findViewById(R.id.checkbox26);
        flagCheckBox[27] = findViewById(R.id.checkbox27);
        flagCheckBox[28] = findViewById(R.id.checkbox28);
        flagCheckBox[29] = findViewById(R.id.checkbox29);
        flagCheckBox[30] = findViewById(R.id.checkbox30);
        flagCheckBox[31] = findViewById(R.id.checkbox31);
        flagCheckBox[32] = findViewById(R.id.checkbox32);
        flagCheckBox[33] = findViewById(R.id.checkbox33);
        flagCheckBox[34] = findViewById(R.id.checkbox34);
        flagCheckBox[35] = findViewById(R.id.checkbox35);
        flagCheckBox[36] = findViewById(R.id.checkbox36);
        flagCheckBox[37] = findViewById(R.id.checkbox37);
        flagCheckBox[38] = findViewById(R.id.checkbox38);
        flagCheckBox[39] = findViewById(R.id.checkbox39);
        flagCheckBox[40] = findViewById(R.id.checkbox40);
        flagCheckBox[41] = findViewById(R.id.checkbox41);
        flagCheckBox[42] = findViewById(R.id.checkbox42);
        flagCheckBox[43] = findViewById(R.id.checkbox43);
        flagCheckBox[44] = findViewById(R.id.checkbox44);

        perStintTimeTextView = findViewById(R.id.perStintText);
        allStintTextEditText = findViewById(R.id.allStintEditText);
        raceTimeEditText = findViewById(R.id.raceTimeEditText);
        setMinEditText = findViewById(R.id.setTimeEditText);

        try {
            raceTime = Integer.parseInt(raceTimeEditText.getText().toString());
        }catch (Exception e){
            Log.e("errorLog","NumberFormatException:" + e );
            raceTime = 0;
            raceTimeEditText.setText("");
        }

        try {
            allStint = Integer.parseInt(allStintTextEditText.getText().toString());
        }catch (Exception e){
            Log.e("errorLog","NumberFormatException:" + e );
            allStint = 0;
            allStintTextEditText.setText("");
        }

        try {
            setRunMin = Integer.parseInt(setMinEditText.getText().toString());
        }catch (Exception e){
            Log.e("errorLog","NumberFormatException:" + e );
            setRunMin = 0;
            setMinEditText.setText("");
        }

        statusText = findViewById(R.id.statusText);

        runSumTimeTextView[0]  = findViewById(R.id.driver0SumTime);
        runSumTimeTextView[1]  = findViewById(R.id.driver1SumTime);
        runSumTimeTextView[2]  = findViewById(R.id.driver2SumTime);
        runSumTimeTextView[3]  = findViewById(R.id.driver3SumTime);
        runSumTimeTextView[4]  = findViewById(R.id.driver4SumTime);
        runSumTimeTextView[5]  = findViewById(R.id.driver5SumTime);
        runSumTimeTextView[6]  = findViewById(R.id.driver6SumTime);
        runSumTimeTextView[7]  = findViewById(R.id.driver7SumTime);
        runSumTimeTextView[8]  = findViewById(R.id.driver8SumTime);
        runSumTimeTextView[9]  = findViewById(R.id.driver9SumTime);
        maxRunTimeTextView = findViewById(R.id.maxRunTimeTextView);

        stintCntTextView[0] = findViewById(R.id.driver0StintCnt);
        stintCntTextView[1] = findViewById(R.id.driver1StintCnt);
        stintCntTextView[2] = findViewById(R.id.driver2StintCnt);
        stintCntTextView[3] = findViewById(R.id.driver3StintCnt);
        stintCntTextView[4] = findViewById(R.id.driver4StintCnt);
        stintCntTextView[5] = findViewById(R.id.driver5StintCnt);
        stintCntTextView[6] = findViewById(R.id.driver6StintCnt);
        stintCntTextView[7] = findViewById(R.id.driver7StintCnt);
        stintCntTextView[8] = findViewById(R.id.driver8StintCnt);
        stintCntTextView[9] = findViewById(R.id.driver9StintCnt);

        endTimeFixedToggle = findViewById(R.id.endTimeFixed);
    }


}