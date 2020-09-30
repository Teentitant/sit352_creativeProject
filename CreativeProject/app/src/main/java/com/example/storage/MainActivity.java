package com.example.storage;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Choreographer;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storage.loginActivity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer timer;
    private TextView tv_timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        tv_timer = findViewById(R.id.tv_timer);

        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }.start();
    }
}

