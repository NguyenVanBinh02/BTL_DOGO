package com.btl.btl_dogo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.btl.btl_dogo.adapter.NotificationAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentNotifileBinding;
import com.btl.btl_dogo.model.Notification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotifileFragment extends BaseFragment<FragmentNotifileBinding> {

    @Override
    protected FragmentNotifileBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentNotifileBinding.inflate(getLayoutInflater());
    }

    private NotificationAdapter notificationAdapter;

    @Override
    protected void initView() {
        notificationAdapter = new NotificationAdapter();
        binding.rcvNotification.setAdapter(notificationAdapter);
        getAllNoti(notifications -> {
            notificationAdapter.setItems(notifications);
        });

    }

    private void getAllNoti(Consumer<ArrayList<Notification>> data) {
        DatabaseReference reference = database.getReference("Notification").child(preference.getString("UserID", ""));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Notification> notifications = new ArrayList<>();
                for (DataSnapshot s : snapshot.getChildren()) { // Fix typo here
                    notifications.add(s.getValue(Notification.class)); // Fix variable name here
                }
                data.accept(notifications);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}