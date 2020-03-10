package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mainActivity extends AppCompatActivity {

    Button playGame;
    EditText playerOne,playerTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne=findViewById(R.id.playerOne);
        playerTwo=findViewById(R.id.twoPlayer);
        playGame=findViewById(R.id.playGame);


        playerOne.addTextChangedListener(playTheGame);
        playerTwo.addTextChangedListener(playTheGame);




    }
    private TextWatcher playTheGame = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            final String firstPlayerName=playerOne.getText().toString().trim();
            final String secondPlayerName=playerTwo.getText().toString().trim();
            playGame.setEnabled(!firstPlayerName.isEmpty() && !secondPlayerName.isEmpty());
            if(playGame.isEnabled()){
                playGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                        intent.putExtra("firstPlayer", firstPlayerName);
                        intent.putExtra("secondPlayer", secondPlayerName);
                        startActivity(intent);
                    }
                });
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    }

