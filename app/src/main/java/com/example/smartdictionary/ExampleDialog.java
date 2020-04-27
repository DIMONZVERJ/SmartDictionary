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

import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText word_edit;
    private EditText translate_edit;
    public static MainActivity activity;
    private static final int notificationID = 1;
    public static final String CHANNEL_ID = "channel_id";
    public static final String name = "channel_id name";
    public static final String description = "This is channel_id";
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_new_word,null);
        builder.setView(view)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String word = word_edit.getText().toString().toLowerCase();
                        final String translate = translate_edit.getText().toString().toLowerCase();
                        if (!word.equals("") && (!translate.equals("")))
                        {
                            ListOperations.insert(word,translate);
                            Toast.makeText(activity,"Слово добавлено в ваш словарь", Toast.LENGTH_SHORT).show();
                        }
//                        TimerTask task = new TimerTask() {
//                            @Override
//                            public void run() {
//                                activity.runOnUiThread(new Runnable() {
//                                                           @Override
//                                                           public void run() {
//                                                               createNotification(word,translate);
//                                                           }
//                                                       });
//                            }
//                        };
//                        Timer timer = new Timer();
//                        timer.schedule(task, 5*1000);}
                    }
                });
        word_edit = view.findViewById(R.id.editText);
        translate_edit = view.findViewById(R.id.editText1);
        return builder.create();
    }
    private void createNotification(String word, String translate)
    {
        createNotificationChannel();
        Intent intent = new Intent(activity, NotificationActivity.class);
        intent.putExtra("word_added", word);
        intent.putExtra("translate", translate);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(activity,0,intent,0);
        Notification builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .setContentTitle("Напоминание")
                .setContentText("Вам надо повторить новое слово")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);
        notificationManager.notify(notificationID, builder);
    }
    private void createNotificationChannel()
    {
        // Create the NotificationChannel, but only on API 26+ because
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager manager = activity.getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }
    }
}
