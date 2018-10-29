package com.koziengineering.docta.pcone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koziengineering.docta.pcone.Fragments.FragmentCardAnim;
import com.koziengineering.docta.pcone.Fragments.FragmentElections;
import com.koziengineering.docta.pcone.Fragments.FragmentElectionsPane;
import com.koziengineering.docta.pcone.Fragments.FragmentVoteSimWarning;
import com.koziengineering.docta.pcone.Objects.Candidate;
import com.koziengineering.docta.pcone.Utils.Utils;

public class ActivityVotes extends AppCompatActivity
    implements FragmentCardAnim.FragListenerFragCardAnim,
        FragmentElectionsPane.OnFragmentInteractionListener,
        FragmentElections.ListenerFragElecPrez,
        FragmentVoteSimWarning.OnFragmentInteractionListener
{

    private static final String TAG = "CENI";
    private static final long PANE_DELAY = 2500;
    FrameLayout fragCont;
    FragmentCardAnim fragmentCardAnim;
    FragmentElections fragmentElections;
    FragmentVoteSimWarning fragmentVoteSimWarning;
    private AlertDialog alertDialog = null;
    private MediaPlayer mp;
    private CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        mp = MediaPlayer.create(this, R.raw.avote_plam);

        fragCont = findViewById(R.id.fragContVotes);

        fragmentVoteSimWarning = FragmentVoteSimWarning.newInstance();
        fragmentCardAnim = FragmentCardAnim.newInstance("a", "b");
        fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_PREZ);



        getSupportFragmentManager().beginTransaction().add(R.id.fragContVotes, fragmentVoteSimWarning).commit();

        //Utils.replaceFragmentWithAnimation(this, R.id.fragContVotes, fragmentVoteSimWarning,"CENI");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().hide();
    }



    /*
    @Override
    public void onBackPressed() {
        //super.onBackPressed();



        if(alertDialog!= null){
            if(
                    alertDialog.isShowing()){
                Log.e(TAG, "onBackPressed: showing" );
                alertDialog.hide();
            }else{
                Log.e(TAG, "onBackPressed: not showing" );
                super.onBackPressed();
            }
        }else{
            Log.e(TAG, "onBackPressed: is null" );
            super.onBackPressed();
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "ONRIZZZ" );
        //getSupportFragmentManager().beginTransaction().remove( fragmentCardAnim).commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragContVotes, fragmentCardAnim).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContVotes, fragmentVoteSimWarning).commit();
        Utils.clearCandidatesData(this);
        fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_PREZ);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.e(TAG, "onBackPressed: " );

        if(alertDialog != null){
            //alertDialog.hide();

            Log.e(TAG, "NOTNULLL" );
        }else{
            Log.e(TAG, "NULLLA" );
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "REZA" );
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

        adjustDialogLayoutToScreenDensity(view);

        ImageView ivPartiLogo = view.findViewById(R.id.cbIvPartiLogo);
        ImageView ivCandPic = view.findViewById(R.id.cbIvCandPic);
        TextView tvFullName = view.findViewById(R.id.cbTvFullName);
        TextView tvPrenom = view.findViewById(R.id.cbTvPrenom);
        TextView tvPartiName = view.findViewById(R.id.cbTvPartiName);
        TextView tvCandNum = view.findViewById(R.id.cbTvNum);

        ivPartiLogo.setImageResource(candidate.getPartiLogo());
        ivCandPic.setImageResource(candidate.getPicId());
        tvFullName.setText(candidate.getNomPostnom());
        tvPrenom.setText(Utils.UCFirst(candidate.getPrenom()));
        tvPartiName.setText(candidate.getPartiName());
        tvCandNum.setText((candidate.getId() + 1) + "");//candidate.getId());





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

    private void adjustDialogLayoutToScreenDensity(View view) {

        int density = Utils.getScreenDensity(this);

        if(density <= 250){

            ImageView ivCandPic = view.findViewById(R.id.cbIvCandPic);
            ImageView ivPartiLogo = view.findViewById(R.id.cbIvPartiLogo);
            TextView tvPartiName = view.findViewById(R.id.cbTvPartiName);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60,60);
            ivCandPic.setLayoutParams(params);

            params = new LinearLayout.LayoutParams(60,60);
            ivPartiLogo.setLayoutParams(params);

            tvPartiName.setTextSize(9);

        }

    }

    @Override
    public void onVoteBlanckClicked(final int candType) {

        View view = getLayoutInflater().inflate(R.layout.layout_dialog_cand_vote_blanc, null);



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
                selectCandidateBlanc(candType);
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

    private void selectCandidateBlanc(int candType) {

        switch (candType){
            case Candidate.CAND_TYPE_PREZ:

                Utils.saveCandToPref(this, Utils.VOTE_KEY_CAND_PREZ, Candidate.CANDIDATE_BLANC);
                fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_NAT);
                showVotePane("Elections Legislatives Nationales", fragmentElections);

                break;

            case Candidate.CAND_TYPE_LEG_NAT:

                Utils.saveCandToPref(this, Utils.VOTE_KEY_CAND_DEP_NAT, Candidate.CANDIDATE_BLANC);
                fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_PROV);
                showVotePane("Election Legislatives Provinciales", fragmentElections);

                break;

            case Candidate.CAND_TYPE_LEG_PROV:

                Utils.saveCandToPref(this, Utils.VOTE_KEY_CAND_DEP_PROV, Candidate.CANDIDATE_BLANC);
                Intent intent = new Intent(this, ActivityPrintVoteResult.class);
                startActivity(intent);

                playVoteFXAndShowVotesResults();

                break;
        }

    }

    private void selectCandidate(Candidate candidate) {
        Log.e(TAG, "CANDIDATE SELECTED" );

        // TODO: 07/04/2018 WILL SAVE CANDS DATA IN PREFS

        if(candidate.getCandType() == Candidate.CAND_TYPE_PREZ){

            Utils.saveCandToPref(this,  Utils.VOTE_KEY_CAND_PREZ, candidate.getId());
            fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_NAT);
            showVotePane("Elections Legislatives Nationales", fragmentElections);


        }else if (candidate.getCandType() == Candidate.CAND_TYPE_LEG_NAT){

            Utils.saveCandToPref(this,  Utils.VOTE_KEY_CAND_DEP_NAT, candidate.getId());
            fragmentElections = FragmentElections.newInstance(Candidate.CAND_TYPE_LEG_PROV);
            showVotePane("Election Legislatives Provinciales", fragmentElections);

        } else {

            Utils.saveCandToPref(this, Utils.VOTE_KEY_CAND_DEP_PROV, candidate.getId());
            Intent intent = new Intent(this, ActivityPrintVoteResult.class);
            startActivity(intent);
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragContVotes, fragmentVoteSimWarning).commit();

            playVoteFXAndShowVotesResults();


        }
    }



    private void playVoteFXAndShowVotesResults() {

        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.avote_plam);
            } mp.start();



            final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);



                new CountDownTimer(3000, 300){

                    @Override
                    public void onTick(long millisUntilFinished) {

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));



                        }else {
                            vibrator.vibrate(200);

                        }

                        Utils.toggleTorchMode(ActivityVotes.this, cameraManager, torchModeOn);

                        torchModeOn = !torchModeOn;
                    }

                    @Override
                    public void onFinish() {

                        torchModeOn = false;
                        Utils.toggleTorchMode(ActivityVotes.this, cameraManager,false);

                    }
                }.start();



        } catch(Exception e) { e.printStackTrace(); }

    }

    private boolean torchModeOn = false;

    @Override
    public void onStartSimulationClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContVotes, fragmentCardAnim).commit();
        //Utils.replaceFragmentWithAnimation(this, R.id.fragContVotes, fragmentCardAnim,"CENI");
    }
}
