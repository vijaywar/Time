package com.moonlight.time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class jobschedule extends AppCompatActivity {

    JobInfo jobInfo;
    JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedule);
    }

    public void startJob(View view)
    {

        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(this,MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(123, componentName);
        builder.setMinimumLatency(1 * 1000);
        builder.setOverrideDeadline(3 * 1000);

        builder.setPersisted(true);
        builder.setRequiredNetworkType(jobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresCharging(false);
        jobScheduler.schedule(builder.build());
    }

    public void stopJob(View view)
    {
        if(jobScheduler != null)
        {
            jobScheduler.cancel(123);
            jobScheduler = null;

            Toast.makeText(this,"Job Cancelled", Toast.LENGTH_SHORT).show();
        }

    }
}