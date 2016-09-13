package com.example.tangyi.travels.bean;

import java.util.ArrayList;

/**
 * Created by aCandy on 2016/8/31.
 */
public class ZhihuNewsBean {
    //新闻日期
    private String date;

    public ArrayList<Stories> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Stories> stories) {
        this.stories = stories;
    }

    private ArrayList<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public class Stories{
        private String id;
        private String title;
        private ArrayList<String> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }

    }
}
