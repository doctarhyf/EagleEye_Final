package com.koziengineering.docta.pcone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.koziengineering.docta.pcone.Adapters.AdapterPrezList;
import com.koziengineering.docta.pcone.Adapters.PresItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActivityPresident extends AppCompatActivity {


    private static final String TAG = "CENI 2018";
    RecyclerView recyclerView;
    AdapterPrezList adapterPrezList;
    RecyclerView.LayoutManager layoutManager;
    List<PresItem> presItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president);


        getSupportActionBar().setTitle(getResources().getString(R.string.menu_cands_prez));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        presItems = new ArrayList<>();

        scanPresidentsData();
        Log.e(TAG, "onCreate: -> prez num " + presItems.size() );


        adapterPrezList = new AdapterPrezList(presItems);

        recyclerView = findViewById(R.id.rvListPres);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterPrezList);
        recyclerView.setLayoutManager(layoutManager);







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;



        if(item.getItemId() == android.R.id.home){
            //finish();

            intent = new Intent(this, ActivityCandidatesList.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void scanPresidentsData() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.pres_data));

        String listData = "";
        //Reading Data into String
        while (scanner.hasNextLine()){

            listData = listData.concat(scanner.nextLine());
            //Log.e("TAG", "List Data : -> " + listData );
        }

        String[] splits = listData.split(";");
        for (int i = 0 ; i < splits.length; i++){

            String prezData = splits[i];
            //Log.e(TAG, "scanPresidentsData: -> " + prezData );
            String[] prezDataSplit = prezData.split(",");

            PresItem presItem = new PresItem(prezDataSplit[0], prezDataSplit[1], Character.toString((char) (i + 97) ));
            presItems.add(presItem);

            Log.e(TAG, "scanPresidentsData: -> Da Pri Len -> " + presItems.size() );
 
        }



        //Log.e(TAG, "scanPresidentsData: -> " + splits[3]);
    }
}
