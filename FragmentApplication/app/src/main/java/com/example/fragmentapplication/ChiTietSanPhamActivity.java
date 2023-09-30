package com.example.fragmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_chi_tiet_san_pham);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("Product");

        fragmentThongTinSanPham fragmentThongTinSanPham = (com.example.fragmentapplication.fragmentThongTinSanPham) getFragmentManager().findFragmentById(R.id.fragment_thongtinsanpham);
        fragmentThongTinSanPham.setThongTinSP(product);
//        ImageView iv = findViewById(R.id.imageView_SanPham);
//        TextView tv_tenSP = findViewById(R.id.textView_tenSanPham);
//        TextView tv_donGia = findViewById(R.id.textView_donGiaSP);
//
//        iv.setImageResource(product.getImgSanPham());
//        tv_tenSP.setText(product.getTenSanPham());
//        tv_donGia.setText(product.getDonGia() + " VND");

        Configuration configuration = getResources().getConfiguration();
        if(fragmentThongTinSanPham != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            Intent intents = new Intent(this, MainActivity.class);
            intents.putExtra("Productxx", product);
            startActivity(intents);
        }
    }
}