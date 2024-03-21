package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.ListItemBinding;
import com.btl.btl_dogo.model.Product;

public class ProductAdater extends BaseAdapter<Product, ListItemBinding> {
    public ProductAdater(ProductEvent event) {
        this.event = event;
    }

    public  interface  ProductEvent{
        void onLove(Product pr);
        void onDetail(Product product);
        void onAddCart(Product product);
    }
    final ProductEvent event;
    @Override
    protected ListItemBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListItemBinding.inflate(inflater,parent,false);
    }

    @Override
    protected void bind(ListItemBinding binding, Product item, int position) {
        LoadImg(item.img==""? R.drawable.img_1:item.img,binding.imgSp);
        binding.name.setText(item.Ten);
        binding.price.setText(String.valueOf(item.Gia));
        binding.icLove.setOnClickListener(v->event.onLove(item));
        binding.getRoot().setOnClickListener(v->event.onDetail(item));
        binding.icAdd.setOnClickListener(v->event.onAddCart(item));
    }
}
