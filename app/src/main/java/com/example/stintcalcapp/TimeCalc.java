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

    /**
     * 引数で渡された時間(分)を00:00のformatに変換
     * @param min 時間(分)
     * @return 引数で渡された値を00:00のformatに変換した時間
     */
    public String timeFormatExtraction(int min) {
        Log.d("TAG", "timeFormatExtraction: " + min);
        int hour = min/60;
        int minute = min%60;
        String time = String.format("%02d:%02d",hour,minute);
        return time;
    }

    /**
     * 引数で渡された時刻の時間差を分で返す(bTimeをaTimeにするための分)
     * @param bTime 比較元の時刻
     * @param aTime 比較先の時刻
     * @return
     */
    public int calcDiffMin(String bTime, String aTime ){
        Log.d("TimeCalc calcDiffMin InputForm", "calcDiffMin");
        int diffMin;
        int bTimeHour = hourExtraction(bTime);
        int bTimeMin = minutesExtraction(bTime);

        int aTimeHour = hourExtraction(aTime);
        int aTimeMin = minutesExtraction(aTime);

        diffMin = (aTimeHour-bTimeHour)*60+(aTimeMin-bTimeMin);

        Log.d("TimeCalc calcDiffMin InputForm", "diffMin:" + diffMin);
        return diffMin;
    }


}
