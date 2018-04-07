package com.example.user.franvanna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class ActivityFAQ extends AppCompatActivity implements AdapterFAQQuestion.Listener {

    private static final String TAG = "CENI";
    public static final String KEY_FAQ_Q = "question";
    public static final String KEY_FAQ_R = "reponse";
    ListView lvFAQQuestions;
    AdapterFAQQuestion adapterFAQQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().setTitle(R.string.vt_faq);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        lvFAQQuestions = findViewById(R.id.lvFAQQuestions);

        ArrayList<ModelFAQQuestion> questions = getFAQData();



        adapterFAQQuestion = new AdapterFAQQuestion(this, questions, this);

        lvFAQQuestions.setAdapter(adapterFAQQuestion);//new ArrayAdapter<>(this,R.layout.layout_faq_question, R.id.tvListFAQQuestions,questions));

    }

    private ArrayList<ModelFAQQuestion> getFAQData() {

        ArrayList<ModelFAQQuestion> questions = new ArrayList<>();

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.faq));
        //int k = 0;
        String faqData = "";
        while (scanner.hasNextLine()){

            faqData = faqData.concat(scanner.nextLine());

            //Log.e(TAG, "getFAQData: -> " + faqData );


        }


        String[] splits = faqData.split("><");

        for(int k = 0; k < splits.length; k+= 2){

            if(k < splits.length - 2) {
                questions.add(new ModelFAQQuestion(k/2, (String) splits[k].replace("<",""), splits[k + 1]));
            }
        }



        /*
            if(!line.isEmpty()){
                String[] splits = line.split("><");
                String q = splits[0];
                String r = splits[1];

                //questions.add(new ModelFAQQuestion(i, q, r));
                Log.e(TAG, "q -> " + q + "\nr -> " + r );
                k++;
            }*/


        return questions;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuestionClicked(ModelFAQQuestion question) {
        Log.e(TAG, "onQuestionClicked: listener");
        Intent intent = new Intent(this, ActivityFAQQA.class);
        Bundle data = new Bundle();
        data.putString(KEY_FAQ_Q, question.getQuestion());
        data.putString(KEY_FAQ_R, question.getReponse());
        intent.putExtras(data);
        startActivity(intent);
    }
}
