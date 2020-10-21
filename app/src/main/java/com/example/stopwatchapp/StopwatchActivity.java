package com.example.stopwatchapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {
    private int seconds=0;
    private int hours,minutes,secs;
    private boolean running;
    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        names = new ArrayList<String>();
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        runTimer();


    }

    public void onSaveInstaceState(Bundle savedInstaceState) {

        savedInstaceState.putInt("seconds",seconds);
        savedInstaceState.putBoolean("running",running);
        super.onSaveInstanceState(savedInstaceState);
    }



    public void onClickStart(View view)
    {
        running=true;
    }
    public void onClickStop(View view) {
        running=false;
    }
    public void onClickReset(View view) {
        running=false;
        names = new ArrayList<String>();
        seconds=0;
        String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);



    }
    private void runTimer()    {
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                hours = seconds / 3600;
                minutes = (seconds % 3600) / 60;
                secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }
    public void onClickVuelta(View view){
        final ListView timeView=(ListView) findViewById(R.id.vuelta_view);

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                names.add(time);
                if (names.size()<10) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
                    timeView.setAdapter(adapter);
                }else{Toast.makeText(StopwatchActivity.this, "Ya completo las 10 vueltas,por favor reinicie", Toast.LENGTH_SHORT).show();}

    }



}