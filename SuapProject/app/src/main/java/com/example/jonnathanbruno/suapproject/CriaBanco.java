package com.example.jonnathanbruno.suapproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bancoSUAP.db";
    protected static final String TABELA = "usuario";
    protected static final String ID = "_id";
    protected static final String EMAIL = "email";
    protected static final String SENHA = "senha";
    protected static final String TOKEN = "token";
    private static final int VERSAO = 1;

    public CriaBanco(Context context, int version) {
        super(context, NOME_BANCO, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"( "
                + ID + " integer primary key autoincrement, "
                + EMAIL + " text, "
                + SENHA + " text, "
                + TOKEN + " text "
                +" ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
