package com.example.spanish_notification_repeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String[] phrases = {"Buenos días = Good morning.", "Buenas tardes = Good afternoon","Buenas noches = Good evening"
                ,"Hola, me llamo Juan = Hello, my name is John","¿Cómo te llamas? = What's your name?",
                "Mucho gusto = Nice to meet you", "¿Cómo estás? = How are you?", "¿Qué hora es? = What time is it?", "Te extraño = I miss you",
                "Te quiero = I love you" } ;
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyChannel")
                .setSmallIcon(R.drawable.ic_heart)
                .setContentTitle("The new phrase: ")
                .setContentText(phrases[randomNum])
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());


    }
}
