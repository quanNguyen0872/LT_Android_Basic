package com.example.ui_demoapplication;

public class NhanVien {
    private String maso;
    private String hoten;
    private String gioitinh;
    private String donvi;
    private byte[] imgNV;

    public NhanVien(String maso, String hoten, String gioitinh, String donvi) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
    }

    public NhanVien(String maso, String hoten, String gioitinh, String donvi, byte[] imgNV) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
        this.imgNV = imgNV;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public byte[] getImgNV() {
        return imgNV;
    }

    public void setImgNV(byte[] imgNV) {
        this.imgNV = imgNV;
    }

    @Override
    public String toString() {
        return "NhanVien{" + maso + ", " + hoten + ", " + gioitinh + ", " + donvi + '}';
    }
}
