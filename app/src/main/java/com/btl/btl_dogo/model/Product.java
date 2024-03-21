package com.btl.btl_dogo.model;

import java.io.Serializable;

import javax.annotation.Nullable;

public class Product implements Serializable {
    public String Id, Ten;
    public String TenLoai, Gia, NoiSX;
    public String MotaSP, KichThuoc, ChatLieu;
    public  String img;

    public Product(String id, String ten, String tenLoai, String gia, String noiSX, String motaSP, String kichThuoc, String chatLieu, String img) {
        Id = id;
        Ten = ten;
        TenLoai = tenLoai;
        Gia = gia;
        NoiSX = noiSX;
        MotaSP = motaSP;
        KichThuoc = kichThuoc;
        ChatLieu = chatLieu;
        this.img = img;
    }

    public Product() {
    }

}
