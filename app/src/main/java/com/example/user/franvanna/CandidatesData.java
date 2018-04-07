package com.example.user.franvanna;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class CandidatesData {
    public static final int CAND_TYPE_PREZ = 0;
    private static final String TAG = "CENI";
    private static final int MAX_CAND_PIX_NUM = 15;


    public static  int getPartiLogoByName(String partiName){

        //int partiLogoId = -1;

        HashMap<String, Integer> partiLogos = new HashMap<>();

        partiLogos.put("eps", R.drawable.pt_eps);
        partiLogos.put("independant", R.drawable.pt_independant);
        partiLogos.put("kozi", R.drawable.pt_kozi);
        partiLogos.put("lion", R.drawable.pt_lion);
        partiLogos.put("loboko", R.drawable.pt_loboko);
        partiLogos.put("losambo", R.drawable.pt_losambo);
        partiLogos.put("masomo", R.drawable.pt_masomo);
        partiLogos.put("mosala", R.drawable.pt_mosala);
        partiLogos.put("ndule", R.drawable.pt_ndule);
        partiLogos.put("vision", R.drawable.pt_vision);
        partiLogos.put("xdb", R.drawable.pt_xdb);

        return partiLogos.get(partiName);

    }

    public static List<Candidate> getCandidates(Context context, int candType) {



        List<Candidate> candidates = new ArrayList<>();

        Scanner scanner;

        int j = 0;
        switch (candType){

            case CAND_TYPE_PREZ:

                /*
                for(int i = 0; i < 10; i++) {

                    candidates.add(new Candidate(i, "NOM POSTON " + i, "Prenom " + i, -1, R.drawable.pt_xdb, candType));

                }*/

                scanner = new Scanner(context.getResources().openRawResource(R.raw.candz_prez));
               j = 0;
                while (scanner.hasNextLine()){
                   // Log.e(TAG, "line -> " + scanner.nextLine() );
                    String line = scanner.nextLine();


                    if(!line.isEmpty()){
                        String[] candData = line.split(",");
                        String fullName = candData[0];
                        String prenom = candData[1];
                        String partiName = candData[2];
                        int picId = Utils.GRIDBN("cd" + (j+1), R.drawable.class);
                        Candidate candidate = new Candidate(j,fullName, prenom,picId,getPartiLogoByName(candData[2]), candType);
                        candidate.setPartiName(partiName);
                        candidates.add(candidate);

                    }
                    j++;
                }

                /*
                ango mongbwalu,jean,indépendant
                yakusu isangi,león,eps
                bonga fataki,justin,kozi
                djuma inongo,freddy,lion
                feshi mutunda,franvale,loboko
                goma kasindi,plamedie,losambo
                kansenya kiambi,yves,masomo
                kongolo panda,antoine de padoue,mosala
                mulongo songa,gaspard,ndule
                nyakunde lolwa,deoel,vision
                oshwe koshibanda,fabrice,xdb*/



                break;

            case Candidate.CAND_TYPE_LEG_NAT:

                /*
                for(int i = 0; i < 15; i++) {

                    candidates.add(new Candidate(i, "NOM POSTON " + i, "Prenom " + i, -1, -1, candType));

                }*/

                scanner = new Scanner(context.getResources().openRawResource(R.raw.candz_dep_nat));
                j = 0;
                int picIdx = 1;
                while (scanner.hasNextLine()){
                    // Log.e(TAG, "line -> " + scanner.nextLine() );
                    if(picIdx > MAX_CAND_PIX_NUM ) picIdx = 1;
                    String line = scanner.nextLine();


                    if(!line.isEmpty()){
                        String[] candData = line.split(",");
                        int picId = Utils.GRIDBN("cd" + picIdx, R.drawable.class);
                        Candidate candidate = new Candidate(j,candData[0], candData[1],picId,getPartiLogoByName(candData[2]), candType);
                        candidate.setPartiName(candData[2]);
                        candidates.add(candidate);

                    }
                    j++;
                    picIdx++;
                }

                break;

            case Candidate.CAND_TYPE_LEG_PROV:

                scanner = new Scanner(context.getResources().openRawResource(R.raw.candz_dep_prov));
                j = 0;
                picIdx = 1;
                while (scanner.hasNextLine()){
                    // Log.e(TAG, "line -> " + scanner.nextLine() );

                    if(picIdx > MAX_CAND_PIX_NUM ) picIdx = 1;
                    String line = scanner.nextLine();


                    if(!line.isEmpty()){
                        String[] candData = line.split(",");
                        int picId = Utils.GRIDBN("cd" + picIdx, R.drawable.class);
                        Candidate candidate = new Candidate(j,candData[0], candData[1],picId,getPartiLogoByName(candData[2]), candType);
                        candidate.setPartiName(candData[2]);
                        candidates.add(candidate);

                    }
                    j++;
                    picIdx++;
                }


                break;
        }






        return candidates;

    }
}
