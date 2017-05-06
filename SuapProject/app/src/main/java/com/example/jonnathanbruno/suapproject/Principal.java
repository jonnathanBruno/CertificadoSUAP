package com.example.jonnathanbruno.suapproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Principal extends AppCompatActivity {

    private ServerRequest server = new ServerRequest();
    private String token;
    private  BancoController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crud = new BancoController(this,1);

        //permissão para acessar internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params!=null)
        {
            String token = params.getString("token");
            String serviceResult = server.requestWebServiceDados("https://suap.ifrn.edu.br/api/v2/edu/alunos/20131014040145/", token);
            try{
                JSONObject obj = new JSONObject(serviceResult);
                String matricula =  obj.getString("matricula");
                String nome =  obj.getString("nome");
                String curso =  obj.getString("curso");
                String campus = obj.getString("campus");
                String situacao = obj.getString("situacao");
                String situacao_sistemica = obj.getString("situacao_sistemica");

            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "erro", Toast.LENGTH_LONG).show();
            }

        }
    }
}