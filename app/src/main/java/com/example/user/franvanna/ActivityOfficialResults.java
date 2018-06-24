package com.example.user.franvanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.franvanna.Adapters.AdapterListVotesTypes;
import com.example.user.franvanna.Objects.VoteType;
import com.example.user.franvanna.Utils.Utils;

import java.util.ArrayList;

public class ActivityOfficialResults extends AppCompatActivity implements AdapterListVotesTypes.Listener {

    private static final String TAG = "CENI";
    ListView lvVotesTypes;
    AdapterListVotesTypes adapterListVotesTypes;
    private ArrayList<VoteType> voteTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_results);


        getSupportActionBar().setTitle(getResources().getString(R.string.title_officiel_result));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        voteTypes.add(new VoteType(0, getResources().getString(R.string.txt_vote_choice_prez)));
        voteTypes.add(new VoteType(1, getResources().getString(R.string.txt_vote_choice_leg_nat)));
        voteTypes.add(new VoteType(2, getResources().getString(R.string.txt_vote_choice_leg_prov)));

        lvVotesTypes = findViewById(R.id.lvVotesTypes);
        adapterListVotesTypes = new AdapterListVotesTypes(this, voteTypes, this);

        lvVotesTypes.setAdapter(adapterListVotesTypes);

        lvVotesTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " );
                Utils.showDialogWithMessage(ActivityOfficialResults.this, true, getResources().getString(R.string.strOptTitleNotAvail),
                        getResources().getString(R.string.strOptMsgNotAvail));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onVoteTypeClicked(VoteType voteType) {
        Log.e(TAG, "onVoteTypeClicked: " );
    }
}
