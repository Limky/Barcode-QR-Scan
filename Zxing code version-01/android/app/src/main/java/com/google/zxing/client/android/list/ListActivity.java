package com.google.zxing.client.android.list;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.database.DBmanager;
import com.google.zxing.client.android.domain.ScancodeDomain;

public class ListActivity extends AppCompatActivity {

    private ScancodeDomain scancodeDomain;
    private TextView QRcodeTextView, BarcodeTextView;
    private SQLiteDatabase db;
    public String DB_NAME = "ScanDataBase";
    public int DB_MODE = Context.MODE_PRIVATE;
    public String TABLE_NAME = "scanTable";
    public String barcode,QRcode;
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
        QRcode = (String) intent.getSerializableExtra("QRcode");

        /*Toast.makeText(getApplicationContext(),barcode+"==="+QRcode,Toast.LENGTH_SHORT).show();*/

        scancodeDomain.setBarcode(barcode);
        scancodeDomain.setQRcode(QRcode);

        QRcodeTextView = (TextView) findViewById(R.id.QRcodeTextInfo);
        BarcodeTextView = (TextView) findViewById(R.id.BarcodeTextInfo);

        QRcodeTextView.setText(QRcode);
        BarcodeTextView.setText(barcode);


        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(getApplicationContext(),"저장버튼"+barcode+"==="+QRcode,Toast.LENGTH_SHORT).show();
                dbmanager.insertData(scancodeDomain);
                moveCaptureActivity();

            }
        });


        cancelButton = (Button)findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"취소버튼",Toast.LENGTH_SHORT).show();
                moveCaptureActivity();
            }
        });



    }


    private void moveCaptureActivity(){

        Intent intent = new Intent(
                getApplicationContext(), // 현재 화면의 제어권자
                CaptureActivity.class); // 다음 넘어갈 클래스 지정
        startActivity(intent); // 다음 화면으로 넘어간다

    }


}








