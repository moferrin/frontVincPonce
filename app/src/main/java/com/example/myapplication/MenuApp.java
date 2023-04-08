package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuApp extends AppCompatActivity {

    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_app);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences preferencias = getSharedPreferences("PrefLogin", Context.MODE_PRIVATE);
                preferencias.edit().clear().commit();
                Intent intent = new Intent(MenuApp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}