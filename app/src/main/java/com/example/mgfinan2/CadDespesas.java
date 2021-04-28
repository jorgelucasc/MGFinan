package com.example.mgfinan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import DBHelper.DespesasBD;
import model.Despesas;

public class CadDespesas extends AppCompatActivity {
    EditText editText_ObsDesp,  editTextText_VlrDespesa;
    Spinner spinner_tpDespesa;
    Button btn_salvarDespesa, btn_VoltarMenu1;
    Despesas despesa;
    DespesasBD DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_despesas);

        despesa = new Despesas();
        DBHelper = new DespesasBD(CadDespesas.this);

        editText_ObsDesp = (EditText) findViewById(R.id.editText_ObsDesp);
        editTextText_VlrDespesa = (EditText) findViewById(R.id.editText_VlrDespesa);

        spinner_tpDespesa = (Spinner) findViewById(R.id.spinner_tpDespesa);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        spinner_tpDespesa.setAdapter(adapter);

        btn_salvarDespesa = (Button) findViewById(R.id.btn_salvarDespesa);
        btn_salvarDespesa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(CadDespesas.this, MainActivity.class);
                startActivity(intent5);

                despesa.setObsDespesa(editText_ObsDesp.getText().toString());
                despesa.setValordespesa(Integer.parseInt(editTextText_VlrDespesa.getText().toString()));
                despesa.setTipoDespesa(spinner_tpDespesa.toString());

                if (btn_salvarDespesa.getText().toString().equals("Salvar")){
                    DBHelper.salvarDespesa(despesa);
                }else{
                    DBHelper.deletarDespesa(despesa);
                }
                DBHelper.close();
            }
        });
        btn_VoltarMenu1 = (Button) findViewById(R.id.btn_VoltarMenu1);
        btn_VoltarMenu1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(CadDespesas.this, MenuPrincipal.class);
                startActivity(intent6);
            }
        });
    }
}