package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.adapter.CartAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentCartBinding;
import com.btl.btl_dogo.model.Product;

import java.util.ArrayList;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    CartAdapter cartAdapter;
    @Override
    protected FragmentCartBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCartBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.ingBack.setOnClickListener(v -> closeFragment(CartFragment.this));
        cartAdapter = new CartAdapter(new CartAdapter.ProductEvent() {
            @Override
            public void onDetail(Product product) {

            }

        });

    }
}