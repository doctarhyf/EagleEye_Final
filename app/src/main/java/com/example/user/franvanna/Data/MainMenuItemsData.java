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

    public static List<MenuItem> getMainMenuItems(Resources res)

    {


        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.sim, res.getString(R.string.menu_vote_sim),R.id.mainMenuVoteSimulation, res.getString(R.string.sub_menu_vote_sim) ));
        menuItems.add(new MenuItem(R.drawable.sim, res.getString(R.string.menu_news),R.id.mainMenuBuroVote, res.getString(R.string.sub_menu_news) ));
        menuItems.add(new MenuItem(R.drawable.le, res.getString(R.string.menu_list_elec),R.id.mainMenuListElec, res.getString(R.string.sub_menu_list_elec) ));
        menuItems.add(new MenuItem(R.drawable.bv, res.getString(R.string.menu_buro_vote) ,R.id.mainMenuBuroVote, res.getString(R.string.sub_menu_buro_vote)));
        menuItems.add(new MenuItem(R.drawable.cands, res.getString(R.string.menu_cands),R.id.mainMenuCands, res.getString(R.string.sub_menu_cands) ));
        menuItems.add(new MenuItem(R.drawable.res, res.getString(R.string.menu_results) ,R.id.mainMenuResults, res.getString(R.string.sub_menu_results)));
        menuItems.add(new MenuItem(R.drawable.sim, res.getString(R.string.menu_faq),R.id.mainMenuFaq, res.getString(R.string.sub_menu_faq) ));
        menuItems.add(new MenuItem(R.drawable.sim, res.getString(R.string.menu_contact_us),R.id.mainMenuFaq, res.getString(R.string.sub_menu_contact_us) ));




        return menuItems;
    }
}
