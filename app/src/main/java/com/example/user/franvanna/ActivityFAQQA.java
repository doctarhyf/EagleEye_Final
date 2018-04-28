package com.example.user.franvanna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityFAQQA extends AppCompatActivity {

    private static final String TAG = "EE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqq);

        getSupportActionBar().setTitle(R.string.vt_faq);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvQ = findViewById(R.id.tvFAQQAQuest);
        TextView tvR = findViewById(R.id.tvFAQQARep);

        Bundle data = getIntent().getExtras();

        tvQ.setText(data.getString(ActivityFAQ.KEY_FAQ_Q));
        String rep = data.getString(ActivityFAQ.KEY_FAQ_R);
        //rep = rep.replace("\\\n", System.getProperty("line.separator"));

        String ls = System.getProperty("line.separator");

        String r = "Line one \n Line 2";

        tvR.setText(rep.replace("\\n", ls));
        //Log.e(TAG, "LINE_SEP -> " + ls );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return true;
    }
}
