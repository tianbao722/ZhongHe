package com.example.Bean;

/**
 * Created by 文龙 on 2020/1/8.
 */

public class SqlBean  {
    private String Image;
    private String title;
    private String data;

    public SqlBean(String image, String title, String data) {
        Image = image;
        this.title = title;
        this.data = data;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
