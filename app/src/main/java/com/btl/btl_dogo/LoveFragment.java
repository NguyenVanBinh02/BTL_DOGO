package com.btl.btl_dogo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentLoveBinding;


public class LoveFragment extends BaseFragment<FragmentLoveBinding> {

    @Override
    protected FragmentLoveBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoveBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }
}