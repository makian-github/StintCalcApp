package com.example.stintcalcapp;

import android.app.Application;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class StintData extends Application {

    private String driverName = "default";

    /**
     * [スティント数][データ数]
     * データ0 = ドライバー情報
     * データ1 = スティントの開始の時間
     * データ2 = スティントの終了時間
     */
    private String raceData[][] = new String[2][3];

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 0; i < raceData.length; i++) {
            raceData[i][0] = "driver";
            for (int j = 1; j < raceData[1].length; j++) {
                raceData[i][j]= "0";
            }
        }

        for (int i = 0; i < raceData.length; i++) {
            for (int j = 0; j < raceData[1].length; j++) {
                Log.d(TAG, "raceData[" + i + "][" + j +  "] = " + raceData[i][j] );
            }
        }
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String[][] getRaceData() {
        return raceData;
    }

    public void setRaceData(String[][] raceData) {
        this.raceData = raceData;
    }

    public void setDriver(int stint,String driverName){
        this.raceData[stint][0] = driverName;
    }

    public void setStartTime(int stint,String startTime){
        this.raceData[stint][1] = startTime;
    }

    public void setEndTime(int stint,String endTime){
        this.raceData[stint][2] = endTime;
    }

}
