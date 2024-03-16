package com.btl.btl_dogo.model;

import java.io.Serializable;

public class Comment implements Serializable {
    public String Id, Ten;
    public String img,cmt;

    public Comment(String id, String ten, String img, String cmt) {
        Id = id;
        Ten = ten;
        this.img = img;
        this.cmt = cmt;
    }

    public Comment() {
    }
}
