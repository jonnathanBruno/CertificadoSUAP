package com.example.jonnathanbruno.suapproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

public class Principal extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private ServerRequest server = new ServerRequest();
    private String token;
    private String login;
    private TextView matricula;
    private TextView nome;
    private TextView curso;
    private TextView campus;
    private TextView situacao;
    private TextView situacaoSistema;
    private  BancoController crud;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //pegando variaveis
        matricula = (TextView) findViewById(R.id.matricula);
        nome = (TextView) findViewById(R.id.nome);
        curso = (TextView) findViewById(R.id.curso);
        campus = (TextView) findViewById(R.id.campus);
        situacao = (TextView) findViewById(R.id.situacao);
        situacaoSistema = (TextView) findViewById(R.id.situacaoSistema);

        //criando o banco
        crud = new BancoController(this,4);

        //permissão para acessar internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //intents
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if(params!=null)
        {
            token = params.getString("token");
            login = params.getString("login");

            String serviceResult = server.requestWebServiceDados("https://suap.ifrn.edu.br/api/v2/edu/alunos/"+login+"/", token, login);
            try{
                JSONObject obj = new JSONObject(serviceResult);

                matricula.setText(obj.getString("matricula"));
                nome.setText(obj.getString("nome"));
                curso.setText(obj.getString("curso"));
                campus.setText(obj.getString("campus"));
                situacao.setText(obj.getString("situacao"));
                situacaoSistema.setText(obj.getString("situacao_sistemica"));

            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "erro", Toast.LENGTH_LONG).show();
            }

            String resultado;
            resultado = crud.inserirDadoUsuario(login, token, matricula.getText().toString(), nome.getText().toString(), curso.getText().toString(), campus.getText().toString(), situacao.getText().toString(), situacaoSistema.getText().toString());
            //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            resultado = crud.buscarDadoUsuario(token, this);
            //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }
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
            Intent login = new Intent(Principal.this, MainActivity.class);
            startActivity(login);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_usuario) {
            Intent principalTela = new Intent(Principal.this, Principal.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            principalTela.putExtras(params);
            startActivity(principalTela);

        } else if (id == R.id.nav_notas) {
            Intent notasSemestreTela = new Intent(Principal.this, NotasSemestre.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            notasSemestreTela.putExtras(params);
            startActivity(notasSemestreTela);

        } else if (id == R.id.nav_faltas) {
            Intent faltasTela = new Intent(Principal.this, FaltasAluno.class);
            Bundle params = new Bundle();
            params.putString("token",token);
            params.putString("login",login);
            faltasTela.putExtras(params);
            startActivity(faltasTela);
        } else if (id == R.id.nav_turmas) {
            Intent turmasTela = new Intent(Principal.this, TurmasVirtuais.class);
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
}