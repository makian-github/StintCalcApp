package com.example.stintcalcapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

public class InputForm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private StintData stintData;
    private TextView startTimeText;
    private TextView endTimeText;
    private EditText driverNameText;
    private Spinner driverSpinner;
    private Button driverSetBtn;
    private Button kartNoSetBtn;
    private int stintNum;
    private int Button;
    private Spinner kartNoSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form_activity);

        Intent intent = getIntent();
        stintNum = intent.getIntExtra("Stint",0);//設定したkeyで取り出す

        stintData = (StintData) this.getApplication();
        startTimeText = findViewById(R.id.startTimeText);
        endTimeText = findViewById(R.id.endTimeText);

        startTimeText.setText(stintData.getRaceData()[stintNum][1]);
        endTimeText.setText(stintData.getRaceData()[stintNum][2]);

        //driverNameText = findViewById(R.id.driverText);
        driverSpinner = findViewById(R.id.driverSpinner);
        driverSetBtn = findViewById(R.id.driverSetBtn);

        kartNoSpinner = findViewById(R.id.kartNoSpinner);
        kartNoSetBtn = findViewById(R.id.kartNoSetBtn);

        //この画面を表示した際に、設定された値を取得して表示する
        driverSpinner.setSelection(stintData.getDriverNo(stintNum));
        kartNoSpinner.setSelection(stintData.getKartNo(stintNum));


        driverSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name = driverNameText.getText().toString();
                String name = (String)driverSpinner.getSelectedItem();
                stintData.setDriver(stintNum,name);
            }
        });

        kartNoSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kartNo = (String)kartNoSpinner.getSelectedItem();
                stintData.setKartNo(stintNum,kartNo);
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String str = String.format(Locale.US, "%02d:%02d", hourOfDay, minute);
        if (Button == 0){
            startTimeText.setText( str );
            stintData.setStartTime(stintNum,str);
        }else{
            endTimeText.setText( str );
            stintData.setEndTime(stintNum,str);
        }

    }

    public void showTimePickerDialog(View v) {
        String[] times = stintData.getRaceData()[stintNum][1].toString().split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        String startTime = String.format("%02d:%02d",hour,minute);

        DialogFragment newFragment = new TimePick();
        newFragment.show(getSupportFragmentManager(), startTime);
        Button = 0;

    }

    public void showTimePickerDialog1(View v) {
        String[] times = stintData.getRaceData()[stintNum][2].toString().split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        String endTime = String.format("%02d:%02d",hour,minute);

        DialogFragment newFragment1 = new TimePick();
        newFragment1.show(getSupportFragmentManager(), endTime);
        Button = 1;
    }

    /**
     * バックキーが押された際にプルダウンメニューから選択した値を設定する処理を追加
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        stintData.setDriver(stintNum,(String)driverSpinner.getSelectedItem());
        stintData.setKartNo(stintNum,(String)kartNoSpinner.getSelectedItem());
        return super.onKeyDown(keyCode,event);
    }

}

