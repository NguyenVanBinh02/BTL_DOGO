package com.btl.btl_dogo;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.base.GenericAdapter;
import com.btl.btl_dogo.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    @Override
    protected FragmentProfileBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentProfileBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        getUserData(us -> {
            binding.txtUser.setText(us.getName());
            binding.txtEmail.setText(us.getEmail());
            binding.txtAddress.setText(us.getAddress());
            binding.txtPhone.setText(us.getPhone());
        });
        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
        binding.imgUser.setOnClickListener(v -> openGallery());
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatePhone = binding.txtPhone.getText().toString();
                String updateAddress = binding.txtAddress.getText().toString();
                updateProfile(updatePhone, updateAddress);
            }
        });
    }

    private void updateProfile(String updatePhone, String updateAddress) {
        String userID = preference.getString("UserID", "");
        DatabaseReference dbRef= database.getReference("users").child(userID);
        dbRef.child("phone").setValue(updatePhone);
        dbRef.child("address").setValue(updateAddress);
        showToast("Update successful");
    }

    private static final int PICK_IMAGE_REQUEST = 1;

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void updateFirebaseData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Load and display the selected image using Glide into the CircleImageView
            Glide.with(this).load(selectedImageUri).into(binding.imgUser);
        }
    }


}
