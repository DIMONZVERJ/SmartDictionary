package com.example.smartdictionary;


import android.util.ArrayMap;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.List;

import roomdatabase.AppDelegate;
import roomdatabase.Dictionary;


class ListOperations {
    private static SimpleAdapter adapter;
    private static ListView listView;
    private static MainActivity activity;
    private static ArrayList<ArrayMap<String, String>> list;
    static void Initialize(final MainActivity activity)
    {
        ListOperations.activity = activity;
        listView = activity.findViewById(R.id.listView);
        list = convertToListMap(((AppDelegate)activity.getApplicationContext()).getAppDatabase().DictionaryDao().getAll());
        adapter = new SimpleAdapter(
                activity,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{"word","translate"},
                new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);
    }

    static ArrayList<ArrayMap<String, String>> convertToListMap(List<roomdatabase.Dictionary> wordsList){
        ArrayList<ArrayMap<String, String>> list = new ArrayList<>();
        for (roomdatabase.Dictionary word:wordsList) {
            ArrayMap<String, String> arrayMap = new ArrayMap<>();
            arrayMap.put("word", word.getWord());
            arrayMap.put("translate",word.getTranslate());
            list.add(arrayMap);
        }
        return list;
    }

    static void insert(final String word, final String translate)
    {
        roomdatabase.Dictionary words = new roomdatabase.Dictionary();
        words.setWord(word);
        words.setTranslate(translate);
        ((AppDelegate)activity.getApplicationContext()).getAppDatabase().DictionaryDao().insert(words);

        List<Dictionary> list_a = ((AppDelegate)activity.getApplicationContext()).getAppDatabase().DictionaryDao().getAll();
        ArrayMap<String, String> set = new ArrayMap<>();
        set.put("word",word);
        set.put("translate",translate);
        list.add(set);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    static void update(final int id, final String word, final String translate)
    {
        ArrayMap<String, String> set = new ArrayMap<>();
        set.put("word",word);
        set.put("translate",translate);

        ((AppDelegate)activity.getApplicationContext())
                .getAppDatabase()
                .DictionaryDao()
                .update(new Dictionary(id+1, word, translate));

        list.set(id,set);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    static void delete(final String word, final String translate)
    {
        ArrayMap<String, String> set = new ArrayMap<>();
        set.put("word",word);
        set.put("translate",translate);
        list.remove(set);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
