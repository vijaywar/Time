package com.moonlight.time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlarmManager;
        import android.app.DatePickerDialog;
        import android.app.PendingIntent;
        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import java.util.Calendar;

public class alarm1 extends AppCompatActivity  {
    Button btnDatePicker, btnTimePicker,setAlarm,cancel;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    PendingIntent pendingIntent;
    AlarmManager    alarmManager;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm1);
        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        setAlarm=findViewById(R.id.btn_alarm);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);
        cancel= findViewById(R.id.cancel);

        Button alrmk=findViewById(R.id.setakml);
        alrmk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent newInt=new Intent(alarm1.this,notification.class);
                startActivity(newInt);
            }
        });
    }

    public void chooseAction(View v)
    {
        if (v == btnDatePicker)
        {
            // Get Current Date
            c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    mYear=year;mMonth=monthOfYear;mDay=dayOfMonth;
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                            mHour=hourOfDay;mMinute=minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if(v==setAlarm)
        {
            setAlarm(c);
        }
        if(v==cancel)
        {
            alarmManager.cancel(pendingIntent);
            AlarmManagerBroadcast.mp.stop();
        }
    }


    public void setAlarm(Calendar c) {
        c.set(mYear,mMonth,mDay,mHour,mMinute);
        long i=c.getTimeInMillis();

        Intent intent = new Intent(this, AlarmManagerBroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, i, pendingIntent);
        long dur= ((c.getTimeInMillis()-System.currentTimeMillis()))/1000;
        System.out.println(c.getTimeInMillis()+" "+System.currentTimeMillis()+" "+c.getTime());
        Toast.makeText(this, "Alarm set in " +dur  + " seconds",Toast.LENGTH_LONG).show();
    }
}