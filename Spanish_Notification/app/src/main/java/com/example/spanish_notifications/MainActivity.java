package com.example.spanish_notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);


    }

    public void sendOnChannel1(View v){
       String[] phrases = {"Buenos días = Good morning.", "Buenas tardes = Good afternoon","Buenas noches = Good evening"
               ,"Hola, me llamo Juan = Hello, my name is John","¿Cómo te llamas? = What's your name?",
               "Mucho gusto = Nice to meet you", "¿Cómo estás? = How are you?", "¿Qué hora es? = What time is it?", "Te extraño = I miss you",
               "Te quiero = I love you" } ;
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("The new phrase: ")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(phrases[randomNum]).build();
        notificationManager.notify(1, notification);

        

    }

}