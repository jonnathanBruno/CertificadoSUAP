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

    public String inserirDadoUsuario(String email, String token,String matricula,String nome,String curso,String campus,String situacao,String situacaoSistema){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.EMAIL , email);
        valores.put(CriaBanco.TOKEN, token);
        valores.put(CriaBanco.MATRICULA, matricula);
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.CURSO, curso);
        valores.put(CriaBanco.CAMPUS, campus);
        valores.put(CriaBanco.SITUACAO, situacao);
        valores.put(CriaBanco.SITUACAO_SISTEMA, situacaoSistema);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public String buscarDadoUsuario(String token,Context c){
        Cursor cursor;
        db = banco.getReadableDatabase();
        String selectQuery = "SELECT * FROM usuario";
        cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        String nomeString = cursor.getString(cursor.getColumnIndex(banco.MATRICULA));
        cursor.close();
        
        StringBuilder conversor = new StringBuilder();
        conversor.append(nomeString);
        return conversor.toString();
    }
}
