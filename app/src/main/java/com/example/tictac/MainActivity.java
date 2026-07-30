package com.example.tictac;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    //player representation
    // let 0=X
    // 1=O
    // 2=null
    int activeplayer =0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void playerTap(View view){
     ImageView img=(ImageView) view;
     int tappedImage=Integer.parseInt(img.getTag().toString());
     if(!gameActive){
         gameReset(view);
     }
        if (gameState[tappedImage]==2) {
            gameState[tappedImage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.x);
                activeplayer = 1;
                TextView status =findViewById(R.id.status);
                status.setText("O : Turn tap to play ");
            } else {
                img.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView status =findViewById(R.id.status);
                status.setText("X : Turn tap to play ");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //for checking if any player won or not
        
        for(int[] winPosition: winPosition) {
             if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                     gameState[winPosition[1]] == gameState[winPosition[2]] &&
                     gameState[winPosition[0]] != 2) {
                 //someone won the game
                 String winnerStr;
                 gameActive=false;
                 if (gameState[winPosition[0]] == 0) {
                    winnerStr="Congratulations! X has won";

                 }
                  else{
                      winnerStr="Congratulations! O has won";
                 }

                  //update the status bar to update winne
                 TextView status =findViewById(R.id.status);
                  status.setText(winnerStr);
             }

         }
    }

   

    public void gameReset(View view){
        gameActive=true;
        activeplayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}