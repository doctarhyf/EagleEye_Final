package com.example.user.franvanna;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentElections extends Fragment

{


    private static final String TAG = "CENI";
    private static final long DELAY_SELECT_CAND_MILLIS = 1800;
    private ListenerFragElecPrez mListener;

    public FragmentElections() {
        // Required empty public constructor
    }

    public static FragmentElections newInstance(int candsType) {
        FragmentElections fragment = new FragmentElections();
        Bundle data = new Bundle();
        data.putInt(Candidate.KEY_CAND_TYPE, candsType);
        fragment.setArguments(data);
        return fragment;
    }

    private int candType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();

        if(null != data){
            candType = data.getInt(Candidate.KEY_CAND_TYPE);
        }else{
            Log.e(TAG, "Candtype not set." );
        }

    }

    private AdapterCandidates adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Candidate> candidates;
    private Button btnVoteBlanc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_fragment_elections, container, false);

        btnVoteBlanc = view.findViewById(R.id.btnVoteBlanc);

        candidates = CandidatesData.getCandidates(getContext(), candType);

        adapter = new AdapterCandidates(getContext(), candidates, new AdapterCandidates.CallbacksAdapterCandidates() {
            @Override
            public void onCandidateClicked(Candidate candidate) {
                mListener.onCandidateClicked(candidate);
            }
        });

        recyclerView = view.findViewById(R.id.rvCandsPrez);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        btnVoteBlanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onVoteBlanckClicked(candType);
            }
        });

        LinearLayout llBtnNumCandCont = view.findViewById(R.id.llBtnNumCandCont);

        ArrayList<View> btns = Utils.getViewsByTag(llBtnNumCandCont,getResources().getString(R.string.tagBtnNumCand));

        for(int i = 0; i < btns.size(); i++){
            final Button btn = (Button) btns.get(i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "BTN TEXT : " + btn.getText().toString() );
                    onBtnCandNumClicked(btn.getText().toString());

                }
            });
        }

        updateViews(candType, view);


        return view;
    }

    private String strCandNum = "";

    private void onBtnCandNumClicked(String btnText) {

        // TODO: 07/04/2018 TO FINISH DELAY CHOOSE CAND


        final int candNum = Integer.parseInt(btnText);

        if(strCandNum.length() == 0){
            strCandNum = "" + candNum;
        }else if (strCandNum.length() == 1 ) {
            strCandNum = strCandNum.concat("" + candNum);
        }







        new CountDownTimer(DELAY_SELECT_CAND_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {



                if(strCandNum.length() != 0){
                    Log.e(TAG, "onBtnCandNumClicked: NUM -> " + strCandNum );

                    Integer candId = Integer.parseInt(strCandNum) - 1;

                    if(candidates.size() > candId && candId >= 0 ){
                        mListener.onCandidateClicked(candidates.get( candId  ));
                    }
                }
                strCandNum = "";
            }
        }.start();

        /*
        if(strCandNum.length() == 2 || strCandNum.equals("") ){
            strCandNum = "" + candNum;
        }else{
            strCandNum = strCandNum.concat("" + candNum);
        }

        if(candNum < candidates.size() && candNum > 0) {*/

            /*
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   // mListener.onCandidateClicked(candidates.get(candNum-1));

                }
            }, DELAY_SELECT_CAND_MILLIS);

        */

    }

    final Handler handler = new Handler();

    private void updateViews(int candType, View layout) {

        TextView tvElectionsTitle = layout.findViewById(R.id.tvElectionsTitle);

        switch (candType){
            case Candidate.CAND_TYPE_LEG_NAT:
                tvElectionsTitle.setText(getActivity().getResources().getString(R.string.strElecLegNat));
                break;

            case Candidate.CAND_TYPE_LEG_PROV:
                tvElectionsTitle.setText(getActivity().getResources().getString(R.string.strElecLegProv));
                break;
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListenerFragElecPrez) {
            mListener = (ListenerFragElecPrez) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListenerFragElecPrez");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface ListenerFragElecPrez {
        void onCandidateClicked(Candidate candidate);
        void onVoteBlanckClicked(int candType);
    }
}
