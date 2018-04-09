package com.example.user.franvanna.Adapters;

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

import com.example.user.franvanna.Candidate;
import com.example.user.franvanna.R;

import java.util.List;


/**
 * Created by Franvanna on 1/10/2018.
 */

public class AdapterCandidates extends RecyclerView.Adapter<AdapterCandidates.ViewHolder>
{


    private static final String TAG = "ADP_REC_IT";



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCandPic, ivPartiLogo;
        TextView tvCandNum, tvPartiName, tvFullName, tvPrenom;
        View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            ivCandPic = (ImageView) layout.findViewById(R.id.cbIvCandPic);
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


    public AdapterCandidates(Context context, List<Candidate> candidates, CallbacksAdapterCandidates callbacksAdapterCandidates){

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

        holder.tvFullName.setText(candidate.getNomPostnom().toUpperCase());

        String prenom = candidate.getPrenom();
        String prenomCamelCase = prenom.substring(0,1).toUpperCase()+prenom.substring(1);

        holder.tvPrenom.setText(prenomCamelCase);
        holder.tvCandNum.setText((candidate.getId() + 1) + "");

        //holder.tvMenuTitle.setText(menuItem.getMenuText());
        //holder.ivMenuIco.setImageDrawable(context.getResources().getDrawable(menuItem.getMenuIco()));



        /*int resId = Utils.getResIdByName("pt_kozi", R.drawable.class);
        Log.e(TAG, "DA RES ID : " + resId );*/

        holder.ivPartiLogo.setImageResource(candidate.getPartiLogo());
        holder.ivCandPic.setImageResource(candidate.getPicId());

        holder.tvPartiName.setText(candidate.getPartiName());


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
