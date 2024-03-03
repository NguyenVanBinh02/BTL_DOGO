package com.btl.btl_dogo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentDetailsBinding;
import com.btl.btl_dogo.model.Product;


public class DetailsFragment extends BaseFragment<FragmentDetailsBinding> {
    private Product product;

    public DetailsFragment(Product product) {
        this.product = product;
    }

    @Override
    protected FragmentDetailsBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentDetailsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.ingBack.setOnClickListener(v -> closeFragment(DetailsFragment.this));
        binding.txtSp.setText("Ten san pham:     "+product.Ten);
        binding.txtGia.setText(String.valueOf(product.TenLoai));
    }

}