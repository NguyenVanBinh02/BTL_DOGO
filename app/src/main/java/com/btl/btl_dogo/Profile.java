package com.btl.btl_dogo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.btl.btl_dogo.databinding.ActivityProfileBinding;
import com.btl.btl_dogo.model.User;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding binding; // Make sure this is the correct import

    private DatabaseReference mDatabase;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase= FirebaseDatabase.getInstance().getReference("users");
        // Call the function to load user data from the database
        loadUserDataFromDatabase();

        // Set up a click listener for the profile image
        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Call the function to load the profile image
        loadProfileImage();
    }

    private void loadUserDataFromDatabase() {
        String userId=  "your_user_id";
        mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String txtNameValue = snapshot.child(getPackageName()).getValue(String.class);
                    if (txtNameValue!= null){
                        binding.txtUser.setText(txtNameValue);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void loadProfileImage() {
        // Implement loading the profile image (e.g., using Glide) if needed
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Load and display the selected image using Glide into the CircleImageView
            Glide.with(this).load(selectedImageUri).into(binding.imgUser);
        }
    }
}
