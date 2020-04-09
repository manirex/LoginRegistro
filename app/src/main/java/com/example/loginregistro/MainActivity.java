package com.example.loginregistro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registro = (TextView) findViewById(R.id.registroLogin);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        final EditText usuarioT = (EditText)findViewById(R.id.usuarioLogin);
        final EditText claveT = (EditText)findViewById(R.id.claveLogin);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(MainActivity.this,RegistroUsuario.class);
                MainActivity.this.startActivity(registro);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String usuario = usuarioT.getText().toString();
            final String clave = claveT.getText().toString();
            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("success");
                        if(ok == true){
                            String nombre = jsonRespuesta.getString("nombre");
                            int edad = jsonRespuesta.getInt("edad");
                            Intent bienvenido = new Intent(MainActivity.this, Bienvenido.class);
                            bienvenido.putExtra("nombre",nombre);
                            bienvenido.putExtra("edad",edad);

                            MainActivity.this.startActivity(bienvenido);
                            MainActivity.this.finish();

                        }else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                            alerta.setMessage("no se logueo").setNegativeButton("reintentar", null).create().show();
                        }
                    }catch (JSONException e){
                        e.getMessage();
                    }
                }
            };
            }
        });
    }
}
