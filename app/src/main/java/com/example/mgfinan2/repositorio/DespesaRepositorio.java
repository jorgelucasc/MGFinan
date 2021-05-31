package com.example.mgfinan2.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mgfinan2.dominio.entidades.Despesa;

import java.util.ArrayList;
import java.util.List;

public class DespesaRepositorio {

    public SQLiteDatabase conexao;

    public DespesaRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Despesa despesa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("OBSDESPESA",despesa.obsDespesa);
        contentValues.put("VLRDESPESA", despesa.vlrDespesa);
        contentValues.put("TPDESPESA", String.valueOf(despesa.tpdespesa));

        conexao.insertOrThrow("DESPESA", null, contentValues);
    }

    public void excluir(int id){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("DESPESA", "id=?", parametros);
    }
    public void alterar(Despesa despesa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("OBSDESPESA",despesa.obsDespesa);
        contentValues.put("VLRDESPESA", despesa.vlrDespesa);
        contentValues.put("TPDESPESA", despesa.tpdespesa);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(despesa.id);

        conexao.update("DESPESA",contentValues, "id=?",parametros);
    }

    public List<Despesa> buscarTodos(){

        List<Despesa> despesas = new ArrayList<Despesa>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID, CREATED_AT, OBSDESPESA, VLRDESPESA, TPDESPESA ");
        sql.append("FROM DESPESA");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if (resultado.getCount()>0){
            resultado.moveToFirst();

            do {

                Despesa desp = new Despesa();

                desp.id = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                desp.obsDespesa = resultado.getString(resultado.getColumnIndexOrThrow("OBSDESPESA"));
                desp.vlrDespesa = resultado.getString(resultado.getColumnIndexOrThrow("VLRDESPESA"));
                desp.tpdespesa = resultado.getString(resultado.getColumnIndexOrThrow("TPDESPESA"));

                despesas.add(desp);

            }while (resultado.moveToNext());
        }
        return despesas;
    }

}
