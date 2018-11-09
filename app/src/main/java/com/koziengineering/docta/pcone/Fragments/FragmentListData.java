package com.koziengineering.docta.pcone.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koziengineering.docta.pcone.DataDisplay;
import com.koziengineering.docta.pcone.R;
import com.koziengineering.docta.pcone.Fragments.dummy.ListContet.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FragmentListData extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_LIST_DATA = "listData";
    private static final String ARG_IS_TERR = "isTerr";
    private static final String ARG_LIST_NAME = "listName";
    private static final String TAG = "CENI";
    //private static final String ARG_OMMIT_FIRST = "omitFirst";
    //private static final String ARG_LIST_TYPE = "listType";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private String mListData;
    //private boolean mIsTerritories = false;
    private String mListName;
    private List<ListItem> itemsList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentListData() {
    }



    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragmentListData newInstance(int columnCount, String listData, String listName) {
        FragmentListData fragment = new FragmentListData();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_LIST_DATA, listData);
        //args.putBoolean(ARG_OMMIT_FIRST, omitFirst);
        args.putString(ARG_LIST_NAME, listName);

        fragment.setArguments(args);




        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mListData = getArguments().getString(ARG_LIST_DATA);
            //mIsTerritories = getArguments().getBoolean(ARG_IS_TERR);
            mListName = getArguments().getString(ARG_LIST_NAME);
            prepareLisData(mListName);

        }
    }

    private void prepareLisData(String listName) {


        String[] splits = mListData.split(",");

        int init = 0;
        if(mListName.equals(DataDisplay.TERRITORIES)){
            init = 1;
        }

        for(int i = init ; i < splits.length; i++) {
            ListItem listItem = new ListItem("" + (i+1), splits[i], "details", listName);
            itemsList.add(listItem);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datatypelistitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //Log.e(TAG, "data from frag okyiela -> " + mListData );
            recyclerView.setAdapter(new RecyclerViewListItem(itemsList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ListItem item);

    }
}
