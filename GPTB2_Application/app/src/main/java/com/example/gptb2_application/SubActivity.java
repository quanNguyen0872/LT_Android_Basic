package com.example.gptb2_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        EditText et_A = findViewById(R.id.editText_HeSoA);
        EditText et_B = findViewById(R.id.editText_HeSoB);
        EditText et_C = findViewById(R.id.editText_HeSoC);
        Button bt_Submit = findViewById(R.id.button_Submit);
        bt_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                int a = Integer.parseInt(et_A.getText().toString());
                int b = Integer.parseInt(et_B.getText().toString());
                int c = Integer.parseInt(et_C.getText().toString());
                PhuongTrinhBacHai phuongTrinhBacHai = new PhuongTrinhBacHai(a,b,c);
                intent.putExtra("nghiem", phuongTrinhBacHai.nghiemPhuongTrinh());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}