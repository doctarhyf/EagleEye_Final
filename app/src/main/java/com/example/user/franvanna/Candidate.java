package com.example.user.franvanna;

public class Candidate {

    public static final String KEY_CAND_TYPE = "candType";
    public static final int CANDIDATE_BLANC = -1;
    private int id;
    private String nomPostnom;
    private String prenom;
    private int picId;
    private int partiLogo;
    private int candType;
    private String partiName = "NO_PARTY_SET";

    public static final int CAND_TYPE_PREZ = 0;
    public static final int CAND_TYPE_LEG_NAT = 1;
    public static final int CAND_TYPE_LEG_PROV = 2;


    public Candidate(){

    }

    public Candidate(int id, String nomPostnom, String prenom, int picId, int partiLogo, int candType){
        this.id = id;
        this.nomPostnom = nomPostnom;
        this.prenom = prenom;
        this.picId = picId;
        this.partiLogo = partiLogo;
        this.candType = candType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPostnom() {
        return nomPostnom;
    }

    public void setNomPostnom(String nomPostnom) {
        this.nomPostnom = nomPostnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getPartiLogo() {
        return partiLogo;
    }

    public void setPartiLogo(int partiLogo) {
        this.partiLogo = partiLogo;
    }

    public int getCandType() {
        return candType;
    }

    public void setCandType(int candType) {
        this.candType = candType;
    }

    public String getPartiName() {
        return partiName;
    }

    public void setPartiName(String partiName) {
        this.partiName = partiName;
    }
}
