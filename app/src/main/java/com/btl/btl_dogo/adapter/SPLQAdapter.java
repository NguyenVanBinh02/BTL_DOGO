package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.ListItemBinding;
import com.btl.btl_dogo.databinding.ListSplqBinding;
import com.btl.btl_dogo.model.Product;

public class SPLQAdapter extends BaseAdapter<Product, ListSplqBinding> {
    public SPLQAdapter(ProductEvent event) {
        this.event = event;
    }

    public  interface  ProductEvent{
        void onDetail(Product product);

    }
    final ProductEvent event;
    @Override
    protected ListSplqBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListSplqBinding.inflate(inflater,parent,false);
    }

    @Override
    protected void bind(ListSplqBinding binding, Product item, int position) {
        LoadImg(item.img==""?R.drawable.img_1:item.img,binding.imgSplq);
        binding.txtSp.setText(item.Ten);
        binding.txtGia.setText(String.valueOf(item.Gia));
    }
}
