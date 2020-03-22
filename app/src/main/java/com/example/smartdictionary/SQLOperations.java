package com.example.smartdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


class SQLOperations {

    private SQLiteDatabase db;
    private DBHelper helper;
    SQLOperations(Context context)
    {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    long insert(String value)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.WORD,value);
        return db.insert(DBHelper.TABLE_NAME,null,contentValues);
    }
    ArrayList<String> getAllData()
    {
        Cursor cursor = db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null,null);
        ArrayList<String> arrayList = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
            {
                arrayList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
    void CloseDatabase()
    {
        db.close();
        helper.close();
    }
}
