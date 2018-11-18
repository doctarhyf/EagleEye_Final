package com.koziengineering.user.franvanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.koziengineering.user.franvanna.Adapters.AdapterNatCands;
import com.koziengineering.user.franvanna.Objects.ELECT_DATA;
import com.koziengineering.user.franvanna.Objects.LenNat;
import com.koziengineering.docta.pcone.R;

import java.util.ArrayList;
import java.util.Scanner;

public class ActivityCandsListType extends AppCompatActivity implements AdapterNatCands.Listener {



    ListView lvNatCands;
    AdapterNatCands adapterNatCands;
    private ArrayList<LenNat> legNatCandsList;
    private TextView tvCandListTypeDataLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationals);

        tvCandListTypeDataLabel = findViewById(R.id.tvCandListTypeDataLabel);
        lvNatCands = findViewById(R.id.lvNatCands);

        getSupportActionBar().setTitle(R.string.cands_legs);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final int elecType = getIntent().getIntExtra(ActivityCandidatesList.CAND_LIST_TYPE, -1);
        legNatCandsList = getCandLIstData(elecType);




        adapterNatCands = new AdapterNatCands(this, legNatCandsList, this);

        lvNatCands.setAdapter(adapterNatCands);



        lvNatCands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                int listRowID = i;



                Log.e("TAG", "listRow : " + listRowID + ", " + elecType);

                if(elecType == ELECT_DATA.ELECT_TYPES_LEG_NAT){

                    //Log.e("EL!", "gota show territories" );
                    showTerritories(listRowID);
                }else{
                    Log.e("EL", "fuck " );
                }


            }
        });


    }

    private void showTerritories(int provID) {

        switch (provID){

            case ELECT_DATA.TERRITORIES_HAUT_KATANGA:
                Log.e("OK", "showTerritories: katanga " );
                adapterNatCands.clear();
                legNatCandsList = getCandLIstData(provID);

                adapterNatCands = new AdapterNatCands(this, legNatCandsList, this);
                //adapterNatCands.notifyAll();
                lvNatCands.setAdapter(adapterNatCands);
                break;


                default:
                    Log.e("TAG", "duuuuudeee" );
                    break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public ArrayList<LenNat> getCandLIstData(int dataType) {

        ArrayList<LenNat> curLegNatList = new ArrayList<>();

        Scanner scanner = null;
        switch (dataType){

            case ActivityCandidatesList.CAND_LIST_ELECTIONS_TYPE_LEG_NAT:
                scanner = new Scanner(getResources().openRawResource(R.raw.el_type_leg_nat));
                tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChooseProvince));
            break;

            case ELECT_DATA.TERRITORIES_LIST_HAUT_KATANGA:
                scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
                tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChooseTerritories));
                break;

            //case ActivityCandidatesList.CAND_LIST_TYPE_TERRITORIES_HAUT_KAT:
                //scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
                //tvCandListTypeDataLabel.setText(getResources().getString(R.strin
                //
                // g.txtChoseTerritories));
                //break;

            default:
                scanner = new Scanner(getResources().openRawResource(R.raw.territoires_haut_katanga));
                tvCandListTypeDataLabel.setText(getResources().getString(R.string.txtChoseTerritories));
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

        if(adapterNatCands != null) {
            adapterNatCands.notifyDataSetChanged();
        }
        return curLegNatList;
    }


}
