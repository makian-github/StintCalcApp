package com.example.stintcalcapp;

import android.util.Log;

public class TimeCalc {
    /**
     * 00:00の書式から分に変換
     * @return
     */
    public int convertTimeToMin(String time){
        int minTime = hourExtraction(time)*60 + minutesExtraction(time);
        return minTime;
    }

    /**
     * 00:00(time)から時(Hour)を抽出
     * @param time 00:00
     * @return hour
     */
    public int hourExtraction(String time) {
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
    public int minutesExtraction(String time) {
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
    public String calcPlusTime(String time , int plusTime) {

        int timeHour = hourExtraction(time);
        int timeMin = minutesExtraction(time);

        //引数で渡された値に1Stint当たりの走行時間を足す
        int endTime = timeHour * 60 + timeMin + plusTime;

        //00:00の書式でreturn
        String returnTime = String.format("%d:%02d", endTime / 60, endTime % 60);
        Log.d("TAG", "calcPlusTime: returnTime = " + returnTime);

        return returnTime;
    }
}
