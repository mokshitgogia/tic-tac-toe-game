package com.example.tictaktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int activeplayer=1;
    int flag=0;
    int[] gamestate={ 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img=(ImageView) view;
        int tappedImage= Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
            return;
        }
        if(gamestate[tappedImage] == 0) {
            if(flag==1)
            {
                flag=0;
            }
            else {
                gamestate[tappedImage] = activeplayer;
                img.setTranslationY(-1000f);

                if (activeplayer == 1) {
                    img.setImageResource(R.drawable.x);
                    activeplayer = 2;
                    TextView status = findViewById(R.id.status);
                    status.setText("O's Turn - Tap to play");
                } else {
                    img.setImageResource(R.drawable.o);
                    activeplayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn - Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
        }
        for(int[] winPosition: winPositions) {
            if (gamestate[winPosition[0]] == gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]] == gamestate[winPosition[2]] &&
                    gamestate[winPosition[0]] != 0) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gamestate[winPosition[0]] == 1) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                flag=1;
            }
        }
        boolean emptySquare = false;
        for (int squareState : gamestate) {
            if (squareState == 0) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }
    public void gameReset(View view) {
        gameActive = true;
        activeplayer = 1;
        for(int i=0; i<gamestate.length; i++){
            gamestate[i] = 0;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}