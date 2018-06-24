package com.koziengineering.user.franvanna.Adapters;

/**
 * Created by Franvanna on 3/8/2018.
 */


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koziengineering.user.franvanna.Objects.Candidate;
import com.koziengineering.user.franvanna.R;

import java.util.List;


/**
 * Created by Franvanna on 1/10/2018.
 */

public class AdapterVotesResults extends RecyclerView.Adapter<AdapterVotesResults.ViewHolder>
{


    private static final String TAG = "ADP_REC_IT";
    private AppCompatActivity activity;


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCandPic, ivPartiLogo;
        TextView tvCandNum, tvPartiName, tvFullName, tvPrenom, tvVotesResCandType;
        View layout;
        LinearLayout  llBadgeResultCand, llBadgeResultBlanc;

        public ViewHolder(View view) {
            super(view);
            layout = view;

            ivCandPic = (ImageView) layout.findViewById(R.id.resBadgeCandPic);
            ivPartiLogo = (ImageView) layout.findViewById(R.id.resBadgeIvPartiLogo);
            tvCandNum = (TextView) layout.findViewById(R.id.resBadgeTvCandNum);
            tvPartiName = (TextView) layout.findViewById(R.id.resBadgeTvPartiName);
            tvFullName = (TextView) layout.findViewById(R.id.resBadgeTvCandFullName);
            tvPrenom = (TextView) layout.findViewById(R.id.resBadgeTvCandPrenom);
            llBadgeResultCand = layout.findViewById(R.id.llBadgeResultCand);
            llBadgeResultBlanc = layout.findViewById(R.id.llBadgeResultBlanc);
            tvVotesResCandType = layout.findViewById(R.id.tvVoteResultsCandType);

        }
    }

    private Context context;
    private List<Candidate> candidates;
    //private CallbacksAdapterResults callbacksAdapterResults;


    public AdapterVotesResults(Context context, AppCompatActivity activity,  List<Candidate> candidates){//}, CallbacksAdapterResults callbacksAdapterResults){

        this.context = context;
        this.candidates = candidates;
        this.activity = activity;
        //this.callbacksAdapterResults = callbacksAdapterResults;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_cand_badge_result, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    LinearLayout llBadgeResultCand, llBadgeResultBlanc;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {




        final Candidate candidate = candidates.get(position);

        switch (candidate.getCandType()){
            case Candidate.CAND_TYPE_PREZ:
                holder.tvVotesResCandType.setText("ELECTIONS " + context.getResources().getString(R.string.txt_vote_choice_prez));
                break;

            case Candidate.CAND_TYPE_LEG_NAT:
                holder.tvVotesResCandType.setText(context.getResources().getString(R.string.txt_vote_choice_leg_nat));
                break;

            case Candidate.CAND_TYPE_LEG_PROV:
                holder.tvVotesResCandType.setText(context.getResources().getString(R.string.txt_vote_choice_leg_prov));
                break;
        }


        if(candidate.getId() == -1){
            holder.llBadgeResultBlanc.setVisibility(View.VISIBLE);
            holder.llBadgeResultCand.setVisibility(View.GONE);

            //int sw = Utils.getScreenSize(activity).x;
            //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)(sw/3));

            //holder.llBadgeResultBlanc.setLayoutParams(lp);
// OR



        }else{

            holder.tvFullName.setText(candidate.getNomPostnom().toUpperCase());

            String prenom = candidate.getPrenom();
            String prenomCamelCase = prenom.substring(0,1).toUpperCase()+prenom.substring(1);

            holder.tvPrenom.setText(prenomCamelCase);
            holder.tvCandNum.setText("No " + (candidate.getId() + 1));


            holder.ivPartiLogo.setImageResource(candidate.getPartiLogo());
            holder.ivCandPic.setImageResource(candidate.getPicId());

            holder.tvPartiName.setText(candidate.getPartiName());

        }


        /*



        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, "CAND CLICKED " );

                if(callbacksAdapterResults != null) {
                    callbacksAdapterResults.onCandidateClicked(candidate);
                }else{
                    Log.e(TAG, "onClick: CALLBACKS IS NULL" );
                }

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }





}
