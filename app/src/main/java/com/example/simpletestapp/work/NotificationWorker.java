package com.example.simpletestapp.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.simpletestapp.R;
import com.example.simpletestapp.SimpleApp;

public class NotificationWorker extends Worker {


    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), SimpleApp.CHANNEL_ID);
        builder.setContentTitle("Alert")
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentText("This is a notification")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1001, builder.build());

        return Result.success();
    }
}
