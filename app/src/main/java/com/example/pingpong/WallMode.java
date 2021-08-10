package com.example.pingpong;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.pingpong.databinding.ActivityWallModeBinding;

public class WallMode extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityWallModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new customViews(this);

    }
}