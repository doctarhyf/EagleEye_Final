package com.example.user.franvanna;

/**
 * Created by Franvanna on 3/8/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Franvanna on 1/10/2018.
 */

public class AdapterCandsPrez extends RecyclerView.Adapter<AdapterCandsPrez.ViewHolder>
{


    private static final String TAG = "ADP_REC_IT";



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCandPic, ivPartiLogo;
        TextView tvCandNum, tvPartiName, tvFullName, tvPrenom;
        View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            ivCandPic = (ImageView) layout.findViewById(R.id.cbIvPic);
            ivPartiLogo = (ImageView) layout.findViewById(R.id.cbIvPartiLogo);
            tvCandNum = (TextView) layout.findViewById(R.id.cbTvNum);
            tvPartiName = (TextView) layout.findViewById(R.id.cbTvPartiName);
            tvFullName = (TextView) layout.findViewById(R.id.cbTvFullName);
            tvPrenom = (TextView) layout.findViewById(R.id.cbTvPrenom);

        }
    }

    private Context context;
    private List<Candidate> candidates;
    private CallbacksAdapterCandidates callbacksAdapterCandidates;


    public AdapterCandsPrez(Context context, List<Candidate> candidates, CallbacksAdapterCandidates callbacksAdapterCandidates){

        this.context = context;
        this.candidates = candidates;
        this.callbacksAdapterCandidates = callbacksAdapterCandidates;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_candidate_badge, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Candidate candidate = candidates.get(position);

        holder.tvFullName.setText(candidate.getNomPostnom());
        holder.tvPrenom.setText(candidate.getPrenom());
        holder.tvCandNum.setText((candidate.getId() + 1) + "");

        //holder.ivMenuText.setText(menuItem.getMenuText());
        //holder.ivMenuIco.setImageDrawable(context.getResources().getDrawable(menuItem.getMenuIco()));


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, "CAND CLICKED " );

                if(callbacksAdapterCandidates != null) {
                    callbacksAdapterCandidates.onCandidateClicked(candidate);
                }else{
                    Log.e(TAG, "onClick: CALLBACKS IS NULL" );
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public interface CallbacksAdapterCandidates {
        void onCandidateClicked(Candidate candidate);

    }



}
