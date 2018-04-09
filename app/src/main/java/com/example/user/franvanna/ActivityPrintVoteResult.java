package com.example.user.franvanna;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.user.franvanna.Utils.Utils;

public class ActivityPrintVoteResult extends AppCompatActivity {

    private static final String TAG = "CENI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_vote_result);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_print_vote_results));
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ImageView iv = findViewById(R.id.iv);
        Animation testAnim = AnimationUtils.loadAnimation(this, R.anim.anim_test);
        iv.startAnimation(testAnim);

        Log.e(TAG, "VOTE_REZ PREZ : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_PREZ) );
        Log.e(TAG, "VOTE_REZ NAT : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_NAT) );
        Log.e(TAG, "VOTE_REZ PROV : " + Utils.getCandFromPref(this, Utils.VOTE_KEY_CAND_DEP_PROV) );

        Utils.clearCandidatesData(this);

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

        if(item.getItemId() == R.id.actionRestartVote){
            Intent intent = new Intent(this, ActivityVotes.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
