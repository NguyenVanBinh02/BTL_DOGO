package com.btl.btl_dogo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.databinding.ItemNofificationBinding;
import com.btl.btl_dogo.model.Notification;

public class NotificationAdapter extends BaseAdapter<Notification, ItemNofificationBinding> {
    @Override
    protected ItemNofificationBinding createBinding(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ItemNofificationBinding.inflate(inflater,parent,false);
    }

    @Override
    protected void bind(ItemNofificationBinding binding, Notification item, int position) {
        binding.txtTime.setText(item.Time);
        binding.txtContent.setText(item.Content);
        binding.txtUserName.setText(item.Title);
    }
}
