package com.btl.btl_dogo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.btl.btl_dogo.adapter.CommentAdapter;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentDetailsBinding;
import com.btl.btl_dogo.model.Comment;
import com.btl.btl_dogo.model.Product;

import java.util.ArrayList;


public class DetailsFragment extends BaseFragment<FragmentDetailsBinding> {
    private Product product;
    CommentAdapter commentAdapter;
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
        binding.txtSp.setText(product.Ten);
        binding.txtGia.setText(String.valueOf(product.Gia));
        ArrayList<Comment> listComment = new ArrayList<>();
        listComment.add(new Comment("ghe", "tot", "", "dung"));
        listComment.add(new Comment("ghe", "tot", "", "dung"));
        listComment.add(new Comment("ghe", "tot", "", "dung"));

        binding.listComment.setAdapter(commentAdapter);
//        commentAdapter.setItems(listComment);


    }


}