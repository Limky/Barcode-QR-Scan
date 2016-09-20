package com.google.zxing.client.android.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.domain.ScancodeDomain;

public class ListActivity extends AppCompatActivity {

    private ScancodeDomain scancodeDomain;
    private TextView QRcodeTextView,BarcodeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String barcode = (String) intent.getSerializableExtra("barcode");
        String QRcode = (String) intent.getSerializableExtra("QRcode");

        /*Toast.makeText(getApplicationContext(),barcode+"==="+QRcode,Toast.LENGTH_SHORT).show();*/

        QRcodeTextView  = (TextView) findViewById(R.id.QRcodeTextInfo);
        BarcodeTextView = (TextView) findViewById(R.id.BarcodeTextInfo);

        QRcodeTextView.setText(QRcode);
        BarcodeTextView.setText(barcode);

    }

}
