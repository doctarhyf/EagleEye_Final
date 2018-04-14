package com.example.user.franvanna;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.franvanna.Utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityVoteSimulation extends AppCompatActivity {


    private static final String TAG = "CENI";
    TextView tvCoundDown;
    GregorianCalendar dueDate;
    Date curDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_simulation);

        dueDate = new GregorianCalendar(2018, Calendar.DECEMBER, 15);



        tvCoundDown = findViewById(R.id.tvCoundDown);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().setTitle(getResources().getString(R.string.titleVoteSimulation));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //final Integer i = {0};
        final long ms = dueDate.getTimeInMillis();
        new CountDownTimer(ms, 1000){

            @Override
            public void onTick(long millisUntilFinished) {




                tvCoundDown.setText(Utils.getDateDifference(new Date(), dueDate.getTime()));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


   /* @Override
    public void onBackPressed() {

        //finish();
        Log.e(TAG, "onBackPressed: FUCK "  );
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Intent intent;

        if(item.getItemId() == android.R.id.home){
            finish();
        }


        /*

        if(item.getItemId() == R.id.setRowAccSettings){
            intent = new Intent(this, ActivityAccountSettings.class);
            startActivity(intent);
        }*/


        return super.onOptionsItemSelected(item);
    }

    public void startVote(View view) {

        Intent intent = new Intent(this, ActivityVotes.class);
        startActivity(intent);

    }
}
