package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity {
    EditText txtUsuario, txtContrasenia;
    Button btnRegresar, btnRegistrar;
    private String IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_usuario);


        txtUsuario = findViewById(R.id.usuarioR);
        txtContrasenia = findViewById(R.id.contraseniaR);

        btnRegresar = findViewById(R.id.regresar);
        btnRegistrar = findViewById(R.id.registroR);

        IP = getString(R.string.ip_serv);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String contrasenia = txtContrasenia.getText().toString();
                Toast.makeText(getApplicationContext(),usuario, Toast.LENGTH_SHORT);
                registrarUsuario(usuario, contrasenia);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarUsuario.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void registrarUsuario(String usuario, String contrasenia){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, IP+"/login/signup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()){
                            Toast.makeText(getApplicationContext(),"Registro correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrarUsuario.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),"Registro incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("contrasenia", contrasenia);

                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}