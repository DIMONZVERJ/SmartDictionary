package com.example.smartdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.xml.validation.SchemaFactoryLoader;

public class SQLOperations {

    public static SQLiteDatabase db;
    private static DBHelper helper;
    public static void openDataBase(Context context)
    {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();

    }
    public static long insert(String value)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.WORD,value);
        return db.insert(DBHelper.TABLE_NAME,null,contentValues);
    }
    public static ArrayList<String> getAllData()
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
    public static void CloseDatabase()
    {
        db.close();
        helper.close();
    }
}
