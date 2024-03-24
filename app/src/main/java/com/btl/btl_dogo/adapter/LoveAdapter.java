package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.ListItemLoveBinding;
import com.btl.btl_dogo.model.Product;

import java.util.Objects;

public class LoveAdapter extends BaseAdapter<Product, ListItemLoveBinding> {
    public LoveAdapter(ProductEvent event) {
        this.event = event;
    }

    public  interface  ProductEvent{
        void onDelete(Product pr);
        void onDetail(Product product);
        void onAddCart(Product product);
    }
    final ProductEvent event;
    @Override
    protected ListItemLoveBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListItemLoveBinding.inflate(inflater,parent,false);
    }

    @Override
    protected void bind(ListItemLoveBinding binding, Product item, int position) {
        LoadImg(Objects.equals(item.img, "") ? R.drawable.img_1:item.img,binding.imgSp);
        binding.name.setText(item.Ten);
        binding.price.setText(String.valueOf(item.Gia));
        binding.icDelete.setOnClickListener(v->event.onDelete(item));
        binding.getRoot().setOnClickListener(v->event.onDetail(item));
        binding.icAdd.setOnClickListener(v->event.onAddCart(item));
    }
}
