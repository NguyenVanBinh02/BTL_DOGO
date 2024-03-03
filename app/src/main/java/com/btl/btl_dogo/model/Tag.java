package com.btl.btl_dogo.model;

import java.io.Serializable;

public class Tag implements Serializable {
    public String name;
    public String idTag;

    public Tag(String name, String idTag) {
        this.name = name;
        this.idTag = idTag;
    }

    public Tag() {
    }
}

