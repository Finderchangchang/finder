package com.example.guojiawei.finderproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guojiawei.finderproject.R;
import com.tencent.bugly.crashreport.CrashReport;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_start);
        if (MainActivity.main == null) {
            startActivity(new Intent(StartActivity.this, SplashActivity.class));
            finish();
        } else {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    }
}
