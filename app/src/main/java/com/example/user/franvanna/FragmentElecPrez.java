package com.example.user.franvanna;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class FragmentElecPrez extends Fragment

{




    private ListenerFragElecPrez mListener;

    public FragmentElecPrez() {
        // Required empty public constructor
    }

    public static FragmentElecPrez newInstance() {
        FragmentElecPrez fragment = new FragmentElecPrez();

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private AdapterCandsPrez adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Candidate> candidates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_elec_prez, container, false);

        candidates = ObjectCandidatesData.getCandidates(ObjectCandidatesData.CAND_TYPE_PREZ);

        adapter = new AdapterCandsPrez(getContext(), candidates, new AdapterCandsPrez.CallbacksAdapterCandidates() {
            @Override
            public void onCandidateClicked(Candidate candidate) {
                mListener.onCandidateClicked(candidate);
            }
        });

        recyclerView = view.findViewById(R.id.rvCandsPrez);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


        return view;
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
    }
}
