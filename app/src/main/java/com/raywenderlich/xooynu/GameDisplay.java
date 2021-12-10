package com.raywenderlich.xooynu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Random;

public class GameDisplay extends AppCompatActivity {
    private ImageView[] buttons = new ImageView[9];
    private int count = 0;
    private int player1Points = 0;
    private int player2Points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private int gameType;
    private String firstPlayerName ="";
    private String secondPlayerName = "";

    private int[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        Intent intent = getIntent();
        gameType = intent.getIntExtra("game", 0);
        firstPlayerName = intent.getStringExtra("player1");
        secondPlayerName = intent.getStringExtra("player2");

        textViewPlayer1 = findViewById(R.id.textViewPlayer1);
        textViewPlayer2 = findViewById(R.id.textViewPlayer2);

        textViewPlayer1.setText(firstPlayerName +" : 0");
        textViewPlayer2.setText(secondPlayerName +" : 0");

        for (int i = 0; i < 9; i++) {
            String buttonId = "imageView" + i;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = findViewById(resId);
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(array[finalI] != 0 ){
                        return;
                    }
                    button(v, finalI);
                    count++;
                    System.out.println(count);
                    int result = checkWins();
                    resultWins(v, result);
                    if(result == 1 || result == 2){
                        finishGame();
                    }
                    if (result == 0 && count != 0 && gameType == 2) {
                        buttonComp();
                        count++;
                        result = checkWins();
                        resultWins(v, result);
                        if(result == 1 || result == 2){
                            finishGame();
                        }
                    }
                }
            });
        }
    }

    public void buttonComp() {
        Random random = new Random();
        boolean checkComputer = true;

        while (checkComputer) {
            int n = random.nextInt(9);
            if (array[n] != 1 && array[n] != 2) {
                array[n] = 2;
                buttons[n].setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.o_photo));
                checkComputer = false;
            }
        }
    }

    public void button(View view, int i) {
        ImageView imageView = (ImageView) view;
        int id = view.getId();
        if(array[i] != 0 ){
            return;
        }
        if (count % 2 == 0) {
            imageView.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.x_photo));
            array[i] = 1;
        } else if ( gameType == 1 && count % 2 == 1) {
            imageView.setImageResource(R.drawable.o_photo);
            array[i] = 2;
        }
    }

    private int checkWins() {
        int[][] lines = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6},
        };
        for (int i = 0; i < lines.length; i++) {
            int a = lines[i][0];
            int b = lines[i][1];
            int c = lines[i][2];
            if (array[a] != 0 && array[a] == array[b] && array[a] == array[c]) {
                return array[a];
            }
        }
        return 0;
    }

    public void resultWins(View v, int result) {
        if (result == 1) {
            //First Player win
            player1Points++;
            count = 0;
            textViewPlayer1.setText(firstPlayerName + " : " + player1Points);
            Snackbar.make(v, firstPlayerName + " Player won", Snackbar.LENGTH_SHORT).show();
            //waitAndRecreate();
        } else if (result == 2 ) { //&& gameType == 1
            player2Points++;
            count = 0;
            //Second Player win
            textViewPlayer2.setText(secondPlayerName + " : " + player2Points);
            Snackbar.make(v, secondPlayerName + " Player won", Snackbar.LENGTH_SHORT).show();
            //waitAndRecreate();
        } else {
            if (count == 9) {
                //tie
                count = 0;
                Snackbar.make(v, "Nobody won", Snackbar.LENGTH_SHORT).show();
                //waitAndRecreate();
            }
        }
    }

    public void finishGame() {
        Arrays.fill(array, 0);
        count = 0;
        for (ImageView b : buttons) {
            b.setImageResource(0);
        }
    }

    public void playAgainButtonClick(View view){
        finishGame();
    }

    public void homeButtonClick(View view){
        Intent intent = new Intent(GameDisplay.this, MainActivity.class);
        startActivity(intent);
    }

}