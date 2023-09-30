package com.example.fragmentapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private String tenSanPham;
    private double donGia;
    private int imgSanPham;

    public Product(String tenSanPham, double donGia, int imgSanPham) {
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.imgSanPham = imgSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getImgSanPham() {
        return imgSanPham;
    }

    public void setImgSanPham(int imgSanPham) {
        this.imgSanPham = imgSanPham;
    }

    @Override
    public String toString() {
        return "Product{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", donGia=" + donGia +
                ", imgSanPham=" + imgSanPham +
                '}';
    }
}
