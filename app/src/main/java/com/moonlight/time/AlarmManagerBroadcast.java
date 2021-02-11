package com.moonlight.time;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmManagerBroadcast extends BroadcastReceiver {
    static MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        /*Vibrator vibrator =
                (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);*/
        mp=MediaPlayer.create(context, R.raw.alarm);
        mp.start();
        Toast.makeText(context, "Message Ringing Alarm", Toast.LENGTH_LONG).show();
    }
}