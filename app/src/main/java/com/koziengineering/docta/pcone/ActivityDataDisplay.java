package com.koziengineering.docta.pcone;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.koziengineering.docta.pcone.Fragments.FragmentListData;
import com.koziengineering.docta.pcone.Fragments.dummy.DummyContent;
import com.koziengineering.docta.pcone.Objects.ELECT_DATA;
import com.koziengineering.docta.pcone.Objects.LenNat;
import com.koziengineering.docta.pcone.Utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class ActivityDataDisplay extends AppCompatActivity implements FragmentListData.OnListFragmentInteractionListener{


    private static final String TAG = "CENI";
    private String dataDisplayType = null;
    private FragmentListData fragmentListData;
    FragmentManager fragmentManager;
    LinearLayout fragCont;
    private String windowTitle = "CENI 2018";
    private TextView listDataPromptText;
    ListView listViewPrezoe;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);


        listDataPromptText = findViewById(R.id.listDataPromptText);


        //recyclerView = new RecyclerView()
        listViewPrezoe = findViewById(R.id.rvListPres);

        dataDisplayType = getIntent().getStringExtra(DataDisplay.KEY_DATA_DISPLAY_TYPE);
        windowTitle = Utils.getPrompTextListData(getApplicationContext(), DataDisplay.CUR_WINDOW_TITLE, "CENI 2018");//getIntent().getStringExtra(DataDisplay.CUR_WINDOW_TITLE);

        fragCont = findViewById(R.id.fragCont);
        fragmentManager = getSupportFragmentManager();





        if(dataDisplayType != null) {
            loadData(dataDisplayType);
        }else {
            Log.e(TAG, "onCreate: dataDisplayType is null"  );
        }


        getSupportActionBar().setTitle("CENI 2018");
        if(!windowTitle.equals(Utils.NO_PROMP_TEXT_DATA)) {
            listDataPromptText.setText(windowTitle);
        }else{
            listDataPromptText.setVisibility(View.GONE);

        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;



        if(item.getItemId() == android.R.id.home){
            //finish();
            intent = new Intent(this, ActivityCandidatesList.class);
            startActivity(intent);

            Log.e(TAG, "onOptionsItemSelected: -> koo" );
        }



        return super.onOptionsItemSelected(item);
    }

    private void loadData(String dataDisplayType) {



        if(dataDisplayType.equals(DataDisplay.PRESIDENTS)){


            //Log.e(TAG, "loadData: presidents" );

            Intent intent = new Intent(this, ActivityPresident.class);
            startActivity(intent);







        }

        if(dataDisplayType.equals(DataDisplay.PROVINCES)){


            String provinceData = getListData(DataDisplay.PROVINCES);

            fragmentListData = FragmentListData.newInstance(-1, provinceData);
            fragmentManager.beginTransaction().add(R.id.fragCont, fragmentListData).commit();
            Log.e(TAG, "loadData: showing provinces" );


        }

        if(dataDisplayType.equals(DataDisplay.TERRITORIES)){


            String territoriesData = getListData(DataDisplay.TERRITORIES);

            fragmentListData = FragmentListData.newInstance(-1, territoriesData);
            fragmentManager.beginTransaction().add(R.id.fragCont, fragmentListData).commit();
            Log.e(TAG, "loadData: showing territories" );

        }

    }

    private String getListData(String dataID) {



        int dataFileID = DataDisplay.GetListDataFileID(dataID);




        Scanner scanner = new Scanner(getResources().openRawResource(dataFileID));

        String listData = "";
        //Reading Data into String
        while (scanner.hasNextLine()){

            listData = listData.concat(scanner.nextLine());
            //Log.e("TAG", "List Data : -> " + listData );
        }

        String[] splits = listData.split(",");



        //Log.e(TAG, "data: " + listData );




        return listData;

    }


    public ArrayList<LenNat> getCandLIstData(int dataType) {

        ArrayList<LenNat> curLegNatList = new ArrayList<>();

        Scanner scanner = null;
        switch (dataType){

            case ActivityCandidatesList.CAND_LIST_ELECTIONS_TYPE_LEG_NAT:
                scanner = new Scanner(getResources().openRawResource(R.raw.el_type_leg_nat));
                //tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChooseProvince));
                break;

            case ELECT_DATA.TERRITORIES_LIST_HAUT_KATANGA:
                scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
                //tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChooseTerritories));
                break;

            //case ActivityCandidatesList.CAND_LIST_TYPE_TERRITORIES_HAUT_KAT:
            //scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
            //tvCandListTypeDataLabel.setText(getResources().getString(R.strin
            //
            // g.txtChoseTerritories));
            //break;

            default:
                scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
                //tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChoseTerritories));
                break;
        }
        //int k = 0;
        String faqData = "";
        while (scanner.hasNextLine()){

            faqData = faqData.concat(scanner.nextLine());

            //Log.e("TAG", "getFAQData: -> " + faqData );
            //lenNat.set(k, k + " ");
            //k++;


        }


        String[] splits = faqData.split(",");

        // Log.e("Split", splits.length + " " );

        for(int k = 0; k < splits.length; k++){


            //legNatCandsList.add(new LenNat(k,"Test", splits[k + 1]));
            Log.e("KKK",  splits[k] + k );
            curLegNatList.add(new LenNat(k, splits[k]));


            k++;
        }

        /*if(adapterNatCands != null) {
            adapterNatCands.notifyDataSetChanged();
        }*/
        return curLegNatList;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.ListItem item) {
        //Log.e(TAG, "onListFragmentInteraction: touched" );

        String data = Utils.getPrompTextListData(this, DataDisplay.KEY_DATA_PROV_LEVEL_NAT_OR_PROV, null);
        //Log.e(TAG, "onListFragmentInteraction: data -> " + data );

        //if(true){//dataDisplayType.equals(DataDisplay.DATA_PROV_LEVEL_NAT_OR_LEG_NATIONAL)){
            //Log.e(TAG, "onListFragmentInteraction: legNat, prov " + item.content );
        //}

        Log.e(TAG, "onListFragmentInteraction: ddt -> " + dataDisplayType );


    }
}
