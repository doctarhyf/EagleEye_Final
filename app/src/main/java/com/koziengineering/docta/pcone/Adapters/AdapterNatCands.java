package com.koziengineering.docta.pcone.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.koziengineering.docta.pcone.Objects.LenNat;
import com.koziengineering.docta.pcone.Objects.VoteType;
import com.koziengineering.docta.pcone.R;

import java.util.ArrayList;

public class AdapterNatCands extends ArrayAdapter<LenNat> {


    private ArrayList<LenNat> lenNatArrayList;


    public AdapterNatCands(@NonNull Context context, ArrayList<LenNat> legNatsList, Listener listener) {
        super(context, 0, legNatsList);
        this.listener = listener;
        this.lenNatArrayList = legNatsList;

        //Log.e("KOK", "getCandLIstData: -> legNatSize : " + legNatsList.size() );

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LenNat lenNat = lenNatArrayList.get(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lit_nat_cands, parent, false);
        }
        // Lookup view for data population
        TextView tvLegNat = (TextView) convertView.findViewById(R.id.tvNatCand);
        tvLegNat.setText(lenNat.getCandName());



        return convertView;
    }

    private Listener listener;



    public interface Listener{
        //void onCandListDataClicked(String dataPath);
    }
}
