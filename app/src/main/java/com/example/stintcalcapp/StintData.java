package com.example.stintcalcapp;

import android.app.Application;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class StintData extends Application {

    private String driverName = "default";
    private int maxStintCount = 15;
    private int perStintTime = 0;
    private int stintCnt = 15;

    /**
     * [スティント数][データ数]
     * データ0 = ドライバー情報
     * データ1 = スティントの開始の時間
     * データ2 = スティントの終了時間
     */
    private String raceData[][] = new String[15][4];

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 0; i < raceData.length; i++) {
            raceData[i][0] = "0";
            raceData[i][1] = "00:00";
            raceData[i][2] = "00:00";
            raceData[i][3] = "-";
        }

        for (int i = 0; i < raceData.length; i++) {
            for (int j = 0; j < raceData[1].length; j++) {
                Log.d(TAG, "raceData[" + i + "][" + j + "] = " + raceData[i][j]);
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

    public void setStartTime(int stint, String startTime) {
        this.raceData[stint][1] = startTime;
    }

    public void setEndTime(int stint, String endTime) {
        this.raceData[stint][2] = endTime;
        if (stint != maxStintCount-1 && stint != stintCnt-1) {
            //次のStintの開始時間に時間をセット
            setStartTime(stint + 1, endTime);
        }
    }

    public void setDriver(int stint, String driverName) {
        this.raceData[stint][3] = driverName;
    }

    public int getMaxStintCount() {
        return maxStintCount;
    }

    public void setMaxStintCount(int maxStintCount) {
        this.maxStintCount = maxStintCount;
    }

    public int getPerStintTime() {
        return perStintTime;
    }

    public void setPerStintTime(int perStintTime) {
        this.perStintTime = perStintTime;
    }

    public int getStintCnt() {
        return stintCnt;
    }

    public void setStintCnt(int stintCnt) {
        this.stintCnt = stintCnt;
    }
}
