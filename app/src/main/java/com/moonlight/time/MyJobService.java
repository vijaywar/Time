package com.moonlight.time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob:");
        Intent intent = new Intent(MyJobService.this,AlarmManagerBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MyJobService.this,
                234,intent,0 );
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),pendingIntent);
        Toast.makeText(MyJobService.this,"Alarm Set ",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob:");
        AlarmManagerBroadcast.mp.stop();
        return true;
    }
}