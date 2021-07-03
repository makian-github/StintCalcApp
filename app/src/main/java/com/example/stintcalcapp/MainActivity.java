package com.example.stintcalcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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

    private TimeCalc timeCalc;

    //ファイル出力用
    private File file;
    private File startTimeFile;
    private File endTimeFile;
    private File runTimeFile;
    private File driverTimeFile;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        flagCheckBox = new CheckBox[maxStintCount];

        //各Stintの走行時間の定義
        runTime = new int[maxStintCount];

        flagCheckBoxes = new boolean[maxStintCount];

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
        Button checkItemSetBtn = findViewById(R.id.setMinuteBtn);

        Button allCheckBtn = findViewById(R.id.allCheckBtn);
        Button allUncheckBtn = findViewById(R.id.allUncheckBtn);
        Button reversCheckBtn = findViewById(R.id.reverseBtn);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button readBtn = findViewById(R.id.readBtn);

        perStintTimeTextView = findViewById(R.id.perStintText);
        allStintTextEditText = findViewById(R.id.allStintEditText);
        raceTimeEditText = findViewById(R.id.raceTimeEditText);
        setMinEditText = findViewById(R.id.setTimeEditText);

        raceTime = Integer.parseInt(raceTimeEditText.getText().toString());
        allStint = Integer.parseInt(allStintTextEditText.getText().toString());
        setRunMin = Integer.parseInt(setMinEditText.getText().toString());

        statusText = findViewById(R.id.statusText);

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
                            stintData.setEndTime(i, timeCalc.calcPlusTime(stintData.getRaceData()[i][1], stintData.getPerStintTime()));
                        }
                    }

                    stintData.getRaceData()[stintData.getStintCnt() - 1][2] = timeCalc.calcPlusTime(stintData.getRaceData()[0][1], raceTime);
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

        /**
         * 選択項目にセットするボタン
         */
        checkItemSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRunMin = Integer.parseInt(setMinEditText.getText().toString());
                setCheckBoxes();
                setRunTimeArray();
                flagItemSetMin(setRunMin);
                displayUpdate();
            }
        });

        /**
         * AllCheckBtn
         */
        allCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < allStint; i++) {
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
                for (int i = 0; i < allStint; i++) {
                    flagCheckBox[i].setChecked(!flagCheckBox[i].isChecked());
                }
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
            String time = runTimeCalc(startTime,endTime);
            int time_min = timeCalc.convertTimeToMin(time);
            int remainingStint = allStint - stint;
            Log.d("TAG", "onClick: remainingStint = " + remainingStint);
            int perStint = Math.round(time_min/remainingStint);

            Log.d("TAG", "uniformitySet: perStint" + perStint);

            for (int i = stint; i < allStint; i++) {
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
        for (int i = 0; i < allStint; i++) {
            flagCheckBoxes[i] = flagCheckBox[i].isChecked();
            Log.d("TAG", "setCheckBoxes: flagCheckBoxes[" + i + "]=" + flagCheckBoxes[i]);
        }
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


    /**
     * FlagがTrueのStintに対してセットした分数に変更。
     * FlagがFalseの項目に対してはセットした値を考慮した開始時間・終了時間に再セット
     * @param runMin
     */
    private void flagItemSetMin(int runMin){
        for (int i = 0; i < allStint; i++) {
            if (flagCheckBoxes[i]){
                //走行終了時間に走行開始にセットしたい時間を足した時間をセットする
                stintData.setEndTime(i,timeCalc.calcPlusTime(stintData.getRaceData()[i][1],runMin));
            }else{
                //走行終了時間に走行開始にもともとセットされていた走行時間を足した時間をセットする
                stintData.setEndTime(i,timeCalc.calcPlusTime(stintData.getRaceData()[i][1],runTime[i]));
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
            statusText.setText(R.string.no_text);
        }
        else{
            statusText.setText(R.string.saved);
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

        statusText.setText(R.string.read_comp);
    }

}