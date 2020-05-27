package com.example.notification30032020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mBtnOpen, mBtnClose;
    String CHANEL_ID = "2";
    String CHANEL_NAME = "chanel_app";
    NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnClose = findViewById(R.id.buttonClose);
        mBtnOpen = findViewById(R.id.buttonOpen);

        // 1 : Tao giao dien notification : NotificationCompat.Builder
        // 2 : Khi click notification se thuc thi viec gi : PendingIntent
        // 3 : Tat notification

        Intent intent = getIntent();
        if (intent != null){
            String chuoi = intent.getStringExtra("chuoi");
            Toast.makeText(this, chuoi, Toast.LENGTH_SHORT).show();
        }
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("chuoi","Hello main");
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                123,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANEL_ID)
                                .setContentTitle("Co thong bao moi")
                                .setContentText("Co phien moi ban muon cap nhap khong")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pendingIntent)
                                .setStyle(
                                        new NotificationCompat
                                                .BigPictureStyle()
                                                .bigPicture(
                                                        BitmapFactory
                                                                .decodeResource(
                                                                getResources(),
                                                                R.mipmap.ic_launcher))

                                );


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel = new NotificationChannel
                            (CHANEL_ID , CHANEL_NAME , NotificationManager.IMPORTANCE_DEFAULT );
                    notificationChannel.enableVibration(true);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }
                mNotificationManager.notify(1 ,builder.build());

            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(1);
            }
        });
    }

}
