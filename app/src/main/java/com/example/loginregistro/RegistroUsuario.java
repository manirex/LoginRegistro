package com.example.loginregistro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
       final EditText nombreT       = (EditText)findViewById(R.id.nombreRegistro);
        final EditText usuarioT     = (EditText)findViewById(R.id.usuarioRegistro);
        final EditText claveT       = (EditText)findViewById(R.id.claveRegistro);
        final EditText edadT        = (EditText)findViewById(R.id.edtEdad);
        Button btnRegistro          = (Button)findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre     = nombreT.getText().toString();
                String usuario    = usuarioT.getText().toString();
                String clave      = claveT.getText().toString();
                int    edad       = Integer.parseInt(edadT.getText().toString());
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                                try{
                                    JSONObject jsonRespuesta = new JSONObject(response);
                                    boolean ok = jsonRespuesta.getBoolean("success");
                                    if (ok == true){
                                        Intent i = new Intent(RegistroUsuario.this, MainActivity.class);
                                        RegistroUsuario.this.startActivity(i);
                                        RegistroUsuario.this.finish();
                                    }else {
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(RegistroUsuario.this);
                                        alerta.setMessage("fallo en el registro").setNegativeButton("reintentar", null).create().show();
                                    }
                                }catch (JSONException e){
                                    e.getMessage();
                                }
                    }
                };
                 RegistroRequest r = new RegistroRequest(nombre,usuario,clave,edad,respuesta);
                RequestQueue cola = Volley.newRequestQueue(RegistroUsuario.this);
                cola.add(r);
            }
        });

    }
}
