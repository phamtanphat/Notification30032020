package com.example.notification30032020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnOpen, mBtnClose;
    String CHANEL_ID = "1";
    String CHANEL_NAME = "chanel_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnClose = findViewById(R.id.buttonClose);
        mBtnOpen = findViewById(R.id.buttonOpen);

        // 1 : Tao giao dien notification : NotificationCompat.Builder
        // 2 : Khi click notification se thuc thi viec gi : PendingIntent
        // 3 : Tat notification
        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANEL_ID)
                                .setContentTitle("Co thong bao moi")
                                .setContentText("Co phien moi ban muon cap nhap khong")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setWhen(System.currentTimeMillis())
                                .setStyle(
                                        new NotificationCompat
                                                .BigPictureStyle()
                                                .bigPicture(
                                                        BitmapFactory
                                                                .decodeResource(
                                                                getResources(),
                                                                R.mipmap.ic_launcher))

                                );
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel = new NotificationChannel
                            (CHANEL_ID , CHANEL_NAME , NotificationManager.IMPORTANCE_DEFAULT );
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                notificationManager.notify(1 ,builder.build());

            }
        });

    }
}
