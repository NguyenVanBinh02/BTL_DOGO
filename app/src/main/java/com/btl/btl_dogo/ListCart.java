package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.ListCartBinding;

public class ListCart extends BaseFragment<ListCartBinding> {
    @Override
    protected ListCartBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return ListCartBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.txtAdd.setOnClickListener(v -> {
            int add = Integer.parseInt(binding.txtSL.getText().toString());
            add ++;
            binding.txtSL.setText(String.valueOf(add));
        });
        binding.txtRemove.setOnClickListener(v -> {
            int remove = Integer.parseInt(binding.txtSL.getText().toString());
            if (remove >1) {
                remove--;
                binding.txtSL.setText(String.valueOf(remove));
            }
        });
    }
}
