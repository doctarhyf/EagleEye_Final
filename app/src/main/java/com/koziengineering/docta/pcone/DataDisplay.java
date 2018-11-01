package com.koziengineering.docta.pcone;

import android.content.Context;

class DataDisplay {
    public static final String PRESIDENTS = "ddPrez";
    public static final String PROVINCES = "ddProvs";
    public static final String TERRITORIES = "ddTerrz";
    public static final String KEY_DATA_DISPLAY_TYPE = "keyDataDisplayType";
    public static final String KEY_DATA_DISPLAY_TYPE_WINDOW_TITLE = "ddWindowTile";

    public static String GetWindowTitle(Context context, String dataDisplayType){


        String windowTitle = "CENI 2018";


        if(dataDisplayType == PROVINCES){
            windowTitle = context.getResources().getString(R.string.txt_vote_choice_leg_nat);
        }


        return windowTitle;

    }

    public static int GetListDataFileID(String dataID) {

        int dataFileID = -1;
        if(dataID == PROVINCES){
            dataFileID = R.raw.ld_provinces;
        }


        return dataFileID;
    }
}
