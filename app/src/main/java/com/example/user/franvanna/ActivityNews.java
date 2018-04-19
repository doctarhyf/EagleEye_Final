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
import android.widget.TextView;

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
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        tvError = findViewById(R.id.tvError);
        alertDialog = Utils.getNoConnDialog(this);

        lvNewsList = findViewById(R.id.lvNewsList);
        newsItems = new ArrayList<>(); //NewsItem.getDummyData();
        adapterListNewsList = new AdapterListNewsList(this, newsItems, this);

        lvNewsList.setAdapter(adapterListNewsList);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_news));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(newsItems.size() == 0){
            lvNewsList.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(getResources().getString(R.string.strErrNoNewsItems));
        }


        lvNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " );
            }
        });

        if (!Utils.isOnline(this)){


            if(alertDialog != null) {
                alertDialog.show();
                lvNewsList.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(getResources().getString(R.string.strNoConnMsg));
            }else {
                tvError.setVisibility(View.GONE);
                lvNewsList.setVisibility(View.VISIBLE);
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
