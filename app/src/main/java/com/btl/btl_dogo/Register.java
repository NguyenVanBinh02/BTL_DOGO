package com.btl.btl_dogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.btl.btl_dogo.databinding.ActivityRegisterBinding;
import com.btl.btl_dogo.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtFooter.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), Login.class));
        });
        binding.backRegister.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), KhoiDong.class));
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        binding.btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String name = binding.txtName.getText().toString().trim();
                String email = binding.txtEmail.getText().toString().trim();
                String phone = binding.txtPhone.getText().toString().trim();
                String address = binding.txtAddress.getText().toString().trim();
                String password = binding.txtPassword.getText().toString().trim();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(password)){
                    // Tạo một đối tượng User mới
                    User newUser = new User(name, email, phone, address, password);

                    // Lưu dữ liệu lên Firebase Realtime Database
                    mDatabase.push().setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Nếu thành công, hiển thị thông báo và chuyển về màn hình đăng nhập
                                Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                            } else {
                                // Nếu không thành công, hiển thị thông báo lỗi
                                Toast.makeText(Register.this, "Đăng ký thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // Nếu có ô nhập liệu trống, hiển thị thông báo
                    Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}