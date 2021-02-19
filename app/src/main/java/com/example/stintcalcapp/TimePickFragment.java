//package com.example.stintcalcapp;
//
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.TimePicker;
//
//import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//
//import java.util.Locale;
//
//public class TimePick extends FragmentActivity
//        implements TimePickerDialog.OnTimeSetListener {
//
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textView = findViewById(R.id.textView);
//    }
//
//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//        String str = String.format(Locale.US, "%d:%d", hourOfDay, minute);
//        textView.setText(str);
//
//    }
//
//    public void showTimePickerDialog(View v) {
////        DialogFragment newFragment = new TimePick();
////        newFragment.show(getSupportFragmentManager(), "timePicker");
//
//    }
//}
