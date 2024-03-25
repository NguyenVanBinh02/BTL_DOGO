package com.btl.btl_dogo.model;

import java.io.Serializable;

public class Comment implements Serializable {
    public String Id;
    public String img,cmt;
    public  String userID, userName,time;

    public Comment(String id, String img, String cmt, String userID, String userName, String time) {
        Id = id;
        this.img = img;
        this.cmt = cmt;
        this.userID = userID;
        this.userName = userName;
        this.time = time;
    }

    public Comment() {
    }
}
