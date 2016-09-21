package com.google.zxing.client.android.list;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.domain.ScancodeDomain;

public class ListActivity extends AppCompatActivity {

    private ScancodeDomain scancodeDomain;
    private TextView QRcodeTextView, BarcodeTextView;
    private SQLiteDatabase db;
    public String DB_NAME = "ScanDataBase";
    public int DB_MODE = Context.MODE_PRIVATE;
    public String TABLE_NAME = "scanTable";
    public String barcode,QRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        scancodeDomain = new ScancodeDomain();

        Intent intent = getIntent();
        barcode = (String) intent.getSerializableExtra("barcode");
        QRcode = (String) intent.getSerializableExtra("QRcode");

        /*Toast.makeText(getApplicationContext(),barcode+"==="+QRcode,Toast.LENGTH_SHORT).show();*/
/*
        scancodeDomain.setBarcode(barcode);
        scancodeDomain.setQRcode(QRcode);*/

        QRcodeTextView = (TextView) findViewById(R.id.QRcodeTextInfo);
        BarcodeTextView = (TextView) findViewById(R.id.BarcodeTextInfo);

        QRcodeTextView.setText(QRcode);
        BarcodeTextView.setText(barcode);

        createDataBase();
        createTable();
        insertRecord();

        selectAll();

    }

    public void createDataBase() {
        db = openOrCreateDatabase(DB_NAME, DB_MODE, null);
        Log.i("database test", DB_NAME + "***************=================Database create=================***************");
    }

    public void createTable() {
        String sql = "create table " + TABLE_NAME + "(id integer primary key autoincrement," + "QRcode text not null," + "Barcode text not null)";
        try {
            db.execSQL(sql);
            Log.i("databasetest", TABLE_NAME + "***************=================Table create***************=================");
        } catch (SQLException e) {

        }
    }

    public void deleteTable(){
        String sql = "drop table " + TABLE_NAME;
        try{
            db.execSQL(sql);
            Log.i("database test", "Table delete");
        }catch(SQLException e){
        }
    }

    public void insertRecord(){

        String sql = "insert into " + TABLE_NAME + " " + "(QRcode, Barcode) values( '" + QRcode + "', '" + barcode +"');";
        try{
            db.execSQL(sql);
            Log.i("database test", " ***************=================Data Value Insert***************=================");
        }catch(SQLException e){
        }
    }


    // 모든 Data 읽기
    public void selectAll(){
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();

        while(!results.isAfterLast()){
            int id = results.getInt(0);
            String DB_QRcode = results.getString(1);
            String DB_Barcode = results.getString(2);
            Toast.makeText(this, "index= "+id+" QRcode="+DB_QRcode+"Barcode="+DB_Barcode, Toast.LENGTH_LONG).show();
            results.moveToNext();
        }
        results.close();
    }



}








