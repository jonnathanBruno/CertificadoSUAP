package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity{

    private Login logar;
    private EditText login;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //permissão para acessar internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        logar = new Login();
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void login(View view) throws JSONException {

       Object logou = logar.logar(login.getText().toString() , senha.getText().toString(), this);


        if(logou != "false"){
            Intent segundaTela = new Intent(MainActivity.this, Principal.class);
            startActivity(segundaTela);
        }else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(R.mipmap.ic_launcher);
            alerta.setTitle("Erro Login");
            alerta.setMessage("Login ou senha incorretos!");

            AlertDialog alertDialog = alerta.create();
            alertDialog.show();
        }
    }
}
