package com.example.mayvamayapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    int manghinhbai[] = {
            R.drawable.c10, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};
    CountDownTimer Timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Kết Quả");

        myDialog.setPositiveButton("Chơi tiếp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Lượt chơi mới", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        myDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        AlertDialog.Builder canhbao = new AlertDialog.Builder(this);
        canhbao.setTitle("Cảnh Báo").setMessage("Chưa nhập thời gian");

        canhbao.setPositiveButton("Đặt Thời Gian", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        canhbao.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //Người
        ImageView iv1 = findViewById(R.id.imageView1);
        ImageView iv2 = findViewById(R.id.imageView2);
        ImageView iv3 = findViewById(R.id.imageView3);
        //Máy
        ImageView iv4 = findViewById(R.id.imageView4);
        ImageView iv5 = findViewById(R.id.imageView5);
        ImageView iv6 = findViewById(R.id.imageView6);
        //Kết Quả
        TextView tv_kqNguoi = findViewById(R.id.textView_KetQua1);
        TextView tv_kqMay = findViewById(R.id.textView_KetQua2);
        //Điểm
        TextView nguoi = findViewById(R.id.Nguoi);
        TextView may = findViewById(R.id.May);
        TextView tg = findViewById(R.id.editText_TimeStart);
        //===============================================
        Button bt_rutbai = findViewById(R.id.button_rutbai);
        bt_rutbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tg.length()==0) {
                    AlertDialog dialog = canhbao.create();
                    dialog.show();
                } else {
                    Timer = new CountDownTimer(Integer.parseInt(tg.getText().toString()) * 1000, 2500) {
                        @Override
                        public void onTick(long l) {
                            int mayPoint = Integer.parseInt(may.getText().toString().trim());
                            int nguoiPoint = Integer.parseInt(nguoi.getText().toString().trim());
                            int[] value = laySauSoNgauNhien(0, 51);
                            //Người
                            iv1.setImageResource(manghinhbai[value[0]]);
                            iv2.setImageResource(manghinhbai[value[2]]);
                            iv3.setImageResource(manghinhbai[value[4]]);
                            int[] valueNguoi = {value[0], value[2], value[4]};
                            tv_kqNguoi.setText(tinhKetQua(valueNguoi));
                            int nutNguoi = tinhSoNut(valueNguoi);
                            //Máy
                            iv4.setImageResource(manghinhbai[value[1]]);
                            iv5.setImageResource(manghinhbai[value[3]]);
                            iv6.setImageResource(manghinhbai[value[5]]);
                            int[] valueMay = {value[1], value[3], value[5]};
                            tv_kqMay.setText(tinhKetQua(valueMay));
                            int nutMay = tinhSoNut(valueMay);

                            if (nutMay > nutNguoi) {
                                mayPoint += 1;
                                may.setText(String.valueOf(mayPoint));
                            } else if (nutMay < nutNguoi) {
                                nguoiPoint += 1;
                                nguoi.setText(String.valueOf(nguoiPoint));
                            }
                        }

                        @Override
                        public void onFinish() {
                            int mayPoint = Integer.parseInt(may.getText().toString().trim());
                            int nguoiPoint = Integer.parseInt(nguoi.getText().toString().trim());
                            if (mayPoint > nguoiPoint) {
                                String mes = " Máy 2 Thắng\n" + " Máy 2  " + mayPoint + " - " + nguoiPoint + "  Máy 1";
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            } else if (nguoiPoint > mayPoint) {
                                String mes = " Máy 1 Thắng\n" + " Máy 1  " + nguoiPoint + " - " + mayPoint + "  Máy 2";
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            } else {
                                String mes = " Hòa Nhau\n" + " Máy 1  " + nguoiPoint + " - " + mayPoint + "  Máy 2";
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            }
                        }
                    }.start();
                }
            }
        });
    }

    //---------------------------------------------------
    private String tinhKetQua(int[] baso){
        String ketQua = "";
        if(tinhSoTay(baso) == 3)
            ketQua = "Kết Quả: 3 Tây";
        else{
            int tong = 0;
            for (int i = 0; i < baso.length; i++) {
                if(baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
                if(tong % 10 == 0)
                    ketQua = "Kết Quả Bù, trong đó có " +tinhSoTay(baso) + " tây.";
                else
                    ketQua = "Kết Quả Là " + (tong % 10) + " nút, trong đó có " + tinhSoTay(baso) + " tây.";
            }
        }
        return ketQua;
    }
    //---------------------------------------------------
    private int tinhSoTay(int[] a){
        int k = 0;
        for(int i = 0; i < a.length; i++)
            if(a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }
    //----------------------------------------------------
    private int[] layBaSoNgauNhien(int min, int max){
        int[] baso = new int[3];
        int i = 0;
        baso[i] = random(min, max);
        do{
            int k = random(min, max);
            if(!kiemTraTrung(k, baso))
                baso[i++] = k;
        }while (i < 3);
        return baso;
    }
    //-----------------------------------------------------
    private int[] laySauSoNgauNhien(int min, int max){
        int[] sauso = new int[6];
        int i = 0;
        sauso[i] = random(min, max);
        do{
            int k = random(min, max);
            if(!kiemTraTrung(k, sauso))
                sauso[i++] = k;
        }while (i < 6);
        return sauso;
    }
    //-----------------------------------------------------
    private boolean kiemTraTrung(int k, int[] a){
        for (int i = 0; i < a.length; i++)
            if(a[i] == k)
                return true;
        return false;
    }
    //------------------------------------------------------
    private int random(int min, int max){
        return min + (int)(Math.random()*((max - min) + 1));
    }
    //-------------------------------------------------------
    private int tinhSoNut(int[] baso) {
        if (tinhSoTay(baso) == 3) {
            return 10;
        } else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                return 0;
            else
                return tong % 10;
        }
    }
}