package com.koziengineering.user.franvanna;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class ActivityIntroVid extends AppCompatActivity {

    private VideoView videoIntro;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid_intro);

        getSupportActionBar().hide();

        videoIntro = findViewById(R.id.vidIntro);
        uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ceni_intro_vid);
        playVid();

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
}
