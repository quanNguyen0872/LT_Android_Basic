package com.example.bt1_th_tuan_2_3application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NhanVien> arrNhanVien = null;
    MyArrayAdapter adapter = null;
    ListView lvNhanVien = null;
    Button btnNhap;
    Button btnRemoveAll;
    EditText txtMa, txtTen;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ánh xạ:
        btnNhap = findViewById(R.id.button_nhapNV);
        btnRemoveAll = findViewById(R.id.button_Xoa);
        txtMa = findViewById(R.id.editText_maNV);
        txtTen = findViewById(R.id.editText_tenNV);
        genderGroup = findViewById(R.id.radioGroup);

        lvNhanVien = findViewById(R.id.listView_NV);
        arrNhanVien = new ArrayList<NhanVien>();
        adapter = new MyArrayAdapter(MainActivity.this, R.layout.my_item_layout, arrNhanVien);
        lvNhanVien.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyNhap();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa();
            }
        });
    }

    public void xuLyNhap()
    {
        String ma = txtMa.getText() + "";
        String ten = txtTen.getText() + "";
        boolean gioitinh = false;
        if(genderGroup.getCheckedRadioButtonId() == R.id.radioButton_Nam)
        {
            gioitinh = true;
        }
        NhanVien nv = new NhanVien();
        nv.setId(ma);
        nv.setName(ten);
        nv.setGender(gioitinh);

        arrNhanVien.add(nv);
        adapter.notifyDataSetChanged();

        txtMa.setText("");
        txtTen.setText("");
        txtMa.requestFocus();
    }

    public void xuLyXoa()
    {
        for (int i = lvNhanVien.getChildCount() - 1; i >= 0; i--) {
            View v = lvNhanVien.getChildAt(i);
            CheckBox chk = (CheckBox) v.findViewById(R.id.chk_item);
            if(chk.isChecked())
            {
                arrNhanVien.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }
}