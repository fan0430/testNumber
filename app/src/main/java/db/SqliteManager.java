package db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dai_li_fan on 2015/12/8.
 */
public class SqliteManager {
    private static String TAG = "SqliteManager";

    public static void clearTable(String table) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();

        db.delete(table, null, null);
    }

//	public static void clearAllTables() {
//		SQLiteDatabase db = DodoHouseApplication.getDBInstance();
//		try {
//			db.beginTransaction();
//			db.delete("sqlite_sequence", null, null);
//			db.delete(TokenDBController.TABLE_NAME, null, null);
//			db.delete(AgreeAppIndemnificationClauseDBController.TABLE_NAME, null, null);
//			db.delete(AppVersionDBController.TABLE_NAME, null, null);
//			db.setTransactionSuccessful();
//		} finally {
//			db.endTransaction();
//		}
//	}

    public static long insertWithoutTransaction(String table, ContentValues values) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        return db.insert(table, null, values);
    }

    public static int updateWithoutTransaction(String table, ContentValues values, String whereClause) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        return db.update(table, values, whereClause, null);
    }

    public static long replaceWithoutTransaction(String table, ContentValues initialValues, String whereClause) {
        long result = -1;

        if (query(table, null, whereClause, null, null, "1").isEmpty()) {
            result = insertWithoutTransaction(table, initialValues);
        } else {
            result = updateWithoutTransaction(table, initialValues, whereClause);
        }

        return result;
    }

    public static int deleteWithoutTransaction(String table, String where) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        return db.delete(table, where, null);
    }

    public static long insert(String table, ContentValues values) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        long row = -1;
        try {
            db.beginTransaction();
            row = db.insert(table, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return row;
    }

    public static ArrayList<HashMap<String, String>> query(String table, String[] columns, String whereClause, String groupBy, String orderBy, String limit) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        Cursor cursor = db.query(table, columns, whereClause, null, groupBy, null, orderBy, limit);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        cursor.moveToFirst();
        int column_count = cursor.getColumnCount();

        while (!cursor.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < column_count; i++) {
                map.put(cursor.getColumnName(i), cursor.getString(i));
            }

            list.add(map);
            cursor.moveToNext();
        }

        cursor.close();

        return list;
    }

    public static long replace(String table, ContentValues initialValues, String whereClause) {
        long result = -1;

        if (query(table, null, whereClause, null, null, "1").isEmpty()) {
            result = insert(table, initialValues);
        } else {
            result = update(table, initialValues, whereClause);
        }

        return result;
    }

    public static int update(String table, ContentValues values, String whereClause) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        int row = -1;
        try {
            db.beginTransaction();
            row = db.update(table, values, whereClause, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return row;
    }

    public static int delete(String table, String where) {
        SQLiteDatabase db = Utilities.mainApplication.getDBInstance();
        int row = -1;
        try {
            db.beginTransaction();
            row = db.delete(table, where, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return row;
    }

}
