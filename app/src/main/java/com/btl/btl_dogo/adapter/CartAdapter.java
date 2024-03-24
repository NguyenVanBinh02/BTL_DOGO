package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.FragmentCartBinding;
import com.btl.btl_dogo.databinding.ListCartBinding;
import com.btl.btl_dogo.model.CardProduct;
import com.btl.btl_dogo.model.Product;

public class CartAdapter extends BaseAdapter<CardProduct, ListCartBinding> {
    public CartAdapter(ProductEvent event) {
        this.event = event;
    }

    public interface ProductEvent {
        void onDetail(CardProduct product);

        void onAdd(CardProduct pr, int post);

        void onRemove(CardProduct product,int post);

        void onSelect(CardProduct product, boolean isSelect,int position);

    }

    final ProductEvent event;

    @Override
    protected ListCartBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListCartBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bind(ListCartBinding binding, CardProduct cardProduct, int position) {
        Product item = cardProduct.Product;
        LoadImg(item.img.trim().equals("") ? R.drawable.img_3 : item.img, binding.imgCart);

        String giatt= cardProduct.Price.replace(".","").replace(" ","").replace(",","");
        float gia= cardProduct.Count * Integer.valueOf(giatt);
        binding.txtGia.setText(String.valueOf(gia));
        binding.txtName.setText(item.Ten);


        binding.txtSL.setText(""+cardProduct.Count);
        binding.txtAdd.setOnClickListener(v -> event.onAdd(cardProduct,position));
        binding.getRoot().setOnClickListener(v -> event.onDetail(cardProduct));
        binding.txtRemove.setOnClickListener(v -> event.onRemove(cardProduct,position));
        binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            event.onSelect(cardProduct, isChecked,position);
        });
    }
}
