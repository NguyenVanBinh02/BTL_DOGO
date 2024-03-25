package com.btl.btl_dogo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.ListCommentBinding;
import com.btl.btl_dogo.model.Comment;
import com.btl.btl_dogo.model.Tag;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter<Comment, ListCommentBinding> {
      @Override
    protected ListCommentBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ListCommentBinding.inflate(inflater,parent,false);
    }
    @Override
    protected void bind(ListCommentBinding binding, Comment item, int position) {
    }
}
