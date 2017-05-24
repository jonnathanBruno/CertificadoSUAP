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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TurmasVirtuais extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String codTurma;
    private TextView componenteCurricular;
    private String token;
    private String login;
    private ServerRequest server = new ServerRequest();
    private ListView alunos;
    private List<Aluno> mAlunoList;
    private AlunoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //pegando variaveis
        componenteCurricular = (TextView) findViewById(R.id.componenteCurricularListagem);

        LinearLayout l1 = (LinearLayout) findViewById(R.id.linearGeralTurmasListagem);
        l1.setVisibility(View.GONE);

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
            Intent login = new Intent(TurmasVirtuais.this, MainActivity.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_usuario) {
            Intent principalTela = new Intent(TurmasVirtuais.this, Principal.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            principalTela.putExtras(params);
            startActivity(principalTela);

        } else if (id == R.id.nav_notas) {
            Intent notasSemestreTela = new Intent(TurmasVirtuais.this, FaltasAluno.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            notasSemestreTela.putExtras(params);
            startActivity(notasSemestreTela);

        } else if (id == R.id.nav_faltas) {
            Intent faltasTela = new Intent(TurmasVirtuais.this, FaltasAluno.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            faltasTela.putExtras(params);
            startActivity(faltasTela);
        } else if (id == R.id.nav_turmas) {
            Intent turmasTela = new Intent(TurmasVirtuais.this, TurmasVirtuais.class);
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

    public void turmas(View view){

        EditText codTurmaText = (EditText) findViewById(R.id.codTurma);
        codTurma = codTurmaText.getText().toString();
        mAlunoList = new ArrayList<>();

        String serviceResult = server.requestWebServiceTurmasVirtuais("https://suap.ifrn.edu.br/api/v2/minhas-informacoes/turmas-virtuais/"+codTurma+"/", token, login);
        if(serviceResult != null) {

            try {

                JSONObject obj = new JSONObject(serviceResult);

                LinearLayout l2 = (LinearLayout) findViewById(R.id.linearGeralTurmasListagem);
                l2.setVisibility(View.VISIBLE);

                componenteCurricular.setText("Cod: "+ obj.getString("id") +" - "+ obj.getString("componente_curricular") + " - Ano e período letivo: " + obj.getString("ano_letivo") +"/"+obj.getString("periodo_letivo"));


                JSONArray objArray = new JSONArray(obj.getJSONArray("participantes").toString());
                for (int i = 0; i < objArray.length(); i++) {
                    JSONObject objEstudante = objArray.getJSONObject(i);
                    mAlunoList.add(new Aluno(
                            objEstudante.getString("matricula"),
                            objEstudante.getString("foto"),
                            objEstudante.getString("email"),
                            objEstudante.getString("nome")));
                }


            } catch (Exception e) {
                Toast.makeText(this,e.toString() , Toast.LENGTH_LONG).show();
            }

            alunos = (ListView) findViewById(R.id.textListaTurmas);

            try {
                //Init adapter
                adapter = new AlunoListAdapter(this, mAlunoList);
                alunos.setAdapter(adapter);
                LinearLayout l = (LinearLayout) findViewById(R.id.linearGeralTurmas);
                l.setVisibility(View.GONE);

            } catch (Exception e) {
                Log.d("ERRO", e.toString());
            }
        }
    }
}
