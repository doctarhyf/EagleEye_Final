package com.example.user.franvanna.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.franvanna.Objects.FAQQuestion;
import com.example.user.franvanna.R;

import java.util.ArrayList;

public class AdapterFAQQuestion extends ArrayAdapter<FAQQuestion> {



    public AdapterFAQQuestion(@NonNull Context context, ArrayList<FAQQuestion> questions, Listener listener) {
        super(context, 0, questions);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final FAQQuestion question = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item_faq_question, parent, false);
        }
        // Lookup view for data population
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tvListFAQQuestions);
        tvQuestion.setText(question.getQuestion());

        /*
        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CENI", "onClick: Question : " + question.getQuestion() );
                listener.onQuestionClicked(question);
            }
        });*/

        return convertView;
    }

    private Listener listener;



    public interface Listener{
        void onQuestionClicked(FAQQuestion question);
    }
}
