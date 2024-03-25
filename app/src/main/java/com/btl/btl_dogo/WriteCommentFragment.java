package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.util.Consumer;

import com.btl.btl_dogo.adapter.CommentAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentWritercommentBinding;
import com.btl.btl_dogo.model.Comment;
import com.btl.btl_dogo.model.Product;

public class WriteCommentFragment extends BaseFragment<FragmentWritercommentBinding> {
CommentAdapter commentAdapter;
final Consumer<Comment> consumer ;

    public WriteCommentFragment(Consumer<Comment> consumer, Product product) {
        this.consumer = consumer;
        this.product = product;
    }

    Product product;
    @Override
    protected FragmentWritercommentBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWritercommentBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        binding.imgBack.setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out
                    )
                    .remove(WriteCommentFragment.this)
                    .commit();
            hideKeyboard();
        });
        commentAdapter =new CommentAdapter();
        getComment(product, comments -> commentAdapter.setItems(comments));
        binding.rcvNotification.setAdapter(commentAdapter);
        binding.btnSend.setOnClickListener(v->{
            String cmt = binding.txtComment.getText().toString();

            if(!cmt.isEmpty()){
                getUserData(us -> {

                    Comment valCmt = new Comment("","",cmt, us.getId(),us.getName(),getTimeNow());
                    addComment(product.Id, valCmt, unused -> binding.txtComment.setText(""));
                    consumer.accept(valCmt);
                    commentAdapter.addItem(valCmt,0);

                });

            }
        });
    }

}
//