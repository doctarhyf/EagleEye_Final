package com.example.user.franvanna;

import java.util.ArrayList;
import java.util.List;

class ObjectCandidatesData {
    public static final int CAND_TYPE_PREZ = 0;

    public static List<Candidate> getCandidates(int candType) {

        List<Candidate> candidates = new ArrayList<>();



        switch (candType){

            case CAND_TYPE_PREZ:

                for(int i = 0; i < 10; i++) {

                    candidates.add(new Candidate(i, "NOM POSTON " + i, "Prenom " + i, -1, -1, candType));

                }


                break;
        }






        return candidates;

    }
}
