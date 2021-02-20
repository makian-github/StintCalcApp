package com.example.stintcalcapp;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

public class SampleFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private StintData stintData;

    public static SampleFragment newInstance(String str){
        // Fragemnt01 インスタンス生成
        SampleFragment fragment = new SampleFragment ();
        // Bundle にパラメータを設定
        Bundle barg = new Bundle();
        barg.putString("Message", str);
        fragment.setArguments(barg);

        return fragment;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args != null ){
            String str = args.getString("Message");
            TextView textView = view.findViewById(R.id.text_fragment);
            textView.setText(str);
        }
        showTimePickerDialog(view);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String str = String.format(Locale.US, "%d:%d", hourOfDay, minute);
//        EditDriverName.setText(str);
//        stintData.setDriverName(str);
//        stintData.setStartTime(stintNum,str);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePick();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }
}