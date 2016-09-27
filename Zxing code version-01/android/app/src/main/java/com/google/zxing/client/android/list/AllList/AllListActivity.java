package com.google.zxing.client.android.list.AllList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.database.DBmanager;
import com.google.zxing.client.android.domain.ScancodeDomain;

import java.util.ArrayList;


public class AllListActivity extends AppCompatActivity {


    ArrayAdapter<ScancodeDomain> Adapter;

    private ListView m_ListView;
    private  CustomAdapter m_Adapter;
    private Button deleteBtn,delete_datatable_btn;
    private  DBmanager dbmanager;
    ArrayList<ScancodeDomain> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        dbmanager = new DBmanager(this);

        m_ListView = (ListView) findViewById(R.id.list);
        delete_datatable_btn = (Button) findViewById(R.id.delete_datatable_btn);
        deleteBtn = (Button) findViewById(R.id.btn_test);

        arraylist = dbmanager.selectAll_data();

        if(arraylist.size() < 1){
            Toast.makeText(this,"조회 할 리스트가 없습니다.",Toast.LENGTH_SHORT).show();
        }

        m_Adapter = new CustomAdapter(this.getApplicationContext(), arraylist);
        m_ListView.setAdapter(m_Adapter);


        delete_datatable_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager.DeleteDataBase();
                refresh();
            }
        });


    }

    public void refresh(){

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);

    }


    public void refresh_list(){

        arraylist.clear();
        m_Adapter.notifyDataSetChanged();

    }




}








/*



public class AllListActivity extends AppCompatActivity {

    DBmanager dbmanager;
    ArrayAdapter<ScancodeDomain> Adapter;

    private ListView m_ListView;
    private  CustomAdapter m_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);


        dbmanager = new DBmanager(this);

        ArrayList<ScancodeDomain> arraylist = dbmanager.selectAll_data();


      String str =  arraylist.get(0).toString();
        Log.i("메시지",str);

        Adapter = new ArrayAdapter<ScancodeDomain>(this, android.R.layout.simple_list_item_1, arraylist);

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);


        // 새로 정의한 리스너로 객체를 만들어 설정
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = Adapter.getItem(position).getIndex();
                Toast.makeText(getApplicationContext(), "Selected : " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }



}







     */
