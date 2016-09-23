package com.google.zxing.client.android.list.AllList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.database.DBmanager;
import com.google.zxing.client.android.domain.ScancodeDomain;

import java.util.ArrayList;

/**
 * Created by SQISOFT on 2016-09-23.
 */
public class CustomAdapter extends BaseAdapter {

    private DBmanager dbmanager;
    // 문자열을 보관 할 ArrayList
    private ArrayList<ScancodeDomain> ScancodeDomain_List;

    // 생성자
    public CustomAdapter() {

        ScancodeDomain_List = new ArrayList<ScancodeDomain>();
        dbmanager = new DBmanager();
    }

    @Override
    public int getCount() {
        return ScancodeDomain_List.size();
    }

    @Override
    public Object getItem(int position) {
        return ScancodeDomain_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem, parent, false);

            // TextView에 현재 position의 인덱스 추가
            TextView index = (TextView) convertView.findViewById(R.id.index);
            //ScancodeDomain_List.get(position).getIndex()
            index.setText("Num = "+Integer.toString(ScancodeDomain_List.get(position).getIndex()));

            // TextView에 현재 position의 문자열 추가
            TextView text1 = (TextView) convertView.findViewById(R.id.text1);
            text1.setText("QRcode = "+ScancodeDomain_List.get(position).getQRcode());

            // TextView에 현재 position의 문자열 추가
            TextView text2 = (TextView) convertView.findViewById(R.id.text2);
            text2.setText("Barcode = "+ScancodeDomain_List.get(position).getBarcode());

            // 버튼을 터치 했을 때 이벤트 발생
            Button btn = (Button) convertView.findViewById(R.id.btn_test);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "Selected : " + pos, Toast.LENGTH_SHORT).show();
             /*       dbmanager.DeleteTableRow(Integer.toString(ScancodeDomain_List.get(pos).getIndex()-1));*/


                }
            });


            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 클릭 : "+ScancodeDomain_List.get(pos).toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        return convertView;
    }


    public void add(ScancodeDomain scancodeDomain_List){
        ScancodeDomain_List.add(scancodeDomain_List);
    }


}



