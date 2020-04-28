package com.example.smartdictionary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import roomdatabase.Dictionary;
import roomdatabase.DictionaryDao;

public class Edit_DeleteDialog extends AppCompatDialogFragment {

    private int id;
    private EditText word_edit;
    private EditText translate_edit;
    public static MainActivity activity;
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.enter_new_word,null);
        builder.setView(view)
                .setPositiveButton(R.string.delete_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String word = word_edit.getText().toString().toLowerCase().trim();
                        final String translate = translate_edit.getText().toString().toLowerCase().trim();
                        ListOperations.delete(word, translate);
                        Toast.makeText(activity, "Слово удалено", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton(R.string.edit_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String word = word_edit.getText().toString().toLowerCase().trim();
                        final String translate = translate_edit.getText().toString().toLowerCase().trim();
                        if (!word.equals("") && (!translate.equals("")))
                        {
                            ListOperations.update(id, word, translate);
                            Toast.makeText(activity,"Слово успешно изменено", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        word_edit = view.findViewById(R.id.editText);
        translate_edit = view.findViewById(R.id.editText1);
        return builder.create();
    }

    public void setId(int id) {
        this.id = id;
    }
}
