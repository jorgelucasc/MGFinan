package com.example.mgfinan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import DBHelper.DespesasBD;
import model.Despesas;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    DespesasBD dbHelper;
    ArrayList<Despesas> listview_listdesp;
    Despesas despesa;
    ArrayAdapter adapter;
    Button btn_VoltarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listview_listdesp);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Despesas despesaSelecionada = (Despesas) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, MenuPrincipal.class);
                i.putExtra("Despesa-selecionada", despesaSelecionada);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                despesa = (Despesas)adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar esta Despesa");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbHelper = new DespesasBD(MainActivity.this);
                dbHelper.deletarDespesa(despesa);
                dbHelper.close();
                carregarLista();
                return true;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregarLista();
    }

    public void carregarLista(){
        dbHelper = new DespesasBD(MainActivity.this);
        listview_listdesp = dbHelper.getLista();
        dbHelper.close();

        if(listview_listdesp != null){
            adapter = new ArrayAdapter<Despesas> (MainActivity.this, android.R.layout.simple_list_item_1, listview_listdesp);
            lista.setAdapter(adapter);
        }
        // comentei pq tava crashando o app finish();
    }
}