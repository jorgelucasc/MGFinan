package DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mgfinan2.dominio.entidades.Despesa;

public class DespesasBD extends SQLiteOpenHelper {

    private static final String DATABASE = "DESPESAS";
    private static final int VERSION = 2;

    public DespesasBD (Context context){
        super(context, "DESPESAS", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDLL.getCreateTableDespesa());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //aqui salva
    public void salvarDespesa(Despesa despesa){
        ContentValues values = new ContentValues();

        values.put("OBSDESPESA",despesa.obsDespesa);
        values.put("TPDESPESA", despesa.tpdespesa);
        values.put("VLRDESPESA",despesa.vlrDespesa);

        getWritableDatabase().insert("DESPESAS",null,values);
    }
}
