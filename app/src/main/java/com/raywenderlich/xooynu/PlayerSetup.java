package com.raywenderlich.xooynu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerSetup extends AppCompatActivity {
    private EditText editTextPlayer1, editTextPlayer2;
    private TextView textView4;
    private int gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);

        editTextPlayer1 = findViewById(R.id.editTextTextPersonName);
        editTextPlayer2 = findViewById(R.id.editTextTextPersonName2);
        textView4 = findViewById(R.id.textView4);

        Intent intent = getIntent();
        gameType = intent.getIntExtra("game", 0);

        if(gameType == 2){
            editTextPlayer2.setVisibility(View.INVISIBLE);
            textView4.setVisibility(View.INVISIBLE);
        }

    }

    public void submitButtonClick(View view){
        String player1 = editTextPlayer1.getText().toString();
        String player2 = "Computer";
        if(gameType == 1){
            player2 = editTextPlayer2.getText().toString();
        }
        Intent intent = new Intent(PlayerSetup.this, GameDisplay.class);
        intent.putExtra("player1",player1);
        intent.putExtra("player2", player2);
        intent.putExtra("game",gameType);
        startActivity(intent);
    }

}