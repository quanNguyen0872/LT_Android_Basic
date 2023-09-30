package com.example.gptb2_application;

public class PhuongTrinhBacHai {
    private int a;
    private int b;
    private int c;

    PhuongTrinhBacHai(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public String nghiemPhuongTrinh(){
        String st = "";
        float denta = b*b - 4*a*c;
        if(denta < 0)
            st = "Phương trình vô nghiệm";
        else if(denta == 0)
            st = "Phương trình có nghiệm kép x = " + (-b*1.0/(2*a));
        else{
            st = "Phương trình có 2 nghiệm phân biệt: ";
            st += "\n x1 = " +  (-b + Math.sqrt(denta))/(2*a);
            st += "\n x2 = " +  (-b - Math.sqrt(denta))/(2*a);
        }
        return st;
    }
}
