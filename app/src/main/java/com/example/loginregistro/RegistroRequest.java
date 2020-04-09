package com.example.loginregistro;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {
        private static final String ruta = "http://rutasmazatlan.000webhostapp.com/insertar_producto.php";
        private Map<String, String> parametros;
        public RegistroRequest(String nombre, String usuario, String clave, int edad, Response.Listener<String> listener){
            super(Request.Method.POST, ruta, listener, null);
            parametros = new HashMap<>();
            parametros.put("usuario", usuario+"");
            parametros.put("nombre", nombre+"");
            parametros.put("clave", clave+"");
            parametros.put("edad", edad+"");

        }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
