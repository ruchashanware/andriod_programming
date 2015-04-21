package com.example.rucha.pairodice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.*;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    public int p1_score=0;
    public int p2_score=0;
    public int p1_round=0;

    public Intent intent_p1 = null;
    public Bundle scores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                holdDice();
            }
        });

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);


            scores = new Bundle();
            scores = getIntent().getExtras();
        if (scores != null) {
            Log.i("main","in if score != null !!!");
            p1_score = scores.getInt("p1_score");
            p2_score = scores.getInt("p2_score");

            TextView textEle = (TextView)findViewById(R.id.p1);
            textEle.setText("P1: "+p1_score);

            TextView textElement = (TextView)findViewById(R.id.p2);
            textElement.setText("P2: "+p2_score);

            Log.i("Main","p1_score from pla2 to Main :"+p1_score);
            Log.i("Main","p2_score from pla2 to Main :"+p2_score);
        }


    }

    public void holdDice(){
        Log.i("MainActivity", "p1 in holdDice____   ");

        Log.i("MainActivity", "p1 round ==== "+p1_round);

        p1_score = p1_round +  p1_score;

        Log.i("MainActivity", "p1 score  aftr p1_score = p1_round + p1_score   "+ p1_score);

        TextView textElement = (TextView)findViewById(R.id.p1);
        textElement.setText("P1: "+p1_score);

        if(p1_score >= 100)
        {
            Log.i("MainActivity", "p1 score inside if == 100");
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Player1 Won!");
            alertDialog.setMessage("You won PairODice");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            p1_round=0;
            p1_score = 0;
            p2_score= 0;
            TextView text_game_over_p2 = (TextView)findViewById(R.id.p1);
            text_game_over_p2.setText("P1: "+p1_score);

            TextView text_game_over_p1 = (TextView)findViewById(R.id.p2);
            text_game_over_p1.setText("P2: "+p2_score);

            TextView text_game_over_round = (TextView)findViewById(R.id.round);
            text_game_over_round.setText("Round: "+p2_score);

        }
        else{
            Log.i("main","p2_score in main holdDice() :"+p2_score);
            intent_p1 = new Intent(MainActivity.this,Player2.class);
            scores = new Bundle();
            scores.putInt("p1_score",p1_score);
            scores.putInt("p2_score",p2_score);
            intent_p1.putExtras(scores);
            intent_p1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent_p1);
        }

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());

        if (val1==1 || val2==1)
        {
           p1_round=0;
        /*    scores = new Bundle();
            scores = getIntent().getExtras();*/
            p1_score = scores.getInt("p1_score");

            TextView textElement = (TextView)findViewById(R.id.round);
            textElement.setText("Round: "+p1_round);

            Log.i("main","p1_score wen dice faces 1 in main rollDice() :"+p1_score);

           if(p1_round == 0 ){
                intent_p1 = new Intent(MainActivity.this,Player2.class);
                scores = new Bundle();
                scores.putInt("p1_score",p1_score);
                scores.putInt("p2_score",p2_score);
                intent_p1.putExtras(scores);
                intent_p1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent_p1);
            }

        }

        else{
        p1_round = p1_round + val1 + val2;

        }
        Log.i("MainActivity", "p1 round score *******" + p1_round);

        setDie(val1, die1);
        setDie(val2, die2);
        TextView textElement = (TextView)findViewById(R.id.round);
        textElement.setText("Round: "+p1_round);

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
