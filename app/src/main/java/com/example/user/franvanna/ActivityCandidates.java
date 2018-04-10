package com.example.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.user.franvanna.Adapters.AdapterListVotesTypes;
import com.example.user.franvanna.Objects.VoteType;

import java.util.ArrayList;

public class ActivityCandidates extends AppCompatActivity implements AdapterListVotesTypes.Listener  {


    private static final String TAG = "CENI";
    ListView lvVotesTypes;
    AdapterListVotesTypes adapterListVotesTypes;
    private ArrayList<VoteType> voteTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates2);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_cands));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        voteTypes.add(new VoteType(0, getResources().getString(R.string.txt_vote_choice_prez)));
        voteTypes.add(new VoteType(1, getResources().getString(R.string.txt_vote_choice_leg_nat)));
        voteTypes.add(new VoteType(2, getResources().getString(R.string.txt_vote_choice_leg_prov)));

        lvVotesTypes = findViewById(R.id.lvVotesTypes);
        adapterListVotesTypes = new AdapterListVotesTypes(this, voteTypes, this);

        lvVotesTypes.setAdapter(adapterListVotesTypes);
    }

    public void onVoteTypeClicked(View view) {

        Log.e(TAG, "onVoteTypeClicked: " + view.getId() );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,  MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

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

    @Override
    public void onVoteTypeClicked(VoteType voteType) {

    }
}
