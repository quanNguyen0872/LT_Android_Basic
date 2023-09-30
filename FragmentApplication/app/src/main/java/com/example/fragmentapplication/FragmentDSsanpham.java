package com.example.fragmentapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FragmentDSsanpham extends Fragment {
    List<Product> listProduct = new ArrayList<>();
    ProductAdapter productAdapter;
    GridView gridView;
    ProductInterface productInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhsachsanpham, container, false);
        gridView = view.findViewById(R.id.grid_fragment);
        themMang();
        productAdapter = new ProductAdapter(getActivity(), R.layout.layoutsanpham, listProduct);
        gridView.setAdapter(productAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productInterface = (ProductInterface) getActivity();
                productInterface.sentData(listProduct.get(i));
            }
        });
        return view;
    }


    public void themMang() {
        listProduct.add(new Product("Galaxy S22", 234999, R.drawable.galaxys22));
        listProduct.add(new Product("Iphone 11 64gb", 178992, R.drawable.iphone11));
        listProduct.add(new Product("Oppo Reno 7", 126874, R.drawable.opporeno7));
        listProduct.add(new Product("Xaomi 11t", 143612, R.drawable.xaomi11t));
        listProduct.add(new Product("Iphone se 2022", 109999, R.drawable.iphonese));
        listProduct.add(new Product("Galaxy A53", 115995, R.drawable.galaxya53));
    }
}
