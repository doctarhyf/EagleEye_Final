package com.example.user.franvanna.Data;

import android.content.res.Resources;

import com.example.user.franvanna.Objects.MenuItem;
import com.example.user.franvanna.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franvanna on 3/8/2018.
 */

public class MainMenuItemsData {

    public static final int MAIN_MENU_VOTE_SIMULATION = 0;
    public static final int MAIN_MENU_NEWS = 1;
    public static final int MAIN_MENU_LIST_ELEC = 2;
    public static final int MAIN_MENU_BUREAU_VOTE = 3;
    public static final int MAIN_MENU_CANDS = 4;
    public static final int MAIN_MENU_OFFICIAL_RESULTS = 5;
    public static final int MAIN_MENU_FAQ = 6;
    public static final int MAIN_MENU_CONTACT_US = 7;

    public static List<MenuItem> getMainMenuItems(Resources res)

    {


        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.sim, res.getString(R.string.menu_vote_sim),MAIN_MENU_VOTE_SIMULATION, res.getString(R.string.sub_menu_vote_sim) ));
        menuItems.add(new MenuItem(R.drawable.ic_news, res.getString(R.string.menu_news),MAIN_MENU_NEWS, res.getString(R.string.sub_menu_news) ));
        menuItems.add(new MenuItem(R.drawable.le, res.getString(R.string.menu_list_elec),MAIN_MENU_LIST_ELEC, res.getString(R.string.sub_menu_list_elec) ));
        menuItems.add(new MenuItem(R.drawable.bv, res.getString(R.string.menu_buro_vote) ,MAIN_MENU_BUREAU_VOTE, res.getString(R.string.sub_menu_buro_vote)));
        menuItems.add(new MenuItem(R.drawable.cands, res.getString(R.string.menu_cands),MAIN_MENU_CANDS, res.getString(R.string.sub_menu_cands) ));
        menuItems.add(new MenuItem(R.drawable.res, res.getString(R.string.menu_results) ,MAIN_MENU_OFFICIAL_RESULTS, res.getString(R.string.sub_menu_results)));
        menuItems.add(new MenuItem(R.drawable.ic_faq, res.getString(R.string.menu_faq),MAIN_MENU_FAQ, res.getString(R.string.sub_menu_faq) ));
        menuItems.add(new MenuItem(R.drawable.ic_contacts, res.getString(R.string.menu_contact_us),MAIN_MENU_CONTACT_US, res.getString(R.string.sub_menu_contact_us) ));




        return menuItems;
    }
}
