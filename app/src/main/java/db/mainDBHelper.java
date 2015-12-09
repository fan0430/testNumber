package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dai_li_fan on 2015/12/8.
 */
public class mainDBHelper extends SQLiteOpenHelper{

    public final static String DB_DATABASE_NAME = "numbergame.db";
    private static int _DB_VERSION = 0;
    public mainDBHelper(Context context) {
        super(context, DB_DATABASE_NAME, null, _DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDB(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion <= oldVersion){
            return;
        }

        if(db.getVersion() == 1){

        }

    }

    private void initDB(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE three_number ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name VARCHAR NULL,"
                + "sec VARCHAR NULL," + ")");

        db.execSQL("CREATE TABLE four_number ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name VARCHAR NULL,"
                + "sec VARCHAR NULL," + ")");

        db.execSQL("CREATE TABLE five_number ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name VARCHAR NULL,"
                + "sec VARCHAR NULL," + ")");

    }
}
