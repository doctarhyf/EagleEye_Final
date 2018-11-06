package com.koziengineering.docta.pcone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koziengineering.docta.pcone.Adapters.AdapterListVotesTypes;
import com.koziengineering.docta.pcone.Objects.VoteType;
import com.koziengineering.docta.pcone.Utils.Utils;

import java.util.ArrayList;

public class ActivityCandidatesList extends AppCompatActivity implements AdapterListVotesTypes.Listener  {


    private static final String TAG = "CENI";
    public static final int CAND_LIST_TYPE_TERRITORIES_HAUT_KAT = 1;
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

        lvVotesTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                showDataFromVoteType(position);






            }
        });
    }

    private void gotoDataDisplay(String dataDisplayType) {


        Intent intent = new Intent(this, ActivityDataDisplay.class);
        intent.putExtra(DataDisplay.KEY_DATA_DISPLAY_TYPE, dataDisplayType);

        startActivity(intent);


    }

    public static final int CAND_LIST_ELECTIONS_TYPE_LEG_NAT = 1;
    public static final int CAND_LIST_TYPE_TERRITORIES = 2;
    public static final String CAND_LIST_TYPE = "candListStep";


    private void gotoProvinces() {


        Intent intent = new Intent(this, ActivityCandsListType.class);
        intent.putExtra(CAND_LIST_TYPE, CAND_LIST_ELECTIONS_TYPE_LEG_NAT);
        startActivity(intent);



    }

    private void gotoTerritories(){
        Intent intent = new Intent(this, ActivityCandsListType.class);
        intent.putExtra(CAND_LIST_TYPE, CAND_LIST_TYPE_TERRITORIES);
        startActivity(intent);

    }





    public void onVoteTypeClicked(View view) {

        Log.e(TAG, "onVoteTypeClicked: " + view.getId() );
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


        showDataFromVoteType(voteType.getId());


    }

    private void showDataFromVoteType(int position){

        //Log.e(TAG, "onVoteTypeClicked: -> " + voteType.getId() );

        //int position = voteType.getId();

        if(position == 0){

            gotoDataDisplay(DataDisplay.PRESIDENTS);
            Utils.setPrompTextListData(getApplicationContext(), DataDisplay.CUR_WINDOW_TITLE, null);

        }else if(position == 1) {
            //gotoProvinces();
            gotoDataDisplay(DataDisplay.PROVINCES);
            Utils.setPrompTextListData(getApplicationContext(), DataDisplay.CUR_WINDOW_TITLE, getResources().getString(R.string.wt_first_choose_province));
            Utils.putStringData(getApplicationContext(), DataDisplay.KEY_DATA_PROV_LEVEL_NAT_OR_PROV, DataDisplay.DATA_PROV_LEVEL_NAT_OR_LEG_NATIONAL);
        }else if(position == 2) {
            //gotoTerritories();
            gotoDataDisplay(DataDisplay.TERRITORIES);
            Utils.setPrompTextListData(getApplicationContext(), DataDisplay.CUR_WINDOW_TITLE, getResources().getString(R.string.wt_first_choose_province));

        }else{
            Log.e(TAG, "onItemClick kak: " );
            Utils.showDialogWithMessage(ActivityCandidatesList.this, true, getResources().getString(R.string.strOptTitleNotAvail),
                    getResources().getString(R.string.strOptMsgNotAvail));

        }
    }


}
