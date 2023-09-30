package com.example.ui_demoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {
    ArrayList<NhanVien> nv_list = new ArrayList<>();
    String[] dv_list;
    String donvi;
    ImageView img_Photo;
    Button btn_ChonAnh;
    int position = 0;
    Button btn_Sua;
    Button btn_Xoa;
    NhanVienAdapter nvAdapter;
    SharedPreferences sharedPreferences;
    ListView lv_NhanVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ánh Xạ:
        Button bt_Luu = findViewById(R.id.button_Luu);
        EditText et_MaSo = findViewById(R.id.editText_MaSo);
        EditText et_HoTen = findViewById(R.id.editText_HoTen);
        RadioGroup rg_GioiTinh = findViewById(R.id.radioGroup);
        RadioButton rb_Nam = findViewById(R.id.radioButton_Nam);
        RadioButton rb_Nu = findViewById(R.id.radioButton_Nu);
        Spinner sp_DonVi = findViewById(R.id.spinner_DonVi);
        Button bt_Them = findViewById(R.id.button_Them);
        lv_NhanVien = findViewById(R.id.listView_NhanVien);
        img_Photo = findViewById(R.id.imageView_Photo);
        btn_ChonAnh = findViewById(R.id.button_ChonAnh);
        btn_Sua = findViewById(R.id.button_Sua);
        btn_Xoa = findViewById(R.id.button_Xoa);
        sharedPreferences = getSharedPreferences("DanhSachNhanVien", MODE_PRIVATE);

        loadListNhanVien();

        // Load danh sách Đơn vị vào Spinner Đơn Vị:
        dv_list = getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dv_list);
        sp_DonVi.setAdapter(adapter);
        sp_DonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi = dv_list[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt_Them.setOnClickListener(view -> {
            String maSo =  et_MaSo.getText().toString();
            String hoTen = et_HoTen.getText().toString();
            String gioiTinh = ((RadioButton)findViewById(rg_GioiTinh.getCheckedRadioButtonId())).getText().toString();
            Bitmap bitmap = ((BitmapDrawable)img_Photo.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Tạo một đối tượng Nhân Viên:
            NhanVien nv = new NhanVien(maSo, hoTen, gioiTinh, donvi, byteArray);

            // Thêm nhân viên vào danh sách:
            nv_list.add(nv);

            nvAdapter = new NhanVienAdapter(MainActivity.this, R.layout.listview_nv, nv_list);
            lv_NhanVien.setAdapter(nvAdapter);
            nvAdapter.notifyDataSetChanged();

//            // Đưa danh sách Nhân Viên vào listView:
//            ArrayList<String> listItems = new ArrayList<>();
//            for (NhanVien nv1 :
//                    nv_list) {
//                listItems.add(nv1.toString());
//            }
//            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this,
//                    android.R.layout.simple_list_item_1, android.R.id.text1, listItems);
//            lv_NhanVien.setAdapter(adapter1);
        });

        lv_NhanVien.setOnItemClickListener((adapterView, view, i, l) -> {
            position = i;
            NhanVien nv = nv_list.get(i);
            et_MaSo.setText(nv.getMaso() + "");
            et_HoTen.setText(nv.getHoten());
            // Xử lý Giới tính:
            if(nv.getGioitinh().equals("Nam"))
                rb_Nam.setChecked(true);
            else
                rb_Nu.setChecked(true);
            // Xử lý Đơn vị:
            for(int j = 0; j < dv_list.length; j++)
                if(dv_list[j].equals(nv.getDonvi()))
                    sp_DonVi.setSelection(j);
            Bitmap bitmap = BitmapFactory.decodeByteArray(nv.getImgNV(), 0, nv.getImgNV().length);
            img_Photo.setImageBitmap(bitmap);
        });
        bt_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(nv_list);
                editor.putString("listNhanVien", json);
                editor.commit();
            }
        });

        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv = nv_list.get(position);

                String maSo =  et_MaSo.getText().toString();
                String hoTen = et_HoTen.getText().toString();
                String gioiTinh = ((RadioButton)findViewById(rg_GioiTinh.getCheckedRadioButtonId())).getText().toString();
                Bitmap bitmap = ((BitmapDrawable)img_Photo.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                nv.setMaso(maSo);
                nv.setHoten(hoTen);
                nv.setGioitinh(gioiTinh);
                nv.setDonvi(donvi);
                nv.setImgNV(byteArray);

                nvAdapter.notifyDataSetChanged();

            }
        });

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv_list.remove(position);
                nvAdapter.notifyDataSetChanged();
            }
        });

        btn_ChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });
    }
    private void requestPermissions(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.with(MainActivity.this)
                .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        // here is selected image uri
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            img_Photo.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void loadListNhanVien() {
        if(getList() != null){
            for (NhanVien nv :
                    getList()) {
                nv_list.add(nv);
            }
            nvAdapter = new NhanVienAdapter(MainActivity.this, R.layout.listview_nv, nv_list);
            lv_NhanVien.setAdapter(nvAdapter);
            nvAdapter.notifyDataSetChanged();
        }
    }

    public List<NhanVien> getList() {
        List<NhanVien> arrayItems = null;
        String serializedObject = sharedPreferences.getString("listNhanVien", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<NhanVien>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }
}