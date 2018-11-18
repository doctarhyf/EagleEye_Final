package com.koziengineering.user.franvanna.Objects;

public class LenNat {





    private int id;
    private String candName;
    private String reponse;

    public LenNat(int i, String string){

        this.candName = string;

    }

    public LenNat(int id, String candName, String reponse){
        this.id = id;
        this.candName = candName;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCandName() {
        return candName;
    }

    public void setCandName(String candName) {
        this.candName = candName;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}

