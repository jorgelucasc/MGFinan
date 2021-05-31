package com.example.mgfinan2;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mgfinan2.dominio.entidades.Despesa;
import com.example.mgfinan2.repositorio.DespesaRepositorio;
import com.google.android.material.snackbar.Snackbar;

import DBHelper.DespesasBD;

public class ActCadDespesa extends AppCompatActivity {

    private EditText edtobservacao,  edtvalordespesa;
    private Spinner tpDespesa;
    private DespesasBD despesasBD;
    private SQLiteDatabase conexao;
    private Despesa despesa;
    private DespesaRepositorio despesaRepositorio;
    private ConstraintLayout layoutContentActCadDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_act_cad_despesas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtobservacao        = (EditText) findViewById(R.id.txtobs);
        edtvalordespesa = (EditText) findViewById(R.id.txtvalor);
        tpDespesa = (Spinner) findViewById(R.id.spinner_tpDespesa);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tpDespesa.setAdapter(adapter);

        layoutContentActCadDespesa = (ConstraintLayout)findViewById(R.id.layoutContentActCadDespesa);

        criarConexao();
        verificaParametro();

    }

    private void verificaParametro(){

        Bundle bundle = getIntent().getExtras();

        despesa = new Despesa();

        if((bundle != null) && (bundle.containsKey("DESPESAS"))){

            despesa = (Despesa)bundle.getSerializable("DESPESAS");

            edtobservacao.setText(despesa.obsDespesa);
            edtvalordespesa.setText(despesa.vlrDespesa);
            tpDespesa.getSelectedItem().toString();
        }
    }

    private void criarConexao(){

        try {

            despesasBD = new DespesasBD(this);
            conexao = despesasBD.getWritableDatabase();

            Snackbar.make(layoutContentActCadDespesa, "Conexão Criada Com Sucesso", Snackbar.LENGTH_SHORT)
                    .setAction("Ok", null).show();

            despesaRepositorio = new DespesaRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

    private void confirmar(){

        if (!validaCampos()){
            try {

                if (despesa.id == 0){
                    despesaRepositorio.inserir(despesa);
                }else {
                    despesaRepositorio.alterar(despesa);
                }
                    finish();

            }catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Erro Confirmar");
                dlg.setMessage("Teste");
                dlg.setNeutralButton("Ok", null);
                dlg.show();
            }
        }
    }

    private boolean validaCampos(){

        boolean res;

        String obsdespesa = edtobservacao.getText().toString();
        String valordespesa = edtvalordespesa.getText().toString();

        despesa.obsDespesa = obsdespesa;
        despesa.vlrDespesa = valordespesa;

        if (res = isCampoVazio(obsdespesa)){
            edtobservacao.requestFocus();
        }
        else
            if (res = isCampoVazio(valordespesa)){
                edtvalordespesa.requestFocus();
            }
        if (res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos em Branco");
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }
        return res;
    }

    private boolean isCampoVazio(String valor){
        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_desp,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){

            case android.R.id.home:
                finish();

                break;
            case R.id.action_ok:
                confirmar();

                break;
            case R.id.action_excluir:
                despesaRepositorio.excluir(despesa.id);
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}