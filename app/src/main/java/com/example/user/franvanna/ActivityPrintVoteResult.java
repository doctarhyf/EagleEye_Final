package com.example.user.franvanna;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.franvanna.Adapters.AdapterVotesResults;
import com.example.user.franvanna.Data.CandidatesData;
import com.example.user.franvanna.Objects.Candidate;
import com.example.user.franvanna.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ActivityPrintVoteResult extends AppCompatActivity {

    private static final String TAG = "CENI";
    private static final long RES_ANIM_DURATION = 500;
    private static final float INIT_TRANSLATION = 1000;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private List<Candidate> candidates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_vote_result);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_print_vote_results));
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /*
        ImageView iv = findViewById(R.id.iv);*/



        candidates = getWinningCandidates();

        recyclerView = findViewById(R.id.rvVoteResults);
        adapter = new AdapterVotesResults(this, this, candidates);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        /*
        Animation testAnim = AnimationUtils.loadAnimation(this, R.anim.anim_test);
        recyclerView.startAnimation(testAnim);*/

        //recyclerView.animate().alpha()
        animateResult();


        //recyclerView.setVisibility(0);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == Utils.REQ_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS GRANT" );
            }else{
                Toast.makeText(this, getResources().getString(R.string.strNeedWritePerms), Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void animateResult() {
        recyclerView.setTranslationY(INIT_TRANSLATION);

        Animation animTrans = new TranslateAnimation(0, 0,0, -INIT_TRANSLATION);
        animTrans.setDuration(RES_ANIM_DURATION);
        animTrans.setFillAfter(true);
        recyclerView.startAnimation(animTrans);

        /*
        Animation animAlpha = new AlphaAnimation(0f,100f);
        animTrans.setDuration(RES_ANIM_DURATION);
        animTrans.setFillAfter(true);
        recyclerView.startAnimation(animAlpha);*/
    }

    private List<Candidate> getWinningCandidates() {
        List<Candidate> candidates = new ArrayList<>();

        Candidate candidateBlanc = new Candidate();
        candidateBlanc.setId(-1);

        Log.e(TAG, "VOTE_REZ PREZ : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_PREZ) );
        Log.e(TAG, "VOTE_REZ NAT : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_NAT) );
        Log.e(TAG, "VOTE_REZ PROV : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_PROV) );

        int prez = Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_PREZ) ;
        int depNat = Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_NAT) ;
        int depProv = Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_PROV) ;


        if(prez != -1) {
            candidates.add(CandidatesData.getWinningCandidate(this, Candidate.CAND_TYPE_PREZ, prez));
        }else{
            // TODO: 10/04/2018 PREZ VOTE BLANC
            candidateBlanc.setCandType(Candidate.CAND_TYPE_PREZ);
            candidates.add(candidateBlanc);

        }

        if(depNat != -1) {
            candidates.add(CandidatesData.getWinningCandidate(this, Candidate.CAND_TYPE_LEG_NAT, depNat));
        }else{
            // TODO: 10/04/2018 NAT VOTE BLANC
            candidateBlanc.setCandType(Candidate.CAND_TYPE_LEG_NAT);
            candidates.add(candidateBlanc);


        }

        if(depProv != -1) {
            candidates.add(CandidatesData.getWinningCandidate(this, Candidate.CAND_TYPE_LEG_PROV, depProv));
        }else{
            // TODO: 10/04/2018 PROV VOTE BLANC
            candidateBlanc.setCandType(Candidate.CAND_TYPE_LEG_PROV);
            candidates.add(candidateBlanc);


        }

        Utils.clearCandidatesData(this);

        return candidates;
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, ActivityVotes.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_print_vote_res, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        if(item.getItemId() == R.id.actionMainMenu){
            Intent intent = new Intent(this, ActivityMainMenu.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.actionRestartVote){
            Intent intent = new Intent(this, ActivityVotes.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void shareResults(View view) {

        switch (view.getId()){

            /*
            case R.id.btnShareResultsTwitter:

                String tweetUrl = "https://twitter.com/intent/tweet?text=WRITE YOUR MESSAGE HERE &url="
                        + "https://www.google.com";
                Uri uri = Uri.parse(tweetUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

                break;*/

            case R.id.btnShareResultsImage:

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(perms, Utils.REQ_CODE);

                    Toast.makeText(this, getResources().getString(R.string.strNeedWritePerms), Toast.LENGTH_SHORT).show();

                }else {

                    LinearLayout linearLayout = findViewById(R.id.llResultsTicket);

                    Bitmap bitmap = Utils.getBitmapFromView(linearLayout);

                    String msg = String.format(getResources().getString(R.string.strVotesSimResultsShare), Utils.PLAYSTORE_URL);


                    Utils.shareImage(this, bitmap, msg);
                }

                break;
        }
    }


}
