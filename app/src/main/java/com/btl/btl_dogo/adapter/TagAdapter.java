package com.btl.btl_dogo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.TextTagBinding;
import com.btl.btl_dogo.model.Tag;

public class TagAdapter extends BaseAdapter<Tag, TextTagBinding> {
    public TagAdapter(TagInterface tagInterface) {
        this.tagInterface = tagInterface;
    }

    public interface TagInterface {
        void onSelect(Tag tag, int position);

    }

    final TagInterface tagInterface;

    @Override
    protected TextTagBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return TextTagBinding.inflate(inflater, parent, false);
    }

    @Override
    protected void bind(TextTagBinding binding, Tag item, int position) {
        binding.txtTag.setText(item.name);

        binding.navTag.setBackgroundColor(currentPosition == position ? Color.parseColor("#FFFF1100") : Color.parseColor("#CCCCCC"));

        binding.getRoot().setOnClickListener(v -> tagInterface.onSelect(item, position));
    }
}
