package DBHelper;

public class ScriptDLL {

    public static String getCreateTableDespesa(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS DESPESA( ");
        sql.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append(" CREATED_AT DATE CURRENT_DATE , ");
        sql.append(" OBSDESPESA VARCHAR(250) NOT NULL DEFAULT ('') , ");
        sql.append(" VLRDESPESA INT NOT NULL DEFAULT ('') , ");
        sql.append(" TPLANC VARCHAR NOT NULL DEFAULT ('') , ");
        sql.append(" TPDESPESA VARCHAR NOT NULL DEFAULT ('') )");

        return sql.toString();
    }
}
