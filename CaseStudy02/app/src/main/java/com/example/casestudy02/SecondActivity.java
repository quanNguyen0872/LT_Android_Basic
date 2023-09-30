package com.example.casestudy02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String hoTen = getIntent().getExtras().getString("ht");
        String namSinh = getIntent().getExtras().getString("ns");
        String st = "";
        st = "Họ và Tên: " + hoTen;
        st = st + " Năm Sinh: " + namSinh;

        TextView tv_KetQua = findViewById(R.id.textView_KetQua);
        tv_KetQua.setText(st);
    }
}