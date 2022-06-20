package com.example.laboratorywork3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    String CHANNEL_ID = "my_channel_01";

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.hey);
            String description = getString(R.string.description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_main);
        ImageButton shortToast = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton longToast = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton notificationButton = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton dialogueButton = (ImageButton) findViewById(R.id.imageButton6);
        shortToast.setOnClickListener(this);
        longToast.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
        dialogueButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Context context = getApplicationContext();
        switch (view.getId()) {
            case R.id.imageButton3:
                Toast shortToast = Toast.makeText(context, "Short toast!", Toast.LENGTH_SHORT);
                shortToast.show();
                break;
            case R.id.imageButton4:
                Toast longToast = Toast.makeText(context, "Long toast!", Toast.LENGTH_LONG);
                longToast.show();
                break;
            case R.id.imageButton5:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                int notificationId = 1;
                String CHANNEL_ID = "my_channel_01";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                        CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_popup_reminder)
                        .setContentTitle("Привет!")
                        .setContentText("Это уведомление!").
                        setContentIntent(pendingIntent).
                        setAutoCancel(true);
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(notificationId, builder.build());
                break;
            case R.id.imageButton6:
                FragmentManager manager = getSupportFragmentManager();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(manager, "myDialog");

        }

    }
}