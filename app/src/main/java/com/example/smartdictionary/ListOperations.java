package com.example.smartdictionary;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

class ListOperations {
    private static SimpleAdapter adapter;
    private static ListView listView;
    private static MainActivity activity;
    private static  ArrayList<HashMap<String,String>> list;
    static void Initialize(MainActivity activity)
    {
        ListOperations.activity = activity;
        listView = activity.findViewById(R.id.listView);
        SQLOperations sqlOperations = new SQLOperations(activity);
        list = sqlOperations.getAllData();
        adapter = new SimpleAdapter(
                activity,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{"word","translate"},
                new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);
        sqlOperations.CloseDatabase();
    }
    static void insert(String word, String translate)
    {
        SQLOperations sqlOperations = new SQLOperations(activity);
        sqlOperations.insert(word, translate);
        HashMap<String, String> set = new HashMap<String,String>();
        set.put("word",word);
        set.put("translate",translate);
        list.add(set);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        sqlOperations.CloseDatabase();
    }
}
