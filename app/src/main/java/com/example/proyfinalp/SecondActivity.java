package com.example.proyfinalp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {

    private ImageButton btnRastreo;
    private ImageButton btnInfo;
    private ImageButton btnComu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnRastreo = (ImageButton) findViewById(R.id.buttonRastreo);
        btnInfo= (ImageButton) findViewById(R.id.buttonInfo);
        btnComu = (ImageButton) findViewById(R.id.buttonContacto);

        btnRastreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SecondActivity.this, FourActivity.class);
                startActivity(intent);
            }
        });

        btnComu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SecondActivity.this, FiveActivity.class);
                startActivity(intent);
            }
        });

    }
}
