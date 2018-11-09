package com.koziengineering.docta.pcone;

import android.content.Context;

public  class DataDisplay {
    public static final String PRESIDENTS = "ddPrez";
    public static final String PROVINCES = "ddProvs";
    public static final String TERRITORIES = "ddTerrz";
    public static final String PROVINCES_FOR_TERRITOIRES = "ddProvForTerzz";
    public static final String KEY_DATA_DISPLAY_TYPE = "keyDataDisplayType";
    public static final String CUR_WINDOW_TITLE = "ddWindowTile";
    public static final String PROMP_TEXT_LIST_DATA_TILE = "wt_listPrez";
    public static final String KEY_DATA_PROV_LEVEL_NAT_OR_PROV = "KprovLegDataNationalOrProv";
    public static final String DATA_PROV_LEVEL_NAT_OR_LEG_NATIONAL = "provinceLevenceLevelNational";
    public static final String KEY_DATA_TO_DISPLAY = "kDataToDisplay";
    public static final String DATA_TO_DISPLAY_DEP_NAT = "dataToDisplayDepNat";
    public static final String KEY_FILE_ID = "kFileID";
    public static final String TERRIRTORI_DATA_PREFIX = "terr_data_";
    public static final String KEY_CURRENT_TERRITORY_ID = "curTerrID";
    public static final String KEY_CURRENT_PROVINCE_ID = "curProvID";
    public static final String DATA_TO_DISPLAY_DEP_PROV = "ddDepProv";


    public static String GetWindowTitle(Context context, String dataDisplayType, String customTime){


        String windowTitle = "CENI 2018";




        if(dataDisplayType == PROVINCES){
            windowTitle = context.getResources().getString(R.string.txt_prov_choice);
        }


        if(dataDisplayType == TERRITORIES){
            windowTitle = context.getResources().getString(R.string.txt_territory_choice);
        }


        if(customTime != null){
            windowTitle = customTime;
        }


        return windowTitle;

    }

    public static int GetListDataFileID(String dataID) {

        int dataFileID = -1;
        if(dataID == PROVINCES){
            dataFileID = R.raw.ld_provinces;
        }

        if(dataID == TERRITORIES){
            dataFileID = R.raw.ld_provinces;
        }


        return dataFileID;
    }
}
