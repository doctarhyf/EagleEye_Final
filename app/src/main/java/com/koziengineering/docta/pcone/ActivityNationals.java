package com.koziengineering.docta.pcone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.koziengineering.docta.pcone.Adapters.AdapterNatCands;
import com.koziengineering.docta.pcone.Objects.LenNat;
import com.koziengineering.docta.pcone.Objects.VoteType;

import java.util.ArrayList;
import java.util.Scanner;

public class ActivityNationals extends AppCompatActivity implements AdapterNatCands.Listener {



    ListView lvNatCands;
    AdapterNatCands adapterNatCands;
    private ArrayList<LenNat> legNatCandsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationals);

        lvNatCands = findViewById(R.id.lvNatCands);

        getSupportActionBar().setTitle(R.string.cands_legs);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        legNatCandsList = getTerritoriesData();


        adapterNatCands = new AdapterNatCands(this, legNatCandsList, this);

        lvNatCands.setAdapter(adapterNatCands);




        //legNatCandsList.add(new LenNat(0, "KAND", "Yes"));
        //legNatCandsList.add(new LenNat(1, "KAND", "Yes"));
        //legNatCandsList.add(new LenNat(2, "KAND", "Yes"));



        /*lvNatCands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onNatCandClicked(legNatCandsList.get(position));
                Log.e("CENI_RDC", "onItemClick: dans le bon" );
            }
        });*/


    }

    //@Override
    public void onNatCandClicked(LenNat lenNat) {
        Log.e("NAT_KAND", "onQuestionClicked: listener");
        //Intent intent = new Intent(this, ActivityFAQQA.class);
        //Bundle data = new Bundle();
        //data.putString(KEY_FAQ_Q, question.getCandName());
        //data.putString(KEY_FAQ_R, question.getReponse());
        //intent.putExtras(data);
        //startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public ArrayList<LenNat> getTerritoriesData() {

        ArrayList<LenNat> curLegNatList = new ArrayList<>();

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.province));
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
            //Log.e("KKK",  splits[k] + k );
            curLegNatList.add(new LenNat(k, splits[k]));


            k++;
        }



        return curLegNatList;
    }

    @Override
    public void onLegCandClicked(VoteType voteType) {

    }
}
