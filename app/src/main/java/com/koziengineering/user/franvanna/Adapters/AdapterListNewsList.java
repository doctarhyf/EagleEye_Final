package com.koziengineering.user.franvanna.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.koziengineering.user.franvanna.Objects.NewsItem;
import com.koziengineering.docta.pcone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterListNewsList extends ArrayAdapter<NewsItem> {



    public AdapterListNewsList(@NonNull Context context, ArrayList<NewsItem> newsItems, Listener listener) {
        super(context, 0, newsItems);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final NewsItem newsItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item_news_item, parent, false);
        }

        ImageView ivNewsIcon = convertView.findViewById(R.id.ivNewsIcon);

        Picasso.with(convertView.getContext()).load(newsItem.getPicUrl()).error(android.R.drawable.ic_delete).into(ivNewsIcon);


        // Lookup view for data population
        /*
        TextView tvVoteType = (TextView) convertView.findViewById(R.id.tvListVoteType);
        tvVoteType.setText(voteType.getName());

        tvVoteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("CENI", "onClick: Question : " + question.getCandName() );
                listener.onVoteTypeClicked(voteType);
            }
        });*/

        return convertView;
    }

    private Listener listener;



    public interface Listener{
        void onNewsItemClicked (NewsItem newsItem);
    }
}
