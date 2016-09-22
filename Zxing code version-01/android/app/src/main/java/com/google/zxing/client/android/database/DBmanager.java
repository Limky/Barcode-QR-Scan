package com.google.zxing.client.android.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.zxing.client.android.domain.ScancodeDomain;

import java.util.ArrayList;

/**
 * Created by SQISOFT on 2016-09-21.
 */
public class DBmanager {

    private ScancodeDomain scancodeDomain;
    private Context context;

    public static final String DB_NAME = "ScanDataBase";
    public int DB_MODE = Context.MODE_PRIVATE;
    public static final String TABLE_NAME = "scanTable";
    public static final int dbVersion = 1;

    public String barcode, QRcode;

    // DB관련 객체 선언
    private OpenHelper opener; // DB opener
    private SQLiteDatabase db; // DB controller

    public DBmanager(Context context) {
        this.context = context;

        this.opener = new OpenHelper(context, DB_NAME, null, dbVersion);
        db = opener.getWritableDatabase();

    }


    // Opener of DB and Table
    private class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

            super(context, name, null, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        // 생성된 DB가 없을 경우에 한번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase db) {
            // String dropSql = "drop table if exists " + tableName;
            // db.execSQL(dropSql);

            String createTableSQL = "create table " + TABLE_NAME + "(id integer primary key autoincrement," + "QRcode text not null," + "Barcode text not null)";
            try {
                db.execSQL(createTableSQL);
                Log.i("databasetest", TABLE_NAME + "***************=================Table create***************=================");
            } catch (SQLException e) {

            }
        }

    }


    // 데이터 추가
    public void insertData(ScancodeDomain scancodeDomain) {
        String sql = "insert into " + TABLE_NAME + " " + "(QRcode, Barcode) values( '" + scancodeDomain.getQRcode() + "', '" + scancodeDomain.getBarcode() +"');";
        Log.i("database test", " ***************=================Data Value Insert***************=================");
        db.execSQL(sql);
    }


    // 모든 Data 읽기
    public void selectAll(){
        Log.i("database test", " ***************=================데이터를 조회만 했다.***************=================");
        String sql = "select * from " + TABLE_NAME + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();

        while(!results.isAfterLast()){
            int id = results.getInt(0);
            String DB_QRcode = results.getString(1);
            String DB_Barcode = results.getString(2);
            //   Toast.makeText(this, "index= "+id+" QRcode="+DB_QRcode+"Barcode="+DB_Barcode, Toast.LENGTH_LONG).show();
            results.moveToNext();
        }
        results.close();
    }


    public ArrayList<ScancodeDomain> selectAll_data(){

        Log.i("database test", " ***************=================Data 직접 가져와서 리스트에 담았다.***************=================");

        String selectAllSQL = "select * from" + TABLE_NAME + ";";
        Cursor results = db.rawQuery(selectAllSQL,null);

        results.moveToFirst();
        ArrayList<ScancodeDomain> infos = new ArrayList<ScancodeDomain>();

        while(!results.isAfterLast()){

            ScancodeDomain scancodeDomain = new ScancodeDomain(results.getInt(0), results.getString(1), results.getString(2));
            infos.add(scancodeDomain);
            results.moveToNext();

        }

        results.close();
        return infos;
    }


}