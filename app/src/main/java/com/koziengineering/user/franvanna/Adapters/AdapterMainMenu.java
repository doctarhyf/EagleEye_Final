package com.koziengineering.user.franvanna.Adapters;

/**
 * Created by Franvanna on 3/8/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koziengineering.user.franvanna.Objects.MenuItem;
import com.koziengineering.docta.pcone.R;

import java.util.List;


/**
 * Created by Franvanna on 1/10/2018.
 */

public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.ViewHolder> {


    private static final String TAG = "ADP_REC_IT";
    private static final int BG_COLS_IDS[] = new int[]{
            R.color.menu_item_col_1,
            R.color.menu_item_col_2,
            R.color.menu_item_col_3,
            R.color.menu_item_col_4,
            R.color.menu_item_col_5,
            R.color.menu_item_col_6,
            R.color.menu_item_col_7,

    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMenuIco;
        TextView tvMenuTitle, tvMenuSubtitle;

        View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            ivMenuIco =  layout.findViewById(R.id.ivMenuIco);
            tvMenuTitle =  layout.findViewById(R.id.tvMainMenuTitle);
            tvMenuSubtitle = layout.findViewById(R.id.tvMainMenuSubtitle);
        }
    }

    private Context context;
    private List<MenuItem> menuItemList;
    private CallbacksAdapterMenuItems callbacksAdapterMenuItems;


    public AdapterMainMenu(Context context, List<MenuItem> menuItemList, CallbacksAdapterMenuItems callbacksAdapterMenuItems){

        this.context = context;
        this.menuItemList = menuItemList;
        this.callbacksAdapterMenuItems = callbacksAdapterMenuItems;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_adapter_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final MenuItem menuItem = menuItemList.get(position);

        holder.tvMenuTitle.setText(menuItem.getMenuText());
        holder.tvMenuSubtitle.setText(menuItem.getMenuSubtitle());
        holder.ivMenuIco.setImageDrawable(context.getResources().getDrawable(menuItem.getMenuIco()));


        final int colIdx = position % BG_COLS_IDS.length;
        final int colId = BG_COLS_IDS[colIdx];
        holder.layout.setBackgroundColor(context.getResources().getColor(colId));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callbacksAdapterMenuItems.onMenuItemClicked(menuItem);


            }
        });

        //Log.e("XXX", " : " +  () );
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public interface CallbacksAdapterMenuItems {
        void onMenuItemClicked(MenuItem menuItem);

    }



}
