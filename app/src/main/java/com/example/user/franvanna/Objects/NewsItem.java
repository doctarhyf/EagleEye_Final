package com.example.user.franvanna.Objects;

import java.util.ArrayList;
import java.util.List;

public class NewsItem {

    private int id;
    private String picUrl;
    private String title;
    private String content;

    public NewsItem(int id, String picUrl, String title, String content){
        this.setId(id);
        this.setPicUrl(picUrl);
        this.setTitle(title);
        this.setContent(content);
    }

    public NewsItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ArrayList<NewsItem> getDummyData() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            newsItems.add(new NewsItem(i, "https://www.washingtonpost.com/rf/image_1248x832/2010-2019/WashingtonPost/2018/04/12/Style/Images/TV-Elvis_Documentary_52991.jpg-99389.jpg?t=20170517a", "News Item " + i, "Dummy content goes here ..."));
        }

        return newsItems;
    }
}
