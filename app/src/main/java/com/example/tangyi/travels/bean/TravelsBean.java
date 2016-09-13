package com.example.tangyi.travels.bean;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by aCandy on 2016/9/10.
 */
public class TravelsBean {
    private MyData data;

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }


    public class MyData{
        private ArrayList<Content> books;


        public ArrayList<Content> getBooks() {
            return books;
        }

        public void setBooks(ArrayList<Content> books) {
            this.books = books;
        }


        public class Content{
            //详情页URL
            private String bookUrl;
            //标题
            private String title;
            //图片URL
            private String headImage;
            //发帖时间
            private String startTime;

            public String getBookUrl() {
                return bookUrl;
            }

            public void setBookUrl(String bookUrl) {
                this.bookUrl = bookUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }
        }
    }
}
