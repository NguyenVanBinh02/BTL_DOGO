package com.btl.btl_dogo.model;

import java.io.Serializable;

public class CardProduct implements Serializable {
    public  String Id;
    public Product Product;
    public int Count;
    public String Price;
    public boolean IsSelect=false;

    public CardProduct(String id, com.btl.btl_dogo.model.Product product, int count, String price,boolean isSelect) {
        Id = id;
        Product = product;
        Count = count;
        Price = price;
        IsSelect=isSelect;
    }

    public CardProduct() {
    }
}
