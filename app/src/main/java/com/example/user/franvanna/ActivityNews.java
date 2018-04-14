package com.example.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.franvanna.Adapters.AdapterListNewsList;
import com.example.user.franvanna.Adapters.AdapterListVotesTypes;
import com.example.user.franvanna.Objects.NewsItem;
import com.example.user.franvanna.Objects.VoteType;
import com.example.user.franvanna.Utils.Utils;

import java.util.ArrayList;

public class ActivityNews extends AppCompatActivity implements AdapterListNewsList.Listener {


    ListView lvNewsList;
    private static final String TAG = "CENI";

    AdapterListNewsList adapterListNewsList;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        alertDialog = Utils.getNoConnDialog(this);

        lvNewsList = findViewById(R.id.lvNewsList);
        newsItems = NewsItem.getDummyData();
        adapterListNewsList = new AdapterListNewsList(this, newsItems, this);

        lvNewsList.setAdapter(adapterListNewsList);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_news));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lvNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " );
            }
        });

        if (!Utils.isOnline(this)){


            if(alertDialog != null) {
                alertDialog.show();
            }

        }
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
    public void onNewsItemClicked(NewsItem newsItem) {
        Log.e(TAG, "onNewsItemClicked: " );
    }
}
