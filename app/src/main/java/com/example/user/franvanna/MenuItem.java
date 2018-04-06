package com.example.user.franvanna;

/**
 * Created by Franvanna on 3/8/2018.
 */


public class MenuItem {

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    private String menuText;

    public int getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(int menuIco) {
        this.menuIco = menuIco;
    }

    private int menuIco;

    private int id;

    public int getId() { return id;}
    public void setId() {this.id = id;}

    public MenuItem(int menuIco, String menuText, int id){
        this.menuIco = menuIco;
        this.menuText = menuText;
        this.id = id;
    }

}