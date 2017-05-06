package com.example.jonnathanbruno.suapproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context, int version){
        banco = new CriaBanco(context, version);
    }

    public String insereDado(String email, String senha, String token){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.EMAIL , email);
        valores.put(CriaBanco.SENHA, senha);
        valores.put(CriaBanco.TOKEN, token);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public String buscaDado(){
        Cursor cursor;
        db = banco.getReadableDatabase();
        String selectQuery = "SELECT * FROM usuario WHERE email='20131014040145'";
        cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        String nomeString = cursor.getString(cursor.getColumnIndex(banco.TOKEN));

        StringBuilder conversor = new StringBuilder();
        conversor.append(nomeString);
        return conversor.toString();
    }
}
