package com.koziengineering.docta.pcone.Adapters;

public class PresItem {


    private String presName;
    private String presParty;
    private String letter;

    public  PresItem(String presName, String presParty, String letter){

        this.presName = presName;
        this.presParty = presParty;
        this.letter = letter;


    }

    public String getPresName() {
        return presName;
    }

    public String getPresParty() {
        return presParty;
    }

    public String getLetter() {
        return letter;
    }
}
