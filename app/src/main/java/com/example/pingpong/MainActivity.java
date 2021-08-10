package com.example.pingpong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        int value = 0;

        Button wallMode = findViewById(R.id.wallMode);
        wallMode.setOnClickListener(v -> {
            vibe.vibrate(100);
            Intent myIntent = new Intent(MainActivity.this, WallMode.class);
            MainActivity.this.startActivity(myIntent);
        });
    }
}