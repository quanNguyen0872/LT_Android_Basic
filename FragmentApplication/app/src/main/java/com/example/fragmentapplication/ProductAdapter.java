package com.example.fragmentapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> listProduts;

    public ProductAdapter(Context context, int layout, List<Product> listProduts) {
        this.context = context;
        this.layout = layout;
        this.listProduts = listProduts;
    }

    @Override
    public int getCount() {
        return listProduts.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view  = inflater.inflate(layout, null);

        // anh xa:
        ImageView iv = view.findViewById(R.id.imageView_SanPham);
        TextView tv_tenSP = view.findViewById(R.id.textView_tenSanPham);
        TextView tv_donGia = view.findViewById(R.id.textView_donGiaSP);

        Product product = listProduts.get(i);
        iv.setImageResource(product.getImgSanPham());
        tv_tenSP.setText(product.getTenSanPham());
        tv_donGia.setText(String.valueOf(product.getDonGia()));

        return  view;

    }
}
