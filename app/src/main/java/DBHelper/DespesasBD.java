package DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.Despesas;

public class DespesasBD extends SQLiteOpenHelper {

    private static final String DATABASE = "mgfinandespesas";
    private static final int VERSION = 1;

    public DespesasBD (Context context){
        super(context, DATABASE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String despesa = "CREATE TABLE despesas(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, obsdespesa TEXT NOT NULL," +
                " tpdespesa TEXT NOT NULL, valordespesa INTEGER);";
        db.execSQL(despesa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String despesa = "DROP TABLE if EXISTS despesas";
        db.execSQL(despesa);
    }
    //aqui salva
    public void salvarDespesa(Despesas despesa){
        ContentValues values = new ContentValues();

        values.put("obsdespesa",despesa.getObsDespesa());
        values.put("tpdespesa",despesa.getTipoDespesa());
        values.put("valordespesa",despesa.getValordespesa());

        getWritableDatabase().insert("despesas",null,values);
    }
    //Deletar Despesa

        public void deletarDespesa(Despesas despesa){
            String [] args = {String.valueOf(despesa.getId()),toString()};
            getWritableDatabase().delete("despesas","id=?",args);
        }

    // listar - mostrar
    public ArrayList<Despesas> getLista(){
        String [] columns = {"id","obsdespesa","tpdespesa","valordespesa"};
        Cursor cursor = getWritableDatabase().query("despesas", columns, null,null,null,null,
        null, null);
        ArrayList<Despesas> despesas = new ArrayList<Despesas>();

        while (cursor.moveToNext()) {
            Despesas despesa = new Despesas();
            despesa.setId(cursor.getLong(0));
            despesa.setObsDespesa(cursor.getString(1));
            despesa.setTipoDespesa(cursor.getString(2));
            despesa.setValordespesa(cursor.getInt(3));

            despesas.add(despesa);
        }
        return despesas;
    }
}
