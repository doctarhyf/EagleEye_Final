package com.koziengineering.user.franvanna.Objects;

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

    private String menuSubtitle;

    public MenuItem(int menuIco, String menuText, int id, String menuSubtitle){
        this.menuIco = menuIco;
        this.menuText = menuText;
        this.id = id;
        this.setMenuSubtitle(menuSubtitle);
    }

    public String getMenuSubtitle() {
        return menuSubtitle;
    }

    public void setMenuSubtitle(String menuSubtitle) {
        this.menuSubtitle = menuSubtitle;
    }
}