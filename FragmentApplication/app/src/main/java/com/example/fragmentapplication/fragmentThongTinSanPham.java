package com.example.fragmentapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class fragmentThongTinSanPham extends Fragment {
    ImageView iv;
    TextView tv_tenSP, tv_donGia;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinsanpham, container, false);
        //anh xa:
        iv = view.findViewById(R.id.imageView_thongtinsanpham);
        tv_tenSP = view.findViewById(R.id.textView_tenThongTinSanPham);
        tv_donGia = view.findViewById(R.id.textView_donGiaThongTinSanPham);


        return view;
    }

    public void setThongTinSP(Product product){
        iv.setImageResource(product.getImgSanPham());
        tv_tenSP.setText(product.getTenSanPham());
        tv_donGia.setText(product.getDonGia() + " VND");
    }
}
