package com.koziengineering.user.franvanna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.koziengineering.user.franvanna.Adapters.AdapterListNewsList;
import com.koziengineering.user.franvanna.Objects.NewsItem;
import com.koziengineering.docta.pcone.R;

public class ActivityNews extends AppCompatActivity implements AdapterListNewsList.Listener {


    ListView lvNewsList;
    private static final String TAG = "CENI";

    //AdapterListNewsList adapterListNewsList;
    //private ArrayList<NewsItem> newsItems = new ArrayList<>();
    //private AlertDialog alertDialog = null;
    TextView tvError;
    WebView wvnews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);



        getSupportActionBar().setTitle(getResources().getString(R.string.title_news));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wvnews = findViewById(R.id.wvnews);



        wvnews.setInitialScale(1);
        wvnews.getSettings().setJavaScriptEnabled(true);
        wvnews.getSettings().setLoadWithOverviewMode(true);
        wvnews.getSettings().setUseWideViewPort(true);
        wvnews.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wvnews.setScrollbarFadingEnabled(false);
        //wvnews.loadDataWithBaseURL(null, Utils.readTextFromResource(this,R.raw.html_page_lois_elec), "text/html", "utf-8", null);
        wvnews.loadUrl("https://www.ceni.cd/rubriques/actualites");


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;



        if(item.getItemId() == android.R.id.home){
            finish();
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewsItemClicked(NewsItem newsItem) {
        Log.e(TAG, "onNewsItemClicked: " );
    }
}
