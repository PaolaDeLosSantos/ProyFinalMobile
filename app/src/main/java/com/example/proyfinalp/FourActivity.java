package com.example.proyfinalp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class FourActivity extends AppCompatActivity {

    private ImageButton imgBtnCamara;
    private EditText editTextTextPersonName;

    private final int PICTURE_FROM_CAMERA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        imgBtnCamara = (ImageButton) findViewById(R.id.imageCamera);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);

        //Boton para el navegador web
        imgBtnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrir camara
                Intent intentCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamara,PICTURE_FROM_CAMERA);
            }
        });

    }

    @Override

    protected void onActivityResult ( int requestCode, int resultCode, Intent data){

        switch (requestCode) {
            case PICTURE_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    //String resultado = data.toUri(0);
                    editTextTextPersonName.setText("Foto Tomada con éxito");
                } else {
                    editTextTextPersonName.setText("Foto no tomada");
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}