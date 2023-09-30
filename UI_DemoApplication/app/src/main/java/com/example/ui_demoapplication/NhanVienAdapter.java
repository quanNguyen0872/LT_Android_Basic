package com.example.ui_demoapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NhanVien> listNV;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> listNV) {
        this.context = context;
        this.layout = layout;
        this.listNV = listNV;
    }


    @Override
    public int getCount() {
        return listNV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);
        // anh xa:
        ImageView iv = view.findViewById(R.id.imageView_NhanVien);
        TextView tv_maso = view.findViewById(R.id.textView_MaSo);
        TextView tv_hoten = view.findViewById(R.id.textView_HoTen);
        TextView tv_gioitinh = view.findViewById(R.id.textView_GioiTinh);
        TextView tv_donvi = view.findViewById(R.id.textView_DonVi);

        NhanVien nv = listNV.get(i);
        tv_maso.setText("Mã Số: " + nv.getMaso());
        tv_hoten.setText("Họ Tên: " + nv.getHoten());
        tv_gioitinh.setText("Giới Tính: " + nv.getGioitinh());
        tv_donvi.setText("Đơn Vị: " + nv.getDonvi());
        Bitmap bitmap = BitmapFactory.decodeByteArray(nv.getImgNV(), 0, nv.getImgNV().length);
        iv.setImageBitmap(bitmap);
        return view;
    }
}
