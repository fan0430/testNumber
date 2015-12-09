package db;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.HashMap;

import model.ModelSuccessInfo;

/**
 * Created by dai_li_fan on 2015/12/8.
 */
public class ThreeDBController extends SqliteManager{
    private static String TAG = "ThreeDBController";
    public static final String TABLE_NAME = "three_number";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_SEC = "sec";


    public static long insertData(ModelSuccessInfo model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, model.name);
        values.put(COLUMN_NAME_SEC, model.sec);
        return replaceWithoutTransaction(TABLE_NAME, values, COLUMN_NAME_NAME + " = '" + model.name + "'");
    }

    private static ModelSuccessInfo rowToModel(HashMap<String, String> data) {
        ModelSuccessInfo model = new ModelSuccessInfo();
        model.name = data.get(COLUMN_NAME_NAME);
        model.sec = data.get(COLUMN_NAME_SEC);
        return model;
    }

    public static ModelSuccessInfo queryUserAccount() {
        ModelSuccessInfo model = null;
        ArrayList<HashMap<String, String>> query_list = query(TABLE_NAME, null, null, null, null, null);
        if (!query_list.isEmpty()) {
            HashMap<String, String> data = query_list.get(0);
            model = rowToModel(data);
        }

        return model;
    }


}
