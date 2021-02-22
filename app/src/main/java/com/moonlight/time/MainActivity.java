 package com.moonlight.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    TextView tv;
    BatteryReceiver br;

    EditText name;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tool=findViewById(R.id.toolbar);
        tool.setLogo(R.drawable.ic_launcher_foreground);
        setSupportActionBar(tool);
        name=findViewById(R.id.name);
        rating=findViewById(R.id.ratingBar);

        ConstraintLayout mainside=findViewById(R.id.mainLayout);

        LayoutInflater inf=getLayoutInflater();
        View myLayout=inf.inflate(R.layout.head,mainside,false);
        myLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_200));
        TextView txt=myLayout.findViewById(R.id.textView2);
        txt.setText("Hi this is inflated");
        mainside.addView(myLayout);

        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Back Arrow", Toast.LENGTH_SHORT).show();
            }
        });

            setContentView(R.layout.activity_main);
            tv=findViewById(R.id.tv);
            br= new BatteryReceiver(tv);
            registerReceiver(br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            Button alrm=findViewById(R.id.alarm);
            alrm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent newInt=new Intent(MainActivity.this,alarm.class);
                    startActivity(newInt);
                }
            });

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(br);
        }



    public void submit(View view) {
        if(!name.getText().toString().equals("")){
            DatabaseVi b=new DatabaseVi(this);
            Contact news=new Contact();
            int no=0;
            no=b.getContactsCount();
            news.setID(no+1);
            news.setName(name.getText().toString());
            news.setPhoneNumber(String.valueOf(rating.getRating()));
            b.addContact(news);
            name.setText("");
            rating.setRating(0);
            Toast.makeText(this,"Thanks for submitting your rating!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show();
        }
    }

    public void showdata(View view) {
        Intent news=new Intent(this,detailes.class);
        startActivity(news);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Schedule(View view) {
        Intent news=new Intent(this,jobschedule.class);
        startActivity(news);
    }
}
