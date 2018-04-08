package com.example.user.franvanna;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListVotesTypes extends ArrayAdapter<VoteType> {



    public AdapterListVotesTypes(@NonNull Context context, ArrayList<VoteType> questions, Listener listener) {
        super(context, 0, questions);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final VoteType voteType = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item_vote_type, parent, false);
        }
        // Lookup view for data population
        TextView tvVoteType = (TextView) convertView.findViewById(R.id.tvListVoteType);
        tvVoteType.setText(voteType.getName());

        tvVoteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("CENI", "onClick: Question : " + question.getQuestion() );
                listener.onVoteTypeClicked(voteType);
            }
        });

        return convertView;
    }

    private Listener listener;



    public interface Listener{
        void onVoteTypeClicked(VoteType voteType);
    }
}
