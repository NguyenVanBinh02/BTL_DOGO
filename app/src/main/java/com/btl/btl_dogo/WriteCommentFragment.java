package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.util.Consumer;

import com.btl.btl_dogo.adapter.CommentAdapter;
import com.btl.btl_dogo.base.BaseActivity;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentWritercommentBinding;
import com.btl.btl_dogo.model.Comment;
import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.User;

import java.util.ArrayList;

public class WriteCommentFragment extends BaseFragment<FragmentWritercommentBinding> {
CommentAdapter commentAdapter;

    public WriteCommentFragment(Product product) {
        this.product = product;
    }

    Product product;
    @Override
    protected FragmentWritercommentBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWritercommentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.imgCart.setOnClickListener(v->{
            replaceFragment(new CartFragment(),android.R.id.content,false);
        });
        binding.imgBack.setOnClickListener(v-> closeFragment(WriteCommentFragment.this));
        commentAdapter =new CommentAdapter();
        getComment(product, comments -> commentAdapter.setItems(comments));
        binding.rcvNotification.setAdapter(commentAdapter);
        binding.btnSend.setOnClickListener(v->{
            String cmt = binding.txtComment.getText().toString();

            getUserData(us -> {
                Comment valCmt = new Comment("","",cmt, us.getId(),us.getName(),getTimeNow());
                addComment(product.Id, valCmt, new Consumer<Void>() {
                    @Override
                    public void accept(Void unused) {
                        binding.txtComment.setText("");
                    }
                });
            });
        });
    }

}
