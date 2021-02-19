package com.example.stintcalcapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class InputForm extends AppCompatActivity {

    private EditText EditDriverName;
    private StintData stintData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form_activity);

        stintData = (StintData) this.getApplication();
        Button setButton = findViewById(R.id.setButton);
        Button timeSetButton = findViewById(R.id.setButton1);
        EditDriverName = findViewById(R.id.edit_driverName);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String driverName = EditDriverName.getText().toString();
                stintData.setDriverName(driverName);
            }
        });

        timeSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    fragmentTransaction.replace(R.id.container,
                            SampleFragment.newInstance("Fragment"));
                    fragmentTransaction.commit();
            }
        });
    }

}

