package com.google.zxing.client.android.list.AllList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.database.DBmanager;
import com.google.zxing.client.android.domain.ScancodeDomain;

import java.util.ArrayList;


public class AllListActivity extends AppCompatActivity {

    DBmanager dbmanager;
    ArrayAdapter<ScancodeDomain> Adapter;

    private ListView m_ListView;
    private  CustomAdapter m_Adapter;
    private Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        m_Adapter = new CustomAdapter();
        m_ListView = (ListView) findViewById(R.id.list);
        m_ListView.setAdapter(m_Adapter);


        dbmanager = new DBmanager(this);
        ArrayList<ScancodeDomain> arraylist = dbmanager.selectAll_data();

        if(arraylist == null){
            Toast.makeText(this,"조회 할 리스트가 없습니다.",Toast.LENGTH_SHORT).show();

        }else {
            for (int i = 0; i < arraylist.size(); i++)
                m_Adapter.add(arraylist.get(i));
        }




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
