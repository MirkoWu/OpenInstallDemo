package com.fm.openinstalldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fm.openinstall.OpenInstall;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);

    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finish();
        }
    };

}
