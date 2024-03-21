package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.FragmentCartBinding;
import com.btl.btl_dogo.databinding.ListCartBinding;
import com.btl.btl_dogo.model.Product;

public class CartAdapter extends BaseAdapter<Product, ListCartBinding> {
    public CartAdapter(ProductEvent event) {
        this.event = event;
    }

    public  interface  ProductEvent{
        void onDetail(Product product);
    }
    final ProductEvent event;
    @Override
    protected ListCartBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListCartBinding.inflate(inflater,parent,false);
    }

    @Override
    protected void bind(ListCartBinding binding, Product item, int position) {
    LoadImg(item.img==""? R.drawable.img_3:item.img,binding.imgCart);
    binding.txtGia.setText(String.valueOf(item.Gia));
    binding.txtName.setText(item.Ten);
    }
}
