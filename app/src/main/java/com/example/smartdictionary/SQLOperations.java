package com.example.smartdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;


class SQLOperations {

    private SQLiteDatabase db;
    private DBHelper helper;
    SQLOperations(Context context)
    {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    long insert(String word, String translate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.WORD, word);
        contentValues.put(DBHelper.TRANSLATE, translate);
        return db.insert(DBHelper.TABLE_NAME,null,contentValues);
    }
    ArrayList<HashMap<String, String>> getAllData()
    {
        ArrayList<HashMap<String, String>> res_list = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> pairs = new HashMap<String, String>();
                pairs.put("word",cursor.getString(1));
                pairs.put("translate", cursor.getString(2));
                res_list.add(pairs);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return res_list;
    }
    void CloseDatabase()
    {
        db.close();
        helper.close();
    }
}
