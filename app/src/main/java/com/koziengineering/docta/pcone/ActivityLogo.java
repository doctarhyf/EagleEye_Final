package com.koziengineering.docta.pcone;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityLogo extends AppCompatActivity {

    Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        th = new Thread(){

            @Override
            public void run() {


                try {
                    sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), ActivityIntroVid.class);
                startActivity(intent);
                finish();
            }

        };


        th.start();
    }
}
