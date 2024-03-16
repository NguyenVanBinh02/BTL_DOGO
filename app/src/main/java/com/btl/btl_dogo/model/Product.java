package com.btl.btl_dogo.model;

import java.io.Serializable;

import javax.annotation.Nullable;

public class Product implements Serializable {
    public String Id, Ten;
    public String TenLoai, Gia, NoiSX;
    public String MotaSP, KichThuoc, ChatLieu;

    public Product(String id, String ten, String tenLoai, String gia, String motaSP, String kichThuoc, String chatLieu, String noiSX) {
        Id = id;
        Ten = ten;
        TenLoai = tenLoai;
        Gia = gia;
        MotaSP = motaSP;
        KichThuoc = kichThuoc;
        ChatLieu = chatLieu;
        NoiSX=noiSX;
    }

    public Product() {
    }

}
