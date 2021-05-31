package com.example.mgfinan2;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mgfinan2.dominio.entidades.Despesa;
import com.example.mgfinan2.repositorio.DespesaRepositorio;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import DBHelper.DespesasBD;

import static com.example.mgfinan2.R.layout.act_main;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActMain extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private RelativeLayout layoutContentMain;
    private DespesasBD despesasBD;
    private RecyclerView lstDados;
    private FloatingActionButton fab1,fab2;
    private DespesaAdapter despesaAdapter;
    private DespesaRepositorio despesaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(act_main);
        lstDados = (RecyclerView)findViewById(R.id.lstDados);
        layoutContentMain = (RelativeLayout) findViewById(R.id.layoutContentMain);

        fab1 = findViewById(R.id.fab_action1);
        fab1.setOnClickListener(v -> {
            showToast("Cadastrar Despesa");
            cadastrar(v);});

        fab2 = findViewById(R.id.fab_action2);
        fab2.setOnClickListener(v -> {
            showToast("Ultimos Lançamentos");
            Intent it = new Intent(ActMain.this,ResultScreen.class);
            startActivity(it);
        });

        criarConexao();

        lstDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        despesaRepositorio = new DespesaRepositorio(conexao);

        List<Despesa> dados = despesaRepositorio.buscarTodos();

        despesaAdapter = new DespesaAdapter(dados);

        lstDados.setAdapter(despesaAdapter);

    }

    private void criarConexao(){

        try {

            despesasBD = new DespesasBD(this);
            conexao = despesasBD.getWritableDatabase();

            Snackbar.make(layoutContentMain, "Conexão Criada Com Sucesso", Snackbar.LENGTH_SHORT).setAction("Ok", null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }
    public void cadastrar(View view){
        Intent it = new Intent(ActMain.this,ActCadDespesa.class);
        startActivityForResult(it,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        List<Despesa> dados = despesaRepositorio.buscarTodos();
        despesaAdapter = new DespesaAdapter(dados);
        lstDados.setAdapter(despesaAdapter);
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}