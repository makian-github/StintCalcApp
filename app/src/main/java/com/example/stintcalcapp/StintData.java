package com.example.stintcalcapp;

import android.app.Application;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class StintData extends Application {

    private String driverName = "default";
    private int maxStintCount = 45;
    private int perStintTime = 0;
    private int stintCnt = 45;
    private int driverCnt = 9;
    private int pauseCnt = 0;
    private int kartNo = 0;

    /**
     * [スティント数][データ数]
     * データ0 = ドライバー情報
     * データ1 = スティントの開始の時間
     * データ2 = スティントの終了時間
     * データ3 = ドライバー名
     */
    private String raceData[][] = new String[maxStintCount][5];

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 0; i < raceData.length; i++) {
            raceData[i][0] = "0";
            raceData[i][1] = "00:00";
            raceData[i][2] = "00:00";
            raceData[i][3] = "-";
            raceData[i][4] = "0";
        }

        for (int i = 0; i < raceData.length; i++) {
            for (int j = 0; j < raceData[1].length; j++) {
                Log.d(TAG, "raceData[" + i + "][" + j + "] = " + raceData[i][j]);
            }
        }
    }

    public String getDriverName(int stint) {
        return this.raceData[stint][3];
    }

    /**
     * 受け取ったStintに受け取ったドライバー名を設定
     * @param stint　設定したいStint
     * @param driverName　設定したいドライバー名
     */
    public void setDriverName(int stint,String driverName) {
        this.raceData[stint][3] = driverName;
    }

    /**
     * [スティント数][データ数]
     * データ0 = ドライバー情報
     * データ1 = スティントの開始の時間
     * データ2 = スティントの終了時間
     * データ3 = ドライバー名
     * データ4 = カートの号車
     * @return
     */
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
        if (stint < maxStintCount+pauseCnt-1 && stint != stintCnt+pauseCnt-1) {
            //次のStintの開始時間に時間をセット
            setStartTime(stint + 1, endTime);
        }
    }

    public void setDriver(int stint, String driverName) {
        this.raceData[stint][3] = driverName;
    }

    public void setKartNo(int stint, String kartNo) {
        this.raceData[stint][4] = kartNo;
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

    public int getDriverCnt() {
        return driverCnt;
    }

    public int getPauseCnt() {
        return pauseCnt;
    }

    public void setPauseCnt(int pauseCnt) {
        this.pauseCnt = pauseCnt;
    }

    public int getKartNo(int stint) {
        kartNo = Integer.parseInt(this.raceData[stint][4]);
        return kartNo;
    }

    /**
     * Stint数よりも先のデータを初期化
     */
    public void clearRaceData(){
        for (int i = stintCnt; i < maxStintCount; i++) {
            raceData[i][0] = "0";
            raceData[i][1] = "00:00";
            raceData[i][2] = "00:00";
            raceData[i][3] = "-";
            raceData[i][4] = "0";
        }
    }
}
