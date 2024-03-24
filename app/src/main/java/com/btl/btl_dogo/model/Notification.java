package com.btl.btl_dogo.model;

import java.io.Serializable;

public class Notification implements Serializable {
    public String Id;
    public String Title;
    public String Content;
    public String Time;
    public boolean IsRead=false;

    public Notification(String id, String title, String content, String time,boolean isRead) {
        Id = id;
        Title = title;
        Content = content;
        Time = time;
        IsRead=isRead;
    }

    public Notification() {
    }
}
