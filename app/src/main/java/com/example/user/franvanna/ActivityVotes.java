package com.example.user.franvanna;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class ActivityVotes extends AppCompatActivity
    implements FragmentCardAnim.FragListenerFragCardAnim,
        FragmentElectionsPane.OnFragmentInteractionListener,
        FragmentElections.ListenerFragElecPrez
{

    private static final String TAG = "CENI";
    private static final long PANE_DELAY = 2500;
    FrameLayout fragCont;
    FragmentCardAnim fragmentCardAnim;
    FragmentElections fragmentElections;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        fragCont = findViewById(R.id.fragContVotes);

        fragmentCardAnim = FragmentCardAnim.newInstance("a", "b");
        fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_PREZ);

        getSupportFragmentManager().beginTransaction().add(R.id.fragContVotes, fragmentCardAnim).commit();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().hide();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        alertDialog.cancel();

        finish();
    }


    @Override
    public void onInsertCard() {
        Log.e(TAG, "onInsertCard: "  );


        showVotePane("Election Presidentielle", fragmentElections);
    }

    FragmentElectionsPane fragmentElectionsPane = null;

    private void showVotePane(String paneTitle, final Fragment nextFragment) {


        fragmentElectionsPane = FragmentElectionsPane.newInstance(paneTitle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContVotes, fragmentElectionsPane).commit();


        if (nextFragment != null) {


            new CountDownTimer(PANE_DELAY, 1000) {
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
    public void onCandidateClicked(final Candidate candidate) {

        Log.e(TAG, "onCandidateClicked: -> " + candidate.getNomPostnom() );

        View view = getLayoutInflater().inflate(R.layout.layout_dialog_cand_badge, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        builder.setCancelable(false);

        alertDialog = builder.create();

        Button btnConfirm = view.findViewById(R.id.btnCandBadgeConfirm);
        Button btnCancel = view.findViewById(R.id.btnCandBadgeCanc);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                selectCandidate(candidate);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });



        alertDialog.show();

        //Toast.makeText(this, "CAND CLICKED", Toast.LENGTH_LONG).show();



    }



    private void selectCandidate(Candidate candidate) {
        Log.e(TAG, "CANDIDATE SELECTED" );

        if(candidate.getCandType() == Candidate.CAND_TYPE_PREZ){

            fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_NAT);
            showVotePane("Elections Legislatives Nationales", fragmentElections);


        }else if (candidate.getCandType() == Candidate.CAND_TYPE_LEG_NAT){
            fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_PROV);
            showVotePane("Election Legislatives Provinciales", fragmentElections);
        } else {

            Intent intent = new Intent(this, ActivityPrintVoteResult.class);
            startActivity(intent);

        }
    }
}
