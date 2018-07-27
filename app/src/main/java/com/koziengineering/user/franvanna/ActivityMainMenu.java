package com.koziengineering.user.franvanna;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.koziengineering.user.franvanna.Adapters.AdapterMainMenu;
import com.koziengineering.user.franvanna.Data.MainMenuItemsData;
import com.koziengineering.user.franvanna.Objects.MenuItem;
import com.koziengineering.user.franvanna.Utils.Utils;

import java.util.List;
import java.util.Map;

public class ActivityMainMenu extends AppCompatActivity implements AdapterMainMenu.CallbacksAdapterMenuItems {

    private static final int REQ_CODE = 1001;
    private static final int PERM_CODE_READ_SMS = 1002;
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



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_SMS};//, Manifest.permission.READ_CONTACTS};
                requestPermissions(perms, Utils.REQ_CODE);


            }



        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //finish();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQ_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS GRANT" );
            }else{
                Toast.makeText(this, "On a besoin de votre permission pour exporter le resultat de la simulation des votes.", Toast.LENGTH_SHORT).show();
            }

            if(grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS SMS GRANT" );
            }else{
                Toast.makeText(this, "On a besoin de votre permission pour assurer la bon fonctionement de l'appli.", Toast.LENGTH_SHORT).show();
            }

            if(grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS SMS GRANT" );
            }else{
                Toast.makeText(this, "On a besoin de votre permission pour assurer la bon fonctionement de l'appli.", Toast.LENGTH_SHORT).show();
            }

            if(grantResults.length > 0 && grantResults[3] == PackageManager.PERMISSION_GRANTED){
                Log.e(TAG, "onRequestPermissionsResult: PERMS SMS GRANT" );
            }else{
                Toast.makeText(this, "On a besoin de votre permission pour assurer la bon fonctionement de l'appli.", Toast.LENGTH_SHORT).show();
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
            //Toast.makeText(this, "CENI 2018 All rights reserved", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ActivityAbout.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.actionLoisElec){
            Intent intent = new Intent(this, ActivityLoisElec.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onMenuItemClicked(MenuItem menuItem) {

        Intent intent = null;

        Log.e(TAG, "onMenuItemClicked: " );

        int id = menuItem.getId();
        switch (id){

            case MainMenuItemsData.MAIN_MENU_CANDS:
                intent = new Intent(this, ActivityCandidates.class);
                break;

            case MainMenuItemsData.MAIN_MENU_VOTE_SIMULATION:
                intent = new Intent(this, ActivityVoteSimulation.class);
                break;



            case MainMenuItemsData.MAIN_MENU_LIST_ELEC:
                intent = new Intent(this, ActivityListElectorral.class);
                break;

            case MainMenuItemsData.MAIN_MENU_OFFICIAL_RESULTS:
                intent = new Intent(this, ActivityOfficialResults.class);
                break;

            case MainMenuItemsData.MAIN_MENU_BUREAU_VOTE:
                intent = new Intent(this, ActivityBureauVote.class);
                break;

            case MainMenuItemsData.MAIN_MENU_FAQ:
                intent = new Intent(this, ActivityFAQ.class);

                break;

            case MainMenuItemsData.MAIN_MENU_CONTACT_US:
                intent = new Intent(this, ActivityContactUs.class);

                break;

            case MainMenuItemsData.MAIN_MENU_NEWS:
                intent = new Intent(this, ActivityNews.class);
                break;

            case MainMenuItemsData.MAIN_MENU_SHARE_APP:
                intent = new Intent(this, ActivityShareApp.class);

                break;








        }




        if(null != intent) {

            startActivity(intent);

        }
    }
}
