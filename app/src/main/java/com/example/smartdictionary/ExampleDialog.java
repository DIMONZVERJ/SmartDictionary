package com.example.smartdictionary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Objects;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText word_edit;
    private ListView listView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_new_word,null);
        builder.setView(view)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListOperations.insert(word_edit.getText().toString());
                    }
                });
        word_edit = view.findViewById(R.id.editText);
        listView = view.findViewById(R.id.listView);
        return builder.create();
    }
}
