package com.example.user.franvanna;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.user.franvanna.Adapters.AdapterMainMenu;
import com.example.user.franvanna.Data.MainMenuItemsData;
import com.example.user.franvanna.Objects.MenuItem;
import com.example.user.franvanna.Utils.Utils;

import java.util.List;

public class ActivityMainMenu extends AppCompatActivity implements AdapterMainMenu.CallbacksAdapterMenuItems {

    private static final int REQ_CODE = 1001;
    private List<MenuItem> menuItemList;
    private static final String TAG = "EE2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<MenuItem> menuItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        menuItemsList = MainMenuItemsData.getMainMenuItems(getResources());

        getSupportActionBar().setTitle(R.string.title_menu);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new AdapterMainMenu(this, menuItemsList, this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);



        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(perms, Utils.REQ_CODE);


        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQ_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS GRANT" );
            }else{
                Toast.makeText(this, "On a besoin de votre permission pour exporter le resultat de la simulation des votes.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        if(item.getItemId() == R.id.actionPlayIntroVid){
            Intent intent = new Intent(this, ActivityIntroVid.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.actionAbout){
            Toast.makeText(this, "CENI 2018 All rights reserved", Toast.LENGTH_LONG).show();
        }

        return true;
    }

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
                intent = new Intent(this, ActivityOfficialResults.class);
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
