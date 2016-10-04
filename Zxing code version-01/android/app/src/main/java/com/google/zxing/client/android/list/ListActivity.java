package com.google.zxing.client.android.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.database.DBmanager;
import com.google.zxing.client.android.domain.ScancodeDomain;
import com.google.zxing.client.android.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    private ScancodeDomain scancodeDomain;
    private TextView BeaconTextView, BarcodeTextView;
    public String barcode,Beacon;
    private Button saveButton,cancelButton;

    private DBmanager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        scancodeDomain = new ScancodeDomain();
        dbmanager = new DBmanager(this); // 지금 이순간 onCreate 발동 데이터베이스, 테이블 을 생성.

        Intent intent = getIntent();
        barcode = (String) intent.getSerializableExtra("barcode");
        Beacon = (String) intent.getSerializableExtra("Beacon");

        /*Toast.makeText(getApplicationContext(),barcode+"==="+Beacon,Toast.LENGTH_SHORT).show();*/

        scancodeDomain.setBarcode(barcode);
        scancodeDomain.setBeacon(Beacon);

        BeaconTextView = (TextView) findViewById(R.id.BeaconTextInfo);
        BarcodeTextView = (TextView) findViewById(R.id.BarcodeTextInfo);

        BeaconTextView.setText(Beacon);
        BarcodeTextView.setText(barcode);


        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Scantime;
                //파일 이름 :날짜_시간
                Date day = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
                Scantime = String.valueOf(sdf.format(day));
                scancodeDomain.setScantime(Scantime);

                //     Toast.makeText(getApplicationContext(),"저장버튼"+barcode+"==="+Beacon,Toast.LENGTH_SHORT).show();
                dbmanager.insertData(scancodeDomain);
                moveCaptureActivity();
                finish();

            }
        });


        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"취소버튼",Toast.LENGTH_SHORT).show();
                moveCaptureActivity();
                finish();
            }
        });



    }


    private void moveCaptureActivity(){

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                MainActivity.class); // 다음 넘어갈 클래스 지정
        startActivity(intent); // 다음 화면으로 넘어간다

    }


}








