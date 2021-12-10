package com.raywenderlich.xooynu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.raywenderlich.xooynu.PlayerSetup;
import com.raywenderlich.xooynu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonClick(View view){
        Intent intent = new Intent(MainActivity.this, PlayerSetup.class);
        intent.putExtra("game", 1);
        startActivity(intent);
    }

    public void playCompButtonClick(View view){
        Intent intent = new Intent(MainActivity.this, PlayerSetup.class);
        intent.putExtra("game", 2);
        startActivity(intent);
    }

}