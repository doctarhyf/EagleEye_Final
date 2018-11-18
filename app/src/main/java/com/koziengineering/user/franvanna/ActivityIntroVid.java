package com.koziengineering.user.franvanna;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

import com.koziengineering.docta.pcone.R;

import java.util.Timer;

public class ActivityIntroVid extends AppCompatActivity implements Runnable {

    private VideoView videoIntro;
    private Uri uri;
    private Button btn;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid_intro);

        timer = new Timer();

        btn = findViewById(R.id.btnSkipVid);

        btn.setVisibility(View.GONE);

        Log.e("CHECK", "onCreate: " +  " debug" );

        getSupportActionBar().hide();

        videoIntro = findViewById(R.id.vidIntro);
        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ceni_intro_vid);
        playVid();

        videoIntro.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("GAAG", "onTouch: " );
                btn.setVisibility(View.VISIBLE);
                startHidingButton();
                return true;
            }
        });

        videoIntro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                skipVid();
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        /*
        Point size = Utils.getScreenSize(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Screen size")
                .setMessage("Width : " + size.x + "\nHeight : " + size.y + "\nDensity : " + Utils.getScreenDensity(this));

        builder.show();*/

    }

    private void startHidingButton() {

        final Button nbtn = findViewById(R.id.btnSkipVid);

        //nbtn.setVisibility(View.GONE);

        Log.e("ACT", "startHidingButton: Hidding" );


        final Handler handler = new Handler();

        Log.e("CHECK", "startHidingButton: " );

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Button btn = findViewById(R.id.btnSkipVid);
                btn.setVisibility(View.GONE);
            }
        },2000);
    }




    @Override
    protected void onResume() {
        super.onResume();
        playVid();
    }

    public void skipVid(){
        Intent intent = new Intent(this, ActivityMainMenu.class);
        startActivity(intent);
    }

    public void onSkipVid(View view) {

        skipVid();
    }

    private void playVid() {

        videoIntro.setVideoURI(uri);
        videoIntro.start();
    }

    @Override
    public void run() {
        Log.e("KODD", "run: coooooll" );
    }
}
