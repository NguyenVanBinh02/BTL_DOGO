package com.btl.btl_dogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.btl.btl_dogo.databinding.ActivityRegisterBinding;
import com.btl.btl_dogo.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    DatabaseReference mDatabase;

    private boolean isShowPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        binding.txtFooter.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
        });

        binding.backRegister.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), KhoiDong.class));
        });
        binding.showPasswordIcon.setOnClickListener(v -> {
            if (!isShowPass) {
                // Show password
                binding.txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                isShowPass = true;
            } else {
                // Hide password
                binding.txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isShowPass = false;
            }
            // Set the cursor at the end of the password
            binding.txtPassword.setSelection(binding.txtPassword.getText().length());
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.txtName.getText().toString().trim();
                String email = binding.txtEmail.getText().toString().trim();
                String phone = binding.txtPhone.getText().toString().trim();
                String address = binding.txtAddress.getText().toString().trim();
                String password = binding.txtPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) &&
                        !TextUtils.isEmpty(address) && !TextUtils.isEmpty(password)) {
                    // Create a new User object
                    User newUser = new User(name, email, phone, address, password);

                    // Save data to Firebase Realtime Database
                    mDatabase.push().setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // If successful, display a success message and navigate to the login screen
                                Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            } else {
                                // If unsuccessful, display an error message
                                Toast.makeText(Register.this, "Đăng ký thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // If there are empty input fields, display a message
                    Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
