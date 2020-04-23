package com.example.smartdictionary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NotificationActivity extends Activity {

    private TextView question;
    private EditText answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        final String question_str = "Введите перевод слова ";
        question = findViewById(R.id.textView_question);
        answer = findViewById(R.id.editText_answer);
        Intent intent = getIntent();
        question.setText(question_str + intent.getStringExtra("word_added"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String a = intent.getStringExtra("word_added");
        int b = 9;
    }
}
