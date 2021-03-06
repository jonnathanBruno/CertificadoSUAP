package com.example.jonnathanbruno.suapproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotasSemestre extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String token;
    private String login;
    private String anoLetivo;
    private String periodoLetivo;
    private ServerRequest server = new ServerRequest();
    private ListView listaNotas;
    private List<Disciplina> mDisciplinaList;
    private DisciplinaListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_semestre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //permissão para acessar internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //intents
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        token = params.getString("token");
        login = params.getString("login");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sair) {
            this.token = "";
            Intent login = new Intent(NotasSemestre.this, MainActivity.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_usuario) {
            Intent principalTela = new Intent(NotasSemestre.this, Principal.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            principalTela.putExtras(params);
            startActivity(principalTela);

        } else if (id == R.id.nav_notas) {
            Intent notasSemestreTela = new Intent(NotasSemestre.this, NotasSemestre.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            notasSemestreTela.putExtras(params);
            startActivity(notasSemestreTela);

        } else if (id == R.id.nav_faltas) {
            Intent faltasTela = new Intent(NotasSemestre.this, FaltasAluno.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            faltasTela.putExtras(params);
            startActivity(faltasTela);
        } else if (id == R.id.nav_turmas) {
            Intent turmasTela = new Intent(NotasSemestre.this, TurmasVirtuais.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            turmasTela.putExtras(params);
            startActivity(turmasTela);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void notasAluno(View view){
        EditText anoLetivoText = (EditText) findViewById(R.id.anoLetivo);
        EditText periodoLetivoText = (EditText) findViewById(R.id.periodoLetivo);

        anoLetivo = anoLetivoText.getText().toString();
        periodoLetivo = periodoLetivoText.getText().toString();
        mDisciplinaList = new ArrayList<>();

        String serviceResult = server.requestWebServiceNotasAluno("https://suap.ifrn.edu.br/api/v2/minhas-informacoes/boletim/"+anoLetivo+"/"+periodoLetivo+"/", token, login);
        if(serviceResult != null) {
            try {
                JSONArray objArray = new JSONArray(serviceResult);
                for (int i = 0; i < objArray.length(); i++) {
                    JSONObject obj = objArray.getJSONObject(i);
                    mDisciplinaList.add(new Disciplina(obj.getInt("codigo_diario"),
                            obj.getString("disciplina"),
                            obj.getJSONObject("nota_etapa_1").getInt("nota"),
                            obj.getJSONObject("nota_etapa_2").getInt("nota"),
                            obj.getInt("media_final_disciplina"),
                            obj.getString("situacao")));
                }
            } catch (Exception e) {
                Log.d("ERRO", e.toString());
            }

            listaNotas = (ListView) findViewById(R.id.textListaNotas);

            try {
                //Init adapter
                adapter = new DisciplinaListAdapter(this, mDisciplinaList);
                listaNotas.setAdapter(adapter);
                LinearLayout l = (LinearLayout) findViewById(R.id.linearGeralNotas);
                l.setVisibility(View.GONE);

            } catch (Exception e) {
                Log.d("ERRO", e.toString());
            }
        }
    }
}
