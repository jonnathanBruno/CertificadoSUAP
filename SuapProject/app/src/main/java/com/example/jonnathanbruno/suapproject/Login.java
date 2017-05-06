package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.content.ContextCompat.startActivity;

public class Login {

    private String login;
    private String senha;
    private String token;
    private ServerRequest server = new ServerRequest();

    public Object logar(String login, String senha, Context c) throws JSONException {

        this.login = login;
        this.senha =  senha;


        JSONObject serviceResult = server.requestWebServiceLogin("https://suap.ifrn.edu.br/api/v2/autenticacao/token/",login,senha);
        if(serviceResult != null){
            return this.token = (String) serviceResult.get("token");
        }else return this.token = "false";
    }
}