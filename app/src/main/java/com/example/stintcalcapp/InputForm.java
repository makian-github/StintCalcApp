package com.example.stintcalcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InputForm extends AppCompatActivity {

    private EditText EditDriverName;
    private StintData stintData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form_activity);

        stintData = (StintData) this.getApplication();
        Button setButton = findViewById(R.id.setButton);
        EditDriverName = findViewById(R.id.edit_driverName);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String driverName = EditDriverName.getText().toString();
                stintData.setDriverName(driverName);
            }
        });
    }
}

