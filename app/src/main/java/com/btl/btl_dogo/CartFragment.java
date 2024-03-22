package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.adapter.CartAdapter;
import com.btl.btl_dogo.base.BaseActivity;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentCartBinding;
import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.User;

import java.util.ArrayList;

public class CartFragment extends BaseFragment<FragmentCartBinding>  {
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
                replaceFragment(new DetailsFragment(product),android.R.id.content,true);
            }

            @Override
            public void onAdd(Product pr) {

            }

            @Override
            public void onRemove(Product product) {
            }

        });
        getUserData(us -> getListCard(us, products -> {
            cartAdapter.setItems(products);
        }));
        binding.listSP.setAdapter(cartAdapter);
    }
}