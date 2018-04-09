package com.example.user.franvanna.Objects;

public class VoteType {

    private String name;
    private int id;

    public VoteType(){

    }

    public VoteType(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
