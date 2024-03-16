package com.btl.btl_dogo.model;

import java.io.Serializable;

import javax.annotation.Nullable;

public class Product implements Serializable {
    public String Id, Ten;
    public String TenLoai, Gia;

    public Product(@Nullable String id, String ten, String tenLoai,String gia) {
        Id = id;
        Ten = ten;
        TenLoai = tenLoai;
        Gia = gia;
    }

    public Product() {
    }

}
