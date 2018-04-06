package com.example.user.franvanna;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.concurrent.Delayed;

public class ActivityVotes extends AppCompatActivity
    implements FragmentCardAnim.FragListenerFragCardAnim,
        FragmentElectionsPane.OnFragmentInteractionListener,
        FragmentElecPrez.ListenerFragElecPrez
{

    private static final String TAG = "CENI";
    private static final long PANE_DELAY = 2500;
    FrameLayout fragCont;
    FragmentCardAnim fragmentCardAnim;
    FragmentElecPrez fragmentElecPrez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        fragCont = findViewById(R.id.fragContVotes);

        fragmentCardAnim = FragmentCardAnim.newInstance("a", "b");
        fragmentElecPrez = FragmentElecPrez.newInstance();

        getSupportFragmentManager().beginTransaction().add(R.id.fragContVotes, fragmentCardAnim).commit();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().hide();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onInsertCard() {
        Log.e(TAG, "onInsertCard: "  );


        showVotePane("Election Presidentielle",  fragmentElecPrez);
    }

    FragmentElectionsPane fragmentElectionsPane = null;

    private void showVotePane(String paneTitle, final Fragment nextFragment) {


        fragmentElectionsPane = FragmentElectionsPane.newInstance(paneTitle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContVotes, fragmentElectionsPane).commit();


        if (nextFragment != null) {


            CountDownTimer timer = new CountDownTimer(PANE_DELAY, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.e(TAG, "onTick: ");
                }

                @Override
                public void onFinish() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragContVotes, nextFragment).commit();
                }
            }.start();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCandidateClicked(Candidate candidate) {

        Log.e(TAG, "onCandidateClicked: -> " + candidate.getNomPostnom() );
    }
}
