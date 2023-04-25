package com.example.stopwatch;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.stopwatch.R.id;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    TextView textView;
    int  h =0;
    int m = 0;
    int s =0;
    int ms = 0;
   boolean isRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_tittle_position);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        textView = findViewById(R.id.timerText);
        startTimer();

    }

    public void onClickStart(View v) {

       isRunning =true;

    }
    public void onClickStop(View v) {
        isRunning =false;

    }
    public void onClickReset(View v) {
       isRunning = false;
        ms =0;
        s=0;
        m=0;
        h=0;


    }

    public void startTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                if(isRunning) {
                    ms++;
                    if (ms == 10) {
                        ms= 0;
                        s++;
                        if (s == 60) {
                            s = 0;
                            m++;
                            if (m == 60) {
                                m= 0;
                                h++;
                            }
                        }
                    }
                }
                String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d" , h, m, s, ms);

                textView.setText(formatString);
                handler.postDelayed(this, 100);


            }
        };
        handler.post(runnable);

    }
}