package com.btl.btl_dogo.model;

import java.io.Serializable;

import javax.annotation.Nullable;

public class Product implements Serializable {
    public String Id, Ten;
    public String TenLoai;

    public Product(@Nullable String id, String ten, String tenLoai) {
        Id = id;
        Ten = ten;
        TenLoai = tenLoai;
    }

    public Product() {
    }

}
