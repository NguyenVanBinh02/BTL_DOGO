package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentNotifileBinding;

public class NotifileFragment extends BaseFragment<FragmentNotifileBinding> {

    @Override
    protected FragmentNotifileBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentNotifileBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }
}