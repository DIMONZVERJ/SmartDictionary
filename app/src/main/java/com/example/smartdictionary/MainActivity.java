package com.example.smartdictionary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        Button add = findViewById(R.id.button);
        add.setOnClickListener(this);
        DBHelper helper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put(DBHelper.WORD,"Apple");

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(DBHelper.TABLE_NAME,null,values);
        ArrayList<String> arrayList = getData(db.query(DBHelper.TABLE_NAME,null,null,null,null,null,null));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        helper.close();
    }

    @Override
    public void onClick(View v) {
        DBHelper helper = new DBHelper(this);

    }

    public ArrayList<String> getData(Cursor cursor)
    {
        ArrayList<String> arrayList = new ArrayList<>();
        int count = cursor.getCount();
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
}
