package com.example.smartdictionary;

import android.widget.ArrayAdapter;
import android.widget.ListView;

class ListOperations {
    private static ArrayAdapter<String> arrayAdapter;
    private static ListView listView;
    private static MainActivity activity;
    static void Initialize(MainActivity activity)
    {
        ListOperations.activity = activity;
        listView = activity.findViewById(R.id.listView);
        SQLOperations sqlOperations = new SQLOperations(activity);
        arrayAdapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,sqlOperations.getAllData());
        listView.setAdapter(arrayAdapter);
    }
    static void insert(String value)
    {
        SQLOperations sqlOperations = new SQLOperations(activity);
        sqlOperations.insert(value);
        arrayAdapter.add(value);
        sqlOperations.CloseDatabase();
    }
}
