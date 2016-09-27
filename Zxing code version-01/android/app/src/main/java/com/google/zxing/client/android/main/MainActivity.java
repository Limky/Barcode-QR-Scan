package com.google.zxing.client.android.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.list.AllList.AllListActivity;

public class MainActivity extends AppCompatActivity {

    Button ScanButton , ListButton;
    long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanButton = (Button)findViewById(R.id.ScanButton);
        ListButton = (Button)findViewById(R.id.ListButton);


        ScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"스캔 버튼 클릭.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        CaptureActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });



        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"리스트 버튼 클릭.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        AllListActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });



    }


    @Override
    public void onBackPressed(){
        //  Toast.makeText(getApplicationContext(), "back button click", Toast.LENGTH_SHORT).show();
        int count = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            count = getFragmentManager().getBackStackEntryCount();
        }
        Log.d("count","count = "+count);
        backButtonFunction();

    }

    public void backButtonFunction(){

        Activity activity = MainActivity.this;


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            int count = getFragmentManager().getBackStackEntryCount();
        }

        //currentTimeMillis 현재시간이 버튼을 눌린 시간 + 2초 보다 흘럿다면 2초내 클릭 안한것임.
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis(); //backKeyPressedTime 버튼을 누른 시간을 입력
            toast = Toast.makeText(getApplicationContext(), "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            super.onBackPressed();
            return;
        }

        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finish();
            toast.cancel();
        }


    }


}
