package com.example.smartdictionary;

import android.arch.persistence.room.RoomDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.arch.persistence.room.Room;

import java.util.List;

import roomdatabase.AppDelegate;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_button;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ListOperations.Initialize(this);
        add_button = findViewById(R.id.button_add_word);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        ((AppDelegate)getApplicationContext()).getAppDatabase().close();

    }
    public void openDialog()
    {
        ExampleDialog exampleDialog = new ExampleDialog();
        ExampleDialog.activity = this;
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }
}
