package com.example.jonnathanbruno.suapproject;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.json.JSONException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


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

       Object token = logar.logar(login.getText().toString() , senha.getText().toString(), this);

        if(token != "false"){
            Intent segundaTela = new Intent(MainActivity.this, Principal.class);
            Bundle params = new Bundle();
            params.putString("token", (String) token);
            params.putString("login", login.getText().toString());
            segundaTela.putExtras(params);
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
