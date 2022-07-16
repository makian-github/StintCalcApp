package com.example.stintcalcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class qrCreateActivity extends AppCompatActivity {

    TextView resultText;

    private SharedPreferences dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_activity);

        Button createBtn = findViewById(R.id.creatButton);
        Button readBtn = findViewById(R.id.readButton);

        resultText = findViewById(R.id.result);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
                String str = dataStore.getString("input", "Nothing");
                if(!str.equals("Nothing")) {
                    resultText.setText(str);
                    Log.v("qrTest",":" + str);
                }
                //QRコード化する文字列
                String data = str;
                //QRコード画像の大きさを指定(pixel)
                int size = 800;

                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

                    HashMap hints = new HashMap();

                    //文字コードの指定
                    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

                    //誤り訂正レベルを指定
                    //L 7%が復元可能
                    //M 15%が復元可能
                    //Q 25%が復元可能
                    //H 30%が復元可能
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

                    //QRコードのバージョンを指定
                    hints.put(EncodeHintType.QR_VERSION, 20);

                    //QRコードをBitmapで作成
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(data,BarcodeFormat.QR_CODE, size, size);

                    //作成したQRコードを画面上に配置
                    ImageView imageViewQrCode = (ImageView) findViewById(R.id.imageView);
                    imageViewQrCode.setImageBitmap(bitmap);

                } catch (Exception e) {
                    throw new AndroidRuntimeException("Barcode Error.", e);
                }
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(qrCreateActivity.this).initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            Log.d("readQR", result.getContents());
            resultText.setText(result.getContents());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}