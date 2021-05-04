package com.example.pars.uni_prj;

import android.media.Image;

public class items {

        private int picture;
        private String name;

    public items(int picture, String name) {
        this.picture = picture;
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
