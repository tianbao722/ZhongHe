package com.example.Bean;

import android.media.Image;

/**
 * Created by 文龙 on 2020/1/12.
 */

public class ImageBean {
    private String image;
    private String name;

    public ImageBean(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
