package com.example.adminhealthymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainadmin extends AppCompatActivity {
    Button registro, ver, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadmin);
        registro =(Button) findViewById(R.id.btnIngresaresp);
        ver = (Button) findViewById(R.id.btnveresp);
        logout = (Button) findViewById(R.id.btncerrar);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainadmin.this, listaespe.class));
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainadmin.this, registro.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainadmin.this, login.class));
            }
        });
    }
}