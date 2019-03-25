package com.example.peticiones;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txtnombre;
    String nombre = "";
    Button btnenviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        btnenviar = findViewById(R.id.btnenviar);
        nombre = txtnombre.getText().toString();

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject datos = new JSONObject();
                try {
                    datos.put("Nombre", txtnombre.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jar = new JsonObjectRequest(Request.Method.POST, "http://nuevo.rnrsiilge-org.mx/nombre", datos,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {

                                    //Gson gson = new Gson();
                                    //Log.d("Cadena", response.toString());
                                    //Type type = new TypeToken<List<Persona>>(){}.getType(); //construyendo un tipo de lista // crea un tipo de lista de personas
                                    //List<Persona> lp  = gson.fromJson(response.toString(), type);

                                    Toast.makeText(MainActivity.this,  response.toString()  , Toast.LENGTH_SHORT).show();

                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jar);
            }
        });
    }
}
