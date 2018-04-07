package com.example.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityMainMenu extends AppCompatActivity implements AdapterMainMenu.CallbacksAdapterMenuItems {


    private static final String TAG = "EE2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<MenuItem> menuItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        menuItemsList = MainMenuItems.getMainMenuItems(getResources());

        getSupportActionBar().setTitle(R.string.title_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new AdapterMainMenu(this, menuItemsList, this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }

    private List<MenuItem> menuItemList;

    @Override
    public void onItemClicked(MenuItem menuItem) {

        Intent intent = null;

        Log.e(TAG, "onItemClicked: " );

        int id = menuItem.getId();
        switch (id){

            case R.id.mainMenuCands:
                intent = new Intent(this, ActivityCandidates.class);
                break;

            case R.id.mainMenuVoteSimulation:
                intent = new Intent(this, ActivityVoteSimulation.class);
                break;



            case R.id.mainMenuListElec:
                intent = new Intent(this, ActivityListElectorral.class);
                break;

            case R.id.mainMenuResults:
                intent = new Intent(this, ActivityResults.class);
                break;

            case R.id.mainMenuBuroVote:
                intent = new Intent(this, ActivityBureauVote.class);
                break;

            case R.id.mainMenuFaq:
                intent = new Intent(this, ActivityFAQ.class);
                Log.e(TAG, "onItemClicked: FAQ " );
                break;


        }




        if(null != intent) {

            startActivity(intent);

        }
    }
}
