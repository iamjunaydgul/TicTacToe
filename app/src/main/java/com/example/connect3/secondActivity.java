package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class secondActivity extends AppCompatActivity {

    int playerTurn=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameIsActive=true;
    //to check wo won 2D array

    int[][] gameWinningPositions= {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    //playAgain button and winning texView

    TextView winningMessage;

    public void dropIn(View view) {
        //0 for yellow and 1 for red
        ImageView clickedImage = (ImageView) view;
        //for tag on every image
        clickedImage.getTag();
        int tappedCounter = Integer.parseInt(clickedImage.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter]=playerTurn;
            clickedImage.setTranslationY(-1000);

            if (playerTurn == 0) {
                clickedImage.setImageResource(R.drawable.yellow);
                playerTurn = 1;
            } else {
                clickedImage.setImageResource(R.drawable.red);
                playerTurn = 0;
            }
            clickedImage.animate().translationYBy(1000f).setDuration(300);

            for(int[] gameWinningPosition:gameWinningPositions){
                if(gameState[gameWinningPosition[0]]==gameState[gameWinningPosition[1]] &&
                        gameState[gameWinningPosition[1]] == gameState[gameWinningPosition[2]] &&
                        gameState[gameWinningPosition[0]]!=2){

                    gameIsActive=false;

                    Intent intent=getIntent();
                    String x=intent.getStringExtra("firstPlayer");
                    String y=intent.getStringExtra("secondPlayer");

                    winningMessage=findViewById(R.id.winningMessage);
                    String winner=(y+" has won!");

                    if(gameState[gameWinningPosition[0]]==0){
                        winner=(x+" has won!");
                    }
                    winMessage(winner);

                }
                else{

                    boolean gameIsOver= true;
                    for(int gameStateCounter : gameState) {
                        if (gameStateCounter == 2)
                            gameIsOver = false;
                    }
                    if(gameIsOver) {
                        winMessage("Its a Draw");
                    }
                }
            }
        }
    }

    public void winMessage(String winner){
        TextView winMessage=findViewById(R.id.winningMessage);
        winMessage.setText(winner);
        LinearLayout layout=findViewById(R.id.playAgainLayout);
        layout.setTranslationY(-1000f);
        layout.setVisibility(VISIBLE);
        layout.animate().translationYBy(1000f).setDuration(300);
        layout.bringToFront();

    }

    public void playAgain(View view){

        gameIsActive=true;
        //first set the layout invisible
        LinearLayout layout=findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);

        //then set the values again same as first game started
        playerTurn=0;
        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }
        //then get every images view in our grid layout and make them disappear or back to zero

        GridLayout gridLayout=findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        RadioButton different=findViewById(R.id.RadioDifferent);

        if(different.isChecked()) {
            Intent intent = new Intent(this, mainActivity.class);
            startActivity(intent);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        TextView first=findViewById(R.id.firstPlayer);
        TextView second=findViewById(R.id.secondPlayer);

        Intent intent=getIntent();
        String x=intent.getStringExtra("firstPlayer");
        String y=intent.getStringExtra("secondPlayer");

        first.setText("1st Player:"+x);
        second.setText("2nd Player:"+y);

    }
}
