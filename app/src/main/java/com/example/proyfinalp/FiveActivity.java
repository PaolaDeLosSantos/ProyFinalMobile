package com.example.proyfinalp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class FiveActivity extends AppCompatActivity {

    private ImageButton imgBtnTe1;
    private ImageButton imgBtnEmail;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        //mensaje de email

        imgBtnEmail  = (ImageButton) findViewById(R.id.imageEmail);
        //hasta aqui

        imgBtnTe1 = (ImageButton) findViewById(R.id.imageTel);

        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = "fasttransportation@gmail.com";

                Intent intentEmail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+correo));
                startActivity(intentEmail);
            }
        });

        imgBtnTe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NumTe1 = "2211405229";
                if (NumTe1 != null) {
                    //Comprobar version de android donde se ejecuta la aplicacion
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //MEJORA, verificamos si el usuario ya concedio el permiso de la llamada,no lo ha concedido, o nuncase le ha preguntado
                        if (VerificarPermiso(Manifest.permission.CALL_PHONE)) {
                            //Se acepto el permiso
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + NumTe1));
                            startActivity(i);
                        } else {
                            //No se acepto el permiso o no se ha realizado la solicitud
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                //No se ha preguntado aun
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            } else {
                                //No se ha aceptado
                                Toast.makeText(FiveActivity.this, "Por favor activa el permiso requerido", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }


                        //requestPermissios(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);
                    } else {
                        VersionesAntiguas(NumTe1);
                    }
                }
            }

            private void VersionesAntiguas(String NumTe1) {
                Intent intentLlama = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + NumTe1)); //Para realizar una llamada telefónica directamente,
                if (VerificarPermiso(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentLlama);
                } else {
                    Toast.makeText(FiveActivity.this, "Declinaste el permiso de Llamada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private boolean VerificarPermiso (String permiso){
        int resultado = this.checkCallingOrSelfPermission(permiso);
        return resultado == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults ){
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permiso = permissions[0];
                int resultado = grantResults[0];

                if (permiso.equals(Manifest.permission.CALL_PHONE)) {
                    if (resultado == PackageManager.PERMISSION_GRANTED) {
                        String NumTe1 = "2211405229";
                        Intent intentLlama = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + NumTe1));
                        startActivity(intentLlama);
                    } else {
                        Toast.makeText(this, "permiso de llamada no concedido", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


}